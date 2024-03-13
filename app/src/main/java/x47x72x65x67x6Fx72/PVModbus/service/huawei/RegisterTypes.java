package x47x72x65x67x6Fx72.PVModbus.service.huawei;

import x47x72x65x67x6Fx72.PVModbus.util.StringHelper;

/**
 * Datatypes of registers given by Solar_Inverter_Modbus_Interface_Definitions__V3.0_.pdf
 */
public enum RegisterTypes {
    U16(2, RegisterTypeParserImpl::parseUnsignedShort),
    U32(4,RegisterTypeParserImpl::parseUnsignedInt),
    I16(2,RegisterTypeParserImpl::parseSignedShort),
    I32(4,RegisterTypeParserImpl::parseSignedInt),
    STR(-1,RegisterTypeParserImpl::parseString),
    MLD(-1,RegisterTypeParserImpl::parseString),
    BIT16(2,RegisterTypeParserImpl::parseBitfieldShort),
    BIT32(4,RegisterTypeParserImpl::parseBitfieldInt);

    public final int byteCount;

    private final RegisterTypeParserAPI<?> parser;

    /**
     * Standard Constructor
     *
     * @param byteCount byte-representation width (-1 if count doesn't matter)
     * @param parser method to parse type
     */
    RegisterTypes(int byteCount, RegisterTypeParserAPI<?> parser) {
        this.byteCount = byteCount;
        this.parser = parser;
    }

    /** @noinspection unchecked*/
    public <T> T parse(byte[] buffer) throws IllegalArgumentException {
        if (buffer.length < this.byteCount) throw new IllegalArgumentException("Buffer doesn't fit required size (>="+this.byteCount+"); given: " + buffer.length);
        return (T) parser.parse(buffer);
    }

    public String parseToString(byte[] buffer) {
        if (buffer == null || buffer.length == 0) {
            return "-";
        }
        return String.valueOf(parser.parse(buffer));
    }

    public String parseToShiftedLongString(byte[] buffer, int offset) {
        if (buffer == null || buffer.length == 0) {
            return "-";
        }
        return StringHelper.longToDecimalString((long)parser.parse(buffer), offset);
    }

    public String parseToDateString(byte[] buffer) {
        if (buffer == null || buffer.length == 0) {
            return "-";
        }
        return String.valueOf(parser.parse(buffer));
    }
}
