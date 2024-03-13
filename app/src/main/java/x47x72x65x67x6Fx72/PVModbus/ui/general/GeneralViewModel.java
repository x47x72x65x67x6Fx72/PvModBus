package x47x72x65x67x6Fx72.PVModbus.ui.general;

import android.util.Log;

import java.io.IOException;
import java.util.HashMap;

import x47x72x65x67x6Fx72.PVModbus.service.huawei.HuaweiException;
import x47x72x65x67x6Fx72.PVModbus.service.huawei.RegisterChunks;
import x47x72x65x67x6Fx72.PVModbus.service.huawei.Registers;
import x47x72x65x67x6Fx72.PVModbus.service.modbus.ModBusTcpConnection;
import x47x72x65x67x6Fx72.PVModbus.service.modbus.ModBusSession;
import x47x72x65x67x6Fx72.PVModbus.service.modbus.ReadRequest;
import x47x72x65x67x6Fx72.PVModbus.service.modbus.ResponsePacket;
import x47x72x65x67x6Fx72.PVModbus.util.AsyncExecutor;
import x47x72x65x67x6Fx72.PVModbus.util.HuaweiModBusRequestFactory;
import x47x72x65x67x6Fx72.PVModbus.ui.DataModel;
import x47x72x65x67x6Fx72.PVModbus.ui.ViewModelTemplate;
import x47x72x65x67x6Fx72.PVModbus.ui.dto.GeneralData;
import x47x72x65x67x6Fx72.PVModbus.GlobalSettings;

public class GeneralViewModel extends ViewModelTemplate<GeneralData> implements DataModel {

    public void fetchData() {
        Log.i("GeneralViewModel", "CALL: fetchData()");
        Log.d("GeneralViewModel", "fetchData()");
        // create new Dto
        GeneralData fetchedData = new GeneralData();

        GlobalSettings config = GlobalSettings.getInstance();

        // create requests
        ReadRequest request_general_chunk = HuaweiModBusRequestFactory.createReadRequestFromChunk(RegisterChunks.GENERAL_CHUNK, config.getUnitId());
        ReadRequest request_energy_chunk = HuaweiModBusRequestFactory.createReadRequestFromChunk(RegisterChunks.ENERGY_CHUNK, config.getUnitId());

        // request from API
        AsyncExecutor.getInstance().execute(() -> {

            ModBusTcpConnection connection = new ModBusTcpConnection(config.getIpAddress(), config.getPort());
            ResponsePacket response_general_chunk = null;
            ResponsePacket response_energy_chunk = null;
            ModBusSession session;

            try {
                // establish tcp connection
                connection.connect();
                Log.v("GeneralViewModel", "fetchData->{Connected to Server}");

                // initial wait to prime server
                Thread.sleep(config.getTimeoutBeforeRequest());
                Log.v("GeneralViewModel", "fetchData->{Timeout passed}");


                // request package: 1
                session = new ModBusSession(request_general_chunk);
                connection.sendRequest(session);
                Log.v("GeneralViewModel", "fetchData->{To Server: "+ session +"}");

                response_general_chunk = new ResponsePacket(connection.receiveResponse(request_general_chunk.calculateEstResponseByteCount()));
                Log.v("GeneralViewModel", "fetchData->{From Server: "+ response_general_chunk +"}");


                // request package: 2
                session.setRequest(request_energy_chunk);
                connection.sendRequest(session);
                Log.v("GeneralViewModel", "fetchData->{To Server: "+ session +"}");

                response_energy_chunk = new ResponsePacket(connection.receiveResponse(request_energy_chunk.calculateEstResponseByteCount()));
                Log.v("GeneralViewModel", "fetchData->{From Server: "+ response_energy_chunk +"}");

                // close tcp connection
                connection.disconnect();
                Log.v("GeneralViewModel", "fetchData->{Disconnected from Server}");

            } catch (IOException e) {
                Log.e("GeneralViewModel", "fetchData->{"+e+"}");

            } catch (HuaweiException e) {
                this.api_notify.postValue(e.getMessage());
                Log.e("GeneralViewModel", "fetchData->{"+e+"}");
            } catch (Exception e) { // everything else
                Log.e("GeneralViewModel", "fetchData->{"+e+"}");

            } finally {
                // attempt to close the connection
                try {
                    connection.disconnect();
                } catch (IOException e) {
                    // might be thrown if connection didn't go trough in the first place!
                    Log.e("GeneralViewModel", "fetchData->{"+e+"}");
                }
            }

            try {
                // parse and update values
                if (response_general_chunk != null) {
                    HashMap<Registers, byte[]> map_general_chunk = RegisterChunks.GENERAL_CHUNK.splitChunkData(response_general_chunk.registerData);
                    Log.v("GeneralViewModel", "fetchData->{map_general_chunk updated}");
                    fetchedData.readDataFromMap(map_general_chunk);
                }
                if (response_energy_chunk != null) {
                    HashMap<Registers, byte[]> map_energy_chunk = RegisterChunks.ENERGY_CHUNK.splitChunkData(response_energy_chunk.registerData);
                    fetchedData.readDataFromMap(map_energy_chunk);
                    Log.v("GeneralViewModel", "fetchData->{map_energy_chunk updated}");
                }

                // push values to Observer on Fragment
                this.api_data.postValue(fetchedData);

                // notify on screen
                this.api_notify.postValue("GeneralViewModel->{UPDATED}");
            } catch (Exception e) {
                this.api_notify.postValue("GeneralViewModel->{" + e + "}");
            }

        });
    }
}
