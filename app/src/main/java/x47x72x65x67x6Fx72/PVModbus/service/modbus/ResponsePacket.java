package x47x72x65x67x6Fx72.PVModbus.service.modbus;

import androidx.annotation.NonNull;

import java.util.Arrays;

import x47x72x65x67x6Fx72.PVModbus.service.huawei.FunctionCodes;
import x47x72x65x67x6Fx72.PVModbus.service.huawei.HuaweiException;

/**
 * MODEL-class of Modbus response
 */
public class ResponsePacket {
    public final short transactionId;
    public final short protocolType;
    public final short dataLength;
    public final byte modBusUnitId;
    public final byte functionCode;
    public final byte numberOfBytesOrErrorId;
    public final byte[] registerData;

    /**
     * Standard Constructor
     *
     * @param response raw byte-buffer
     * @throws IllegalArgumentException buffer doesn't fit required length
     * @throws HuaweiException response might contain exception thrown by server
     */
    public ResponsePacket(final byte[] response) throws IllegalArgumentException, HuaweiException {
        // length 9 is minimum required response length of valid response
        // a valid response includes also responses with errors
        if (response.length < 9) throw new IllegalArgumentException("Response doesn't fit required length(>=9)! given: " + response.length);

        this.transactionId = (short) ((response[0] << 8) | response[1]);
        this.protocolType = (short) ((response[2] << 8) | response[3]);
        this.dataLength = (short) ((response[4] << 8) | response[5]);
        this.modBusUnitId = response[6];
        this.functionCode = response[7];
        this.numberOfBytesOrErrorId = response[8];

        if (this.functionCode == FunctionCodes.ERROR.code) {
            // get and throw according error;
            throw new HuaweiException(HuaweiException.getHuaweiExceptionByCode(this.numberOfBytesOrErrorId));
        }

        this.registerData = Arrays.copyOfRange(response, 9, 9 + this.numberOfBytesOrErrorId);
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ResponsePacket{");
        builder.append(String.format("transactionId: x%04X", transactionId));
        builder.append(String.format(", protocolType: x%04X", protocolType));
        builder.append(String.format(", dataLength: x%04X", dataLength));
        builder.append(String.format(", modBusUnitId: x%02X", modBusUnitId));
        builder.append(String.format(", functionCode: x%02X", functionCode));
        builder.append(String.format(", numberOfBytesOrErrorId: x%02X", numberOfBytesOrErrorId));
        builder.append(", registerData: [");

        for (byte b : registerData) builder.append(String.format(" 0x%02X ", b));

        builder.append("]}");

        return builder.toString();
    }
}
