package x47x72x65x67x6Fx72.PVModbus;

/**
 * Holds all global configuration
 */
public class GlobalSettings {
    private static GlobalSettings instance;
    private String ip_address = "192.168.1.10";
    private int port = 502;
    private byte unit_id = (byte) 1;
    private int timeout_before_request = 2000; // ms

    // Private constructor to prevent instantiation from outside
    private GlobalSettings() { }

    public static GlobalSettings getInstance() {
        if (instance == null) {
            synchronized (GlobalSettings.class) {
                if (instance == null) {
                    instance = new GlobalSettings();
                }
            }
        }
        return instance;
    }

    public String getIpAddress() {
        return ip_address;
    }

    public int getPort() {
        return port;
    }

    public byte getUnitId() {
        return unit_id;
    }

    public int getTimeoutBeforeRequest() {
        return timeout_before_request;
    }

    public void setIpAddress(String ip_address) {
        this.ip_address = ip_address;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setUnitId(byte unit_id) {
        this.unit_id = unit_id;
    }

    public void setTimeoutBeforeRequest(int timeout_before_request) {
        this.timeout_before_request = timeout_before_request;
    }
}
