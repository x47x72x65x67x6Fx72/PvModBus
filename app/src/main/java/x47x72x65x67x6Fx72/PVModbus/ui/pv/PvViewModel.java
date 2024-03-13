package x47x72x65x67x6Fx72.PVModbus.ui.pv;

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
import x47x72x65x67x6Fx72.PVModbus.util.AsyncExecutor;
import x47x72x65x67x6Fx72.PVModbus.util.HuaweiModBusRequestFactory;
import x47x72x65x67x6Fx72.PVModbus.ui.DataModel;
import x47x72x65x67x6Fx72.PVModbus.ui.ViewModelTemplate;
import x47x72x65x67x6Fx72.PVModbus.ui.dto.PvData;
import x47x72x65x67x6Fx72.PVModbus.GlobalSettings;

public class PvViewModel extends ViewModelTemplate<PvData> implements DataModel {

    public void fetchData() {
        Log.i("PvViewModel", "CALL: fetchData()");
        Log.d("PvViewModel", "fetchData()");
        // create new Dto
        PvData fetchedData = new PvData();

        GlobalSettings config = GlobalSettings.getInstance();

        // create request
        ReadRequest request_pv_chunk = HuaweiModBusRequestFactory.createReadRequestFromChunk(RegisterChunks.PV_CHUNK, config.getUnitId());

        // request from API
        AsyncExecutor.getInstance().execute(() -> {

            ModBusTcpConnection connection = new ModBusTcpConnection(config.getIpAddress(), config.getPort());
            ResponsePacket response_pv_chunk = null;
            ModBusSession session;

            try {
                // establish tcp connection
                connection.connect();
                Log.v("PvViewModel", "fetchData->{Connected to Server}");

                // initial wait to prime server
                Thread.sleep(config.getTimeoutBeforeRequest());
                Log.v("PvViewModel", "fetchData->{Timeout passed}");


                // request package: 1
                session = new ModBusSession(request_pv_chunk);
                connection.sendRequest(session);
                Log.v("PvViewModel", "fetchData->{To Server: "+ session +"}");

                response_pv_chunk = new ResponsePacket(connection.receiveResponse(request_pv_chunk.calculateEstResponseByteCount()));
                Log.v("PvViewModel", "fetchData->{From Server: "+ response_pv_chunk +"}");


                // close tcp connection
                connection.disconnect();
                Log.v("PvViewModel", "fetchData->{Disconnected from Server}");

            } catch (IOException e) {
                Log.e("PvViewModel", "fetchData->{"+e+"}");

            } catch (HuaweiException e) {
                this.api_notify.postValue(e.getMessage());
                Log.e("PvViewModel", "fetchData->{"+e+"}");
            } catch (Exception e) { // everything else
                Log.e("PvViewModel", "fetchData->{"+e+"}");

            } finally {
                // attempt to close the connection
                try {
                    connection.disconnect();
                } catch (IOException e) {
                    // might be thrown if connection didn't go trough in the first place!
                    Log.e("PvViewModel", "fetchData->{"+e+"}");
                }
            }

            try {

                // parse and update values
                if (response_pv_chunk != null) {
                    HashMap<Registers, byte[]> map_pv_chunk = RegisterChunks.PV_CHUNK.splitChunkData(response_pv_chunk.registerData);
                    Log.v("PvViewModel", "fetchData->{map_pv_chunk updated}");
                    fetchedData.readDataFromMap(map_pv_chunk);
                }

                // push values to Observer on Fragment
                this.api_data.postValue(fetchedData);

                // notify on screen
                this.api_notify.postValue("PvViewModel->{UPDATED}");
            } catch (Exception e) {
                this.api_notify.postValue("PvViewModel->{" + e + "}");
            }

        });
    }
}