package x47x72x65x67x6Fx72.PVModbus.service.modbus;

/**
 * API-class of Modbus Request
 */
public interface Request {
    /**
     * Will create request-byte-array that might be sent via ModBusConnection
     *
     * @return request-data as byte-array
     */
    byte[] getBytes();

    /**
     * Calculates estimated ModBus payload-size
     *
     * @return required buffer-size
     */
    int calculateEstResponseByteCount();
}
