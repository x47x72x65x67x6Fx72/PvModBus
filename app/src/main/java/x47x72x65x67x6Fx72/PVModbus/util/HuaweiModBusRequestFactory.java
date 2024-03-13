package x47x72x65x67x6Fx72.PVModbus.util;

import x47x72x65x67x6Fx72.PVModbus.service.huawei.RegisterChunks;
import x47x72x65x67x6Fx72.PVModbus.service.huawei.Registers;
import x47x72x65x67x6Fx72.PVModbus.service.modbus.ReadRequest;

/**
 * FACTORY-Class that holds static methods to generate HuaweiModBusRequests
 */
public class HuaweiModBusRequestFactory {

    public static ReadRequest createReadRequestFromRegister(Registers register) {
        return new ReadRequest(
                Registers.MODEL_ID.address,
                (short) Registers.MODEL_ID.quantity,
                Registers.Constants.BYTES_PER_REGISTER);
    }

    public static ReadRequest createReadRequestFromRegister(Registers register, byte unitId) {
        return new ReadRequest(
                unitId,
                Registers.MODEL_ID.address,
                (short) Registers.MODEL_ID.quantity,
                Registers.Constants.BYTES_PER_REGISTER);
    }

    public static ReadRequest createReadRequestFromChunk(RegisterChunks chunk) {
        final Registers startRegister = chunk.getRegisters()[0];                                // First entry
        final Registers lastRegister = chunk.getRegisters()[chunk.getRegisters().length - 1];   // Last entry

        final short chunkSize = (short) (lastRegister.address - startRegister.address + lastRegister.quantity);

        return new ReadRequest(
                startRegister.address,
                chunkSize,
                Registers.Constants.BYTES_PER_REGISTER);
    }

    public static ReadRequest createReadRequestFromChunk(RegisterChunks chunk, byte unitId) {
        final Registers startRegister = chunk.getRegisters()[0];                                // First entry
        final Registers lastRegister = chunk.getRegisters()[chunk.getRegisters().length - 1];   // Last entry

        final short chunkSize = (short) (lastRegister.address - startRegister.address + lastRegister.quantity);

        return new ReadRequest(
                unitId,
                startRegister.address,
                chunkSize,
                Registers.Constants.BYTES_PER_REGISTER);
    }
}
