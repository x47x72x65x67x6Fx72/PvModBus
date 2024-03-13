package x47x72x65x67x6Fx72.PVModbus.ui.status;

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
import x47x72x65x67x6Fx72.PVModbus.ui.dto.StatusData;
import x47x72x65x67x6Fx72.PVModbus.GlobalSettings;

public class StatusViewModel extends ViewModelTemplate<StatusData> implements DataModel {

    public void fetchData() {
        Log.i("StatusViewModel", "CALL: fetchData()");
        Log.d("StatusViewModel", "fetchData()");
        // create new Dto
        StatusData fetchedData = new StatusData();

        GlobalSettings config = GlobalSettings.getInstance();

        // create requests
        ReadRequest request_status_chunk = HuaweiModBusRequestFactory.createReadRequestFromChunk(RegisterChunks.STATUS_CHUNK, config.getUnitId());
        ReadRequest request_alarms_chunk = HuaweiModBusRequestFactory.createReadRequestFromChunk(RegisterChunks.ALARMS_CHUNK, config.getUnitId());
        ReadRequest request_additional_chunk = HuaweiModBusRequestFactory.createReadRequestFromChunk(RegisterChunks.ADDITIONAL_CHUNK, config.getUnitId());

        // request from API
        AsyncExecutor.getInstance().execute(() -> {

            ModBusTcpConnection connection = new ModBusTcpConnection(config.getIpAddress(), config.getPort());
            ResponsePacket response_status_chunk = null;
            ResponsePacket response_alarms_chunk = null;
            ResponsePacket response_additional_chunk = null;
            ModBusSession session;

            try {
                // establish tcp connection
                connection.connect();
                Log.v("StatusViewModel", "fetchData->{Connected to Server}");

                // initial wait to prime server
                Thread.sleep(config.getTimeoutBeforeRequest());
                Log.v("StatusViewModel", "fetchData->{Timeout passed}");


                // request package: 1
                session = new ModBusSession(request_status_chunk);
                connection.sendRequest(session);
                Log.v("StatusViewModel", "fetchData->{To Server: "+ session +"}");

                response_status_chunk = new ResponsePacket(connection.receiveResponse(request_status_chunk.calculateEstResponseByteCount()));
                Log.v("StatusViewModel", "fetchData->{From Server: "+ response_status_chunk +"}");


                // request package: 2
                session.setRequest(request_alarms_chunk);
                connection.sendRequest(session);
                Log.v("StatusViewModel", "fetchData->{To Server: "+ session +"}");

                response_alarms_chunk = new ResponsePacket(connection.receiveResponse(request_alarms_chunk.calculateEstResponseByteCount()));
                Log.v("StatusViewModel", "fetchData->{From Server: "+ response_alarms_chunk +"}");


                // request package: 3
                session.setRequest(request_additional_chunk);
                connection.sendRequest(session);
                Log.v("StatusViewModel", "fetchData->{To Server: "+ session +"}");

                response_additional_chunk = new ResponsePacket(connection.receiveResponse(request_additional_chunk.calculateEstResponseByteCount()));
                Log.v("StatusViewModel", "fetchData->{From Server: "+ response_additional_chunk +"}");


                // close tcp connection
                connection.disconnect();
                Log.v("StatusViewModel", "fetchData->{Disconnected from Server}");

            } catch (IOException e) {
                Log.e("StatusViewModel", "fetchData->{"+e+"}");

            } catch (HuaweiException e) {
                this.api_notify.postValue(e.getMessage());
                Log.e("StatusViewModel", "fetchData->{"+e+"}");
            } catch (Exception e) { // everything else
                Log.e("StatusViewModel", "fetchData->{"+e+"}");

            } finally {
                // attempt to close the connection
                try {
                    connection.disconnect();
                } catch (IOException e) {
                    // might be thrown if connection didn't go trough in the first place!
                    Log.e("StatusViewModel", "fetchData->{"+e+"}");
                }
            }

            try {

                // parse and update values
                if (response_status_chunk != null) {
                    HashMap<Registers, byte[]> map_status_chunk = RegisterChunks.STATUS_CHUNK.splitChunkData(response_status_chunk.registerData);
                    Log.v("StatusViewModel", "fetchData->{map_status_chunk updated}");
                    fetchedData.readDataFromMap(map_status_chunk);
                }

                if (response_alarms_chunk != null) {
                    HashMap<Registers, byte[]> map_alarms_chunk = RegisterChunks.ALARMS_CHUNK.splitChunkData(response_alarms_chunk.registerData);
                    Log.v("StatusViewModel", "fetchData->{map_alarms_chunk updated}");
                    fetchedData.readDataFromMap(map_alarms_chunk);
                }

                if (response_additional_chunk != null) {
                    HashMap<Registers, byte[]> map_additional_chunk = RegisterChunks.ADDITIONAL_CHUNK.splitChunkData(response_additional_chunk.registerData);
                    Log.v("StatusViewModel", "fetchData->{map_additional_chunk updated}");
                    fetchedData.readDataFromMap(map_additional_chunk);
                }

                // push values to Observer on Fragment
                this.api_data.postValue(fetchedData);

                // notify on screen
                this.api_notify.postValue("StatusViewModel->{UPDATED}");
            } catch (Exception e) {
                this.api_notify.postValue("StatusViewModel->{" + e + "}");
            }
        });
    }
}
