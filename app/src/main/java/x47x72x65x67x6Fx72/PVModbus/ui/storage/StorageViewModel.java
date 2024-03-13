package x47x72x65x67x6Fx72.PVModbus.ui.storage;

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
import x47x72x65x67x6Fx72.PVModbus.ui.dto.StorageData;
import x47x72x65x67x6Fx72.PVModbus.GlobalSettings;

public class StorageViewModel extends ViewModelTemplate<StorageData> implements DataModel {

    public void fetchData() {
        Log.i("StorageViewModel", "CALL: fetchData()");
        Log.d("StorageViewModel", "fetchData()");
        // create new Dto
        StorageData fetchedData = new StorageData();

        GlobalSettings config = GlobalSettings.getInstance();

        // create requests
        ReadRequest request_energy_storage_chunk_1 = HuaweiModBusRequestFactory.createReadRequestFromChunk(RegisterChunks.ENERGY_STORAGE_CHUNK_1, config.getUnitId());
        ReadRequest request_energy_storage_chunk_2 = HuaweiModBusRequestFactory.createReadRequestFromChunk(RegisterChunks.ENERGY_STORAGE_CHUNK_2, config.getUnitId());

        // request from API
        AsyncExecutor.getInstance().execute(() -> {

            ModBusTcpConnection connection = new ModBusTcpConnection(config.getIpAddress(), config.getPort());
            ResponsePacket response_energy_storage_chunk_1 = null;
            ResponsePacket response_energy_storage_chunk_2 = null;
            ModBusSession session;

            try {
                // establish tcp connection
                connection.connect();
                Log.v("StorageViewModel", "fetchData->{Connected to Server}");

                // initial wait to prime server
                Thread.sleep(config.getTimeoutBeforeRequest());
                Log.v("StorageViewModel", "fetchData->{Timeout passed}");


                // request package: 1
                session = new ModBusSession(request_energy_storage_chunk_1);
                connection.sendRequest(session);
                Log.v("StorageViewModel", "fetchData->{To Server: "+ session +"}");

                response_energy_storage_chunk_1 = new ResponsePacket(connection.receiveResponse(request_energy_storage_chunk_1.calculateEstResponseByteCount()));
                Log.v("StorageViewModel", "fetchData->{From Server: "+ response_energy_storage_chunk_1 +"}");


                // request package: 2
                session.setRequest(request_energy_storage_chunk_2);
                connection.sendRequest(session);
                Log.v("StorageViewModel", "fetchData->{To Server: "+ session +"}");

                response_energy_storage_chunk_2 = new ResponsePacket(connection.receiveResponse(request_energy_storage_chunk_2.calculateEstResponseByteCount()));
                Log.v("StorageViewModel", "fetchData->{From Server: "+ response_energy_storage_chunk_2 +"}");

                // close tcp connection
                connection.disconnect();
                Log.v("StorageViewModel", "fetchData->{Disconnected from Server}");

            } catch (IOException e) {
                Log.e("StorageViewModel", "fetchData->{"+e+"}");

            } catch (HuaweiException e) {
                this.api_notify.postValue(e.getMessage());
                Log.e("StorageViewModel", "fetchData->{"+e+"}");
            } catch (Exception e) { // everything else
                Log.e("StorageViewModel", "fetchData->{"+e+"}");

            } finally {
                // attempt to close the connection
                try {
                    connection.disconnect();
                } catch (IOException e) {
                    // might be thrown if connection didn't go trough in the first place!
                    Log.e("StorageViewModel", "fetchData->{"+e+"}");
                }
            }

            try {

                // parse and update values
                if (response_energy_storage_chunk_1 != null) {
                    HashMap<Registers, byte[]> map_energy_storage_chunk_1 = RegisterChunks.ENERGY_STORAGE_CHUNK_1.splitChunkData(response_energy_storage_chunk_1.registerData);
                    Log.v("StorageViewModel", "fetchData->{map_energy_storage_chunk_1 updated}");
                    fetchedData.readDataFromMap(map_energy_storage_chunk_1);
                }
                if (response_energy_storage_chunk_2 != null) {
                    HashMap<Registers, byte[]> map_energy_storage_chunk_2 = RegisterChunks.ENERGY_STORAGE_CHUNK_2.splitChunkData(response_energy_storage_chunk_2.registerData);
                    fetchedData.readDataFromMap(map_energy_storage_chunk_2);
                    Log.v("StorageViewModel", "fetchData->{map_energy_storage_chunk_2 updated}");
                }

                // push values to Observer on Fragment
                this.api_data.postValue(fetchedData);

                // notify on screen
                this.api_notify.postValue("StorageViewModel->{UPDATED}");
            } catch (Exception e) {
                this.api_notify.postValue("StorageViewModel->{" + e + "}");
            }
        });
    }
}
