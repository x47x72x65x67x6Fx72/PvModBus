package x47x72x65x67x6Fx72.PVModbus.service.huawei;

@FunctionalInterface
public interface RegisterTypeParserAPI<T> {
    T parse(byte[] buffer);
}
