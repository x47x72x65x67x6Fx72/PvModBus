package x47x72x65x67x6Fx72.PVModbus.ui.power;

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
import x47x72x65x67x6Fx72.PVModbus.ui.dto.PowerData;
import x47x72x65x67x6Fx72.PVModbus.GlobalSettings;

public class PowerViewModel extends ViewModelTemplate<PowerData> implements DataModel {

    public void fetchData() {
        Log.i("PowerViewModel", "CALL: fetchData()");
        Log.d("PowerViewModel", "fetchData()");
        // create new Dto
        PowerData fetchedData = new PowerData();

        GlobalSettings config = GlobalSettings.getInstance();

        // create requests
        ReadRequest request_power_chunk = HuaweiModBusRequestFactory.createReadRequestFromChunk(RegisterChunks.POWER_CHUNK, config.getUnitId());
        ReadRequest request_powermeter_chunk = HuaweiModBusRequestFactory.createReadRequestFromChunk(RegisterChunks.POWERMETER_CHUNK, config.getUnitId());

        // request from API
        AsyncExecutor.getInstance().execute(() -> {

            ModBusTcpConnection connection = new ModBusTcpConnection(config.getIpAddress(), config.getPort());
            ResponsePacket response_power_chunk = null;
            ResponsePacket response_powermeter_chunk = null;
            ModBusSession session;

            try {
                // establish tcp connection
                connection.connect();
                Log.v("PowerViewModel", "fetchData->{Connected to Server}");

                // initial wait to prime server
                Thread.sleep(config.getTimeoutBeforeRequest());
                Log.v("PowerViewModel", "fetchData->{Timeout passed}");


                // request package: 1
                session = new ModBusSession(request_power_chunk);
                connection.sendRequest(session);
                Log.v("PowerViewModel", "fetchData->{To Server: "+ session +"}");

                response_power_chunk = new ResponsePacket(connection.receiveResponse(request_power_chunk.calculateEstResponseByteCount()));
                Log.v("PowerViewModel", "fetchData->{From Server: "+ response_power_chunk +"}");


                // request package: 2
                session.setRequest(request_powermeter_chunk);
                connection.sendRequest(session);
                Log.v("GeneralViewModel", "fetchData->{To Server: "+ session +"}");

                response_powermeter_chunk = new ResponsePacket(connection.receiveResponse(request_powermeter_chunk.calculateEstResponseByteCount()));
                Log.v("GeneralViewModel", "fetchData->{From Server: "+ response_powermeter_chunk +"}");


                // close tcp connection
                connection.disconnect();
                Log.v("PowerViewModel", "fetchData->{Disconnected from Server}");

            } catch (IOException e) {
                Log.e("PowerViewModel", "fetchData->{"+e+"}");

            } catch (HuaweiException e) {
                this.api_notify.postValue(e.getMessage());
                Log.e("PowerViewModel", "fetchData->{"+e+"}");
            } catch (Exception e) { // everything else
                Log.e("PowerViewModel", "fetchData->{"+e+"}");

            } finally {
                // attempt to close the connection
                try {
                    connection.disconnect();
                } catch (IOException e) {
                    // might be thrown if connection didn't go trough in the first place!
                    Log.e("PowerViewModel", "fetchData->{"+e+"}");
                }
            }

            try {
                // parse and update values
                if (response_power_chunk != null) {
                    HashMap<Registers, byte[]> map_power_chunk = RegisterChunks.POWER_CHUNK.splitChunkData(response_power_chunk.registerData);
                    Log.v("PowerViewModel", "fetchData->{map_power_chunk updated}");
                    fetchedData.readDataFromMap(map_power_chunk);
                }
                if (response_powermeter_chunk != null) {
                    HashMap<Registers, byte[]> map_powermeter_chunk = RegisterChunks.POWERMETER_CHUNK.splitChunkData(response_powermeter_chunk.registerData);
                    fetchedData.readDataFromMap(map_powermeter_chunk);
                    Log.v("PowerViewModel", "fetchData->{map_powermeter_chunk updated}");
                }

                // push values to Observer on Fragment
                this.api_data.postValue(fetchedData);

                // notify on screen
                this.api_notify.postValue("PowerViewModel->{UPDATED}");
            } catch (Exception e) {
                this.api_notify.postValue("PowerViewModel->{" + e + "}");
            }
        });
    }
}