package x47x72x65x67x6Fx72.PVModbus.service.huawei;

import java.util.Arrays;

/**
 * Exception-Code-List given by Solar_Inverter_Modbus_Interface_Definitions__V3.0_.pdf
 * Exceptions that might be thrown while communicating via modbus.
 */
public class HuaweiException extends Exception {

    public enum ExceptionCode {
        ILLEGAL_FUNCTION(0x01, "The function code received in the query is not an allowable action for the server (or slave)!"),
        ILLEGAL_DATA_ADDRESS(0x02, "The data address received in the query is not an allowable address for the server!"),
        ILLEGAL_DATA_VALUE(0x03, "he value contained in the query data field is not an allowable value for the server (or slave)!"),
        SLAVE_NODE_FAILURE(0x04, "An error occurred while the server was attempting to perform the requested action!"),
        SLAVE_DEVICE_BUSY(0x06, "Server cannot accept a Modbus request PDU!"),
        NO_PERMISSION(0x80, "Permission authentication failure or permission expiration!");

        private final String description;
        private final byte code;

        /**
         * Standard Constructor
         *
         * @param code Exception-identifier
         * @param description Exception-message
         */
        ExceptionCode(int code, String description) {
            this.code = (byte) (code & 0xff);
            this.description = description;
        }

        public String getDescription() {
            return description;
        }

        public int getCode() {
            return code;
        }
    }

    private final ExceptionCode exceptionCode;

    /**
     * Standard Constructor
     *
     * @param exceptionCode Exception by huawei (ExceptionCode-ENUM)
     */
    public HuaweiException(ExceptionCode exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

    @Override
    public String getMessage() {
        return this.exceptionCode.getDescription();
    }


    /**
     * Gets the ExceptionCode-Enum for given Code. (not Enum ordinal)
     *
     * @param code HuaweiExceptionCode
     * @return HuaweiExceptionCode-Enum
     */
    public static ExceptionCode getHuaweiExceptionByCode(byte code) {
        return Arrays.stream(ExceptionCode.values()).filter(
                v -> v.getCode() == code).findFirst().orElse(null);
    }
}
