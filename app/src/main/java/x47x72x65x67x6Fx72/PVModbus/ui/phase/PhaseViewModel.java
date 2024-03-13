package x47x72x65x67x6Fx72.PVModbus.ui.phase;

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
import x47x72x65x67x6Fx72.PVModbus.ui.dto.PhaseData;
import x47x72x65x67x6Fx72.PVModbus.GlobalSettings;

public class PhaseViewModel extends ViewModelTemplate<PhaseData> implements DataModel {

    public void fetchData() {
        Log.i("PhaseViewModel", "CALL: fetchData()");
        Log.d("PhaseViewModel", "fetchData()");
        // create new Dto
        PhaseData fetchedData = new PhaseData();

        GlobalSettings config = GlobalSettings.getInstance();

        // create requests
        ReadRequest request_phase_chunk = HuaweiModBusRequestFactory.createReadRequestFromChunk(RegisterChunks.PHASE_CHUNK, config.getUnitId());

        // request from API
        AsyncExecutor.getInstance().execute(() -> {

            ModBusTcpConnection connection = new ModBusTcpConnection(config.getIpAddress(), config.getPort());
            ResponsePacket response_phase_chunk = null;
            ModBusSession session;

            try {
                // establish tcp connection
                connection.connect();
                Log.v("PhaseViewModel", "fetchData->{Connected to Server}");

                // initial wait to prime server
                Thread.sleep(config.getTimeoutBeforeRequest());
                Log.v("PhaseViewModel", "fetchData->{Timeout passed}");

                // request package: 1
                session = new ModBusSession(request_phase_chunk);
                connection.sendRequest(session);
                Log.v("PhaseViewModel", "fetchData->{To Server: " + session + "}");

                response_phase_chunk = new ResponsePacket(connection.receiveResponse(request_phase_chunk.calculateEstResponseByteCount()));
                Log.v("PhaseViewModel", "fetchData->{From Server: " + response_phase_chunk + "}");

                // close tcp connection
                connection.disconnect();
                Log.v("PhaseViewModel", "fetchData->{Disconnected from Server}");


            } catch (IOException e) { // Exception because of TCP IO
                Log.e("PhaseViewModel", "fetchData->{"+e+"}");

            } catch (HuaweiException e) { // Exception because of Package-Content
                this.api_notify.postValue(e.getMessage());
                Log.e("PhaseViewModel", "fetchData->{"+e+"}");

            } catch (Exception e) { // everything else
                Log.e("PhaseViewModel", "fetchData->{"+e+"}");

            } finally { // attempt to close the connection
                try {
                    connection.disconnect();
                } catch (IOException e) {
                    // might be thrown if connection didn't go trough in the first place!
                    Log.e("PhaseViewModel", "fetchData->{"+e+"}");
                }
            }

            try {
                // parse and update values
                if (response_phase_chunk != null) {
                    HashMap<Registers, byte[]> map_phase_chunk = RegisterChunks.PHASE_CHUNK.splitChunkData(response_phase_chunk.registerData);
                    Log.v("PhaseViewModel", "fetchData->{map_phase_chunk updated}");
                    fetchedData.readDataFromMap(map_phase_chunk);
                }

                // push values to Observer on Fragment
                this.api_data.postValue(fetchedData);

                // notify on screen
                this.api_notify.postValue("PhaseViewModel->{UPDATED}");
            } catch (Exception e) {
                this.api_notify.postValue("PhaseViewModel->{" + e + "}");
            }
        });
    }
}