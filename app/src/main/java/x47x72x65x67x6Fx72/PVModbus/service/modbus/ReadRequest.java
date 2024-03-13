package x47x72x65x67x6Fx72.PVModbus.service.modbus;

import androidx.annotation.NonNull;

import java.nio.ByteBuffer;

import x47x72x65x67x6Fx72.PVModbus.service.huawei.FunctionCodes;

/**
 * MODEL-class of Modbus Request for Read-Operations
 */
public class ReadRequest implements Request {

    public static final int RESPONSE_FIELD_MBAP_LENGTH = 7; // 7 byte
    public static final int RESPONSE_FIELD_FUNCTIONCODE_LENGTH = 1; // 1 byte
    public static final int RESPONSE_FIELD_DATAHEADER_LENGTH = 1; // 1 byte for error or data length

    private final byte modBusUnitId; // mostly 0 or 1
    private final byte functionCode; // on Read -> always 0x03
    private final short startAddress;
    private final short chunkSize;


    private final int registerWidth;

    /**
     * ReadRequest-Constructor
     * Will set modBusUnitId default to 0!
     *
     * @param startAddress first register to be read
     * @param chunkSize number of registers to be read
     */
    public ReadRequest(short startAddress, short chunkSize, int registerWidth) {
        this((byte) 0, startAddress, chunkSize, registerWidth);
    }

    /**
     * ReadRequest-Constructor
     *
     * @param modBusUnitId id of modbus unit to be queried
     * @param startAddress first register to be read
     * @param chunkSize number of registers to be read
     */
    public ReadRequest(byte modBusUnitId, short startAddress, short chunkSize, int registerWidth) {
        this.modBusUnitId = modBusUnitId;
        this.functionCode = FunctionCodes.READ.code;
        this.startAddress = startAddress;
        this.chunkSize =  chunkSize;
        this.registerWidth = registerWidth;
    }

    public byte[] getBytes() {
        ByteBuffer buffer = ByteBuffer.allocate(6);
        buffer.put(this.modBusUnitId);
        buffer.put(this.functionCode);
        buffer.putShort(this.startAddress);
        buffer.putShort(this.chunkSize);
        return buffer.array();
    }

    /**
     * Calculates estimated ModBus payload-size
     *
     * @return required buffer-size
     */
    public int calculateEstResponseByteCount() {
        return RESPONSE_FIELD_MBAP_LENGTH
                + RESPONSE_FIELD_FUNCTIONCODE_LENGTH
                + RESPONSE_FIELD_DATAHEADER_LENGTH
                + (this.chunkSize * this.registerWidth);
    }

    @NonNull
    @Override
    public String toString() {
        return "ReadRequest{" +
                "modBusUnitId=" + modBusUnitId +
                ", functionCode=" + functionCode +
                ", startAddress=" + startAddress +
                ", chunkSize=" + chunkSize +
                ", registerWidth=" + registerWidth +
                '}';
    }
}
