package x47x72x65x67x6Fx72.PVModbus.service.huawei;

import androidx.annotation.NonNull;

/**
 * DeviceStatus given by Solar_Inverter_Modbus_Interface_Definitions__V3.0_.pdf
 */
public enum DeviceStatus {
    STANDBY_INITIALIZING(0x0000, "STANDBY_INITIALIZING"),
    STANDBY_DETECTING_INSULATION_RESISTANCE(0x0001, "STANDBY_DETECTING_INSULATION_RESISTANCE"),
    STANDBY_DETECTING_IRRADIATION(0x0002, "STANDBY_DETECTING_IRRADIATION"),
    STANDBY_DRID_DETECTING(0x0003, "STANDBY_DRID_DETECTING"),
    STARTING(0x0100, "STARTING"),
    ON_GRID(0x0200, "ON_GRID"),
    GRID_CONNECTION_POWER_LIMITED(0x0201, "GRID_CONNECTION_POWER_LIMITED"),
    GRID_CONNECTION_SELF_DERATING(0x0202, "GRID_CONNECTION_SELF_DERATING"),
    SHUTDOWN_FAULT(0x0300, "SHUTDOWN_FAULT"),
    SHUTDOWN_COMMAND(0x0301, "SHUTDOWN_COMMAND"),
    SHUTDOWN_OVGR(0x0302, "SHUTDOWN_OVGR"),
    SHUTDOWN_COMMUNICATION_DISCONNECTED(0x0303, "SHUTDOWN_COMMUNICATION_DISCONNECTED"),
    SHUTDOWN_POWER_LIMITED(0x0304, "SHUTDOWN_POWER_LIMITED"),
    SHUTDOWN_MANUAL_STARTUP_REQUIRED(0x0305, "SHUTDOWN_MANUAL_STARTUP_REQUIRED"),
    SHUTDOWN_DC_SWITCHES_DISCONNECTED(0x0306, "SHUTDOWN_DC_SWITCHES_DISCONNECTED"),
    SHUTDOWN_RAPID_CUTOFF(0x0307, "SHUTDOWN_RAPID_CUTOFF"),
    UNKNOWN(0xFFFF, "UNKNOWN");

    private final int code;
    private final String txt;

    DeviceStatus(int code, String txt) {
        this.code = code;
        this.txt = txt;
    }

    /**
     * Gets Enum by internal status-code.
     *
     * @param code status (not ordinal)
     * @return Enum:DeviceStatus
     */
    public static DeviceStatus getDeviceStatusByCode(int code) {
        for (DeviceStatus status: DeviceStatus.values()) {
            if (status.code == code) return status;
        }
        return DeviceStatus.UNKNOWN;
    }

    @NonNull
    @Override
    public String toString() {
        return txt;
    }
}
