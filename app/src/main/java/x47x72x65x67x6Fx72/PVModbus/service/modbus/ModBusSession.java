package x47x72x65x67x6Fx72.PVModbus.service.modbus;

import androidx.annotation.NonNull;

import java.nio.ByteBuffer;

/**
 * Will manage session-data of ModBus-connection
 */
public class ModBusSession implements Request {
    private short transactionId;

    private static final byte[] PROTOCOL_TYPE = {0x00, 0x00}; // always 00 00 in our usecase

    private Request request;

    /**
     * Will call Standard Constructor
     * Will set initial transactionId to 0!
     *
     * @param request payload of first transaction of this session
     */
    public ModBusSession(Request request) {
        this(request, (short) 0);
    }

    /**
     * Standard Constructor
     *
     * @param request payload of first transaction of this session
     * @param transactionId id of session if it needs to be customizable
     */
    public ModBusSession(Request request, short transactionId) {
        this.transactionId = transactionId;
        this.request = request;
    }

    public byte[] getBytes() {
        final byte[] requestBytes = this.request.getBytes();

        // 2 bytes -> transactionId
        // 2 bytes -> protocolId
        // 2 bytes -> request length
        // n bytes -> request content
        final int length = 6 + requestBytes.length;

        ByteBuffer buffer = ByteBuffer.allocate(length);
        buffer.putShort(this.transactionId);
        buffer.put(PROTOCOL_TYPE);
        buffer.putChar((char) requestBytes.length);
        buffer.put(requestBytes);
        return buffer.array();
    }

    /**
     * Will set request-data
     *
     * @param request next request of session
     * @return self
     */
    public ModBusSession setRequest(Request request) {
        this.request = request;
        return this;
    }

    /**
     * Will increment session id
     *
     * @return self
     */
    public ModBusSession incrementSession() {
        this.transactionId++;
        return this;
    }

    public int calculateEstResponseByteCount() {
        return this.request.calculateEstResponseByteCount();
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder hexString = new StringBuilder();
        hexString.append("ModBusRequestPacket{");
        for(byte b : this.getBytes()) {
            hexString.append(String.format("%02X ", b));
        }
        return hexString.toString().trim() + "}";
    }

}
