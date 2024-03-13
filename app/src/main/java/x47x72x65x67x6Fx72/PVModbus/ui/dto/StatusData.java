package x47x72x65x67x6Fx72.PVModbus.ui.dto;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.Map;
import java.util.Objects;


import x47x72x65x67x6Fx72.PVModbus.service.huawei.DeviceStatus;
import x47x72x65x67x6Fx72.PVModbus.service.huawei.HuaweiBitfields;
import x47x72x65x67x6Fx72.PVModbus.service.huawei.Registers;
import x47x72x65x67x6Fx72.PVModbus.util.StringHelper;


public class StatusData {
    public String state_1;
    public String state_2;
    public String state_3;
    public String alarm_1;
    public String alarm_2;
    public String alarm_3;
    public String device_status;
    public String fault_code;
    public String startup_time;
    public String shutdown_time;

    public void readDataFromMap(Map<Registers, byte[]> map) {
        Log.i("StatusData", "CALL: readDataFromMap()");
        Log.d("StatusData", "readDataFromMap(" + map + ")");

        for (Map.Entry<Registers, byte[]> entry : map.entrySet()) {


                switch (entry.getKey()) {
                    case STATE1:
                        this.state_1 = new HuaweiBitfields.State1(Registers.STATE1.type.<Short>parse(entry.getValue())).toString();
                        break;

                    case STATE2:
                        this.state_2 = new HuaweiBitfields.State2(Registers.STATE2.type.<Short>parse(entry.getValue())).toString();
                        break;

                    case STATE3:
                        this.state_3 = new HuaweiBitfields.State3(Registers.STATE3.type.<Integer>parse(entry.getValue())).toString();
                        break;

                    case ALARM1:
                        this.alarm_1 = new HuaweiBitfields.Alarm1(Registers.ALARM1.type.<Short>parse(entry.getValue())).toString();
                        break;

                    case ALARM2:
                        this.alarm_2 = new HuaweiBitfields.Alarm2(Registers.ALARM2.type.<Short>parse(entry.getValue())).toString();
                        break;

                    case ALARM3:
                        this.alarm_3 = new HuaweiBitfields.Alarm3(Registers.ALARM3.type.<Short>parse(entry.getValue())).toString();
                        break;

                    case DEVICE_STATUS:
                        this.device_status = DeviceStatus.getDeviceStatusByCode(Registers.DEVICE_STATUS.type.<Integer>parse(entry.getValue())).toString();
                        break;

                    case FAULT_CODE:
                        this.fault_code = Registers.FAULT_CODE.type.parseToString(entry.getValue());
                        break;

                    case STARTUP_TIME:
                        this.startup_time = StringHelper.longToEpochSecondsString(Registers.STARTUP_TIME.type.<Long>parse(entry.getValue()));
                        break;

                    case SHUTDOWN_TIME:
                        this.shutdown_time = StringHelper.longToEpochSecondsString(Registers.SHUTDOWN_TIME.type.<Long>parse(entry.getValue()));
                        break;

                    default:
                        break;
                }


        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatusData that = (StatusData) o;
        return Objects.equals(state_1, that.state_1) && Objects.equals(state_2, that.state_2) && Objects.equals(state_3, that.state_3) && Objects.equals(alarm_1, that.alarm_1) && Objects.equals(alarm_2, that.alarm_2) && Objects.equals(alarm_3, that.alarm_3) && Objects.equals(device_status, that.device_status) && Objects.equals(fault_code, that.fault_code) && Objects.equals(startup_time, that.startup_time) && Objects.equals(shutdown_time, that.shutdown_time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state_1, state_2, state_3, alarm_1, alarm_2, alarm_3, device_status, fault_code, startup_time, shutdown_time);
    }

    @NonNull
    @Override
    public String toString() {
        return "StatusData{" +
                "state_1='" + state_1 + '\'' +
                ", state_2='" + state_2 + '\'' +
                ", state_3='" + state_3 + '\'' +
                ", alarm_1='" + alarm_1 + '\'' +
                ", alarm_2='" + alarm_2 + '\'' +
                ", alarm_3='" + alarm_3 + '\'' +
                ", device_status='" + device_status + '\'' +
                ", fault_code='" + fault_code + '\'' +
                ", startup_time='" + startup_time + '\'' +
                ", shutdown_time='" + shutdown_time + '\'' +
                '}';
    }
}
