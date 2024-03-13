package x47x72x65x67x6Fx72.PVModbus;

import android.util.Log;

import java.io.IOException;
import java.util.HashMap;

import x47x72x65x67x6Fx72.PVModbus.service.huawei.HuaweiException;
import x47x72x65x67x6Fx72.PVModbus.service.huawei.RegisterChunks;
import x47x72x65x67x6Fx72.PVModbus.service.huawei.Registers;
import x47x72x65x67x6Fx72.PVModbus.service.modbus.ModBusSession;
import x47x72x65x67x6Fx72.PVModbus.service.modbus.ModBusTcpConnection;
import x47x72x65x67x6Fx72.PVModbus.service.modbus.ReadRequest;
import x47x72x65x67x6Fx72.PVModbus.service.modbus.ResponsePacket;
import x47x72x65x67x6Fx72.PVModbus.ui.DataModel;
import x47x72x65x67x6Fx72.PVModbus.ui.ViewModelTemplate;
import x47x72x65x67x6Fx72.PVModbus.ui.dto.HeaderData;
import x47x72x65x67x6Fx72.PVModbus.util.AsyncExecutor;
import x47x72x65x67x6Fx72.PVModbus.util.HuaweiModBusRequestFactory;

public class MainActivityViewModel extends ViewModelTemplate<HeaderData> implements DataModel {

    public void fetchData() {
        Log.i("MainActivityViewModel", "CALL: fetchData()");
        Log.d("MainActivityViewModel", "fetchData()");
        // create new Dto
        HeaderData fetchedData = new HeaderData();

        GlobalSettings config = GlobalSettings.getInstance();

        // create requests
        ReadRequest request_names_chunk = HuaweiModBusRequestFactory.createReadRequestFromChunk(RegisterChunks.NAMES_CHUNK, config.getUnitId());

        // request from API
        AsyncExecutor.getInstance().execute(() -> {

            ModBusTcpConnection connection = new ModBusTcpConnection(config.getIpAddress(), config.getPort());
            ResponsePacket response_names_chunk = null;
            ModBusSession session;

            try {
                // establish tcp connection
                connection.connect();
                Log.v("MainActivityViewModel", "fetchData->{Connected to Server}");

                // initial wait to prime server
                Thread.sleep(config.getTimeoutBeforeRequest());
                Log.v("MainActivityViewModel", "fetchData->{Timeout passed}");


                // request package: 1
                session = new ModBusSession(request_names_chunk);
                connection.sendRequest(session);
                Log.v("MainActivityViewModel", "fetchData->{To Server: "+ session +"}");

                response_names_chunk = new ResponsePacket(connection.receiveResponse(request_names_chunk.calculateEstResponseByteCount()));
                Log.v("MainActivityViewModel", "fetchData->{From Server: "+ response_names_chunk +"}");


                // close tcp connection
                connection.disconnect();
                Log.v("MainActivityViewModel", "fetchData->{Disconnected from Server}");

            } catch (IOException e) {
                Log.e("MainActivityViewModel", "fetchData->{"+e+"}");

            } catch (HuaweiException e) {
                this.api_notify.postValue(e.getMessage());
                Log.e("MainActivityViewModel", "fetchData->{"+e+"}");
            } catch (Exception e) { // everything else
                Log.e("MainActivityViewModel", "fetchData->{"+e+"}");

            } finally {
                // attempt to close the connection
                try {
                    connection.disconnect();
                } catch (IOException e) {
                    // might be thrown if connection didn't go trough in the first place!
                    Log.e("MainActivityViewModel", "fetchData->{"+e+"}");
                }
            }

            try {

                // parse and update values
                if (response_names_chunk != null) {
                    HashMap<Registers, byte[]> map_names_chunk = RegisterChunks.NAMES_CHUNK.splitChunkData(response_names_chunk.registerData);
                    Log.v("MainActivityViewModel", "fetchData->{map_names_chunk updated}");
                    fetchedData.readDataFromMap(map_names_chunk);
                }

                // push values to Observer on Fragment
                this.api_data.postValue(fetchedData);

                // notify on screen
                this.api_notify.postValue("MainActivityViewModel->{UPDATED}");
            } catch (Exception e) {
                this.api_notify.postValue("MainActivityViewModel->{" + e + "}");
            }
        });
    }
}
