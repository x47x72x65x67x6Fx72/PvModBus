package x47x72x65x67x6Fx72.PVModbus.service.huawei;

/**
 * Function codes given by Solar_Inverter_Modbus_Interface_Definitions__V3.0_.pdf
 * API supports only a partial number of FunctionCodes.
 */
public enum FunctionCodes {
    READ(0x03),                 // Read access
    WRITE_SINGLE(0x06),         // NOT USED
    WRITE_MULTIPLE(0x10),       // NOT USED
    ERROR(0x83);                // response code if an error occurred

    public final byte code;
    FunctionCodes(int code) {
        this.code = (byte) (code & 0xFF);
    }
}
