package x47x72x65x67x6Fx72.PVModbus.service.huawei;

/**
 * Holds static implementations on how to process a given byte-buffer
 */
public class RegisterTypeParserImpl {
    public static int parseUnsignedShort(final byte[] buffer)  {
        return (buffer[0] & 0xFF) << 8 | (buffer[1] & 0xFF);
    }

    public static long parseUnsignedInt(final byte[] buffer) {
        return (long) (buffer[0] & 0xFF) << 24 |
                (buffer[1] & 0xFF) << 16 |
                (buffer[2] & 0xFF) << 8  |
                (buffer[3] & 0xFF);
    }

    public static short parseSignedShort(final byte[] buffer) {
        return (short) ((buffer[0] & 0xFF) << 8 | (buffer[1] & 0xFF));
    }

    public static int parseSignedInt(final byte[] buffer) {
        return (buffer[0] & 0xFF) << 24 |
                (buffer[1] & 0xFF) << 16 |
                (buffer[2] & 0xFF) << 8  |
                (buffer[3] & 0xFF);
    }

    public static String parseString(final byte[] buffer) {
        char[] charString = new char[buffer.length];

        for(int i = 0; i < buffer.length; i++) {
            charString[i] = (char) (buffer[i] & 0xFF);
        }
        return new String(charString);
    }

    public static short parseBitfieldShort(final byte[] buffer) {
        // only combines buffer to 1 bitfield
        return parseSignedShort(buffer);// masking-operations required if data shall be accessed
    }

    public static int parseBitfieldInt(final byte[] buffer) {
        // only combines buffer to 1 bitfield
        return parseSignedInt(buffer); // masking-operations required if data shall be accessed
    }
}
