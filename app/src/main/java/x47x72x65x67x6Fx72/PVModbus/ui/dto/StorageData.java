package x47x72x65x67x6Fx72.PVModbus.ui.dto;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.Map;
import java.util.Objects;

import x47x72x65x67x6Fx72.PVModbus.service.huawei.Registers;
import x47x72x65x67x6Fx72.PVModbus.util.StringHelper;
import x47x72x65x67x6Fx72.PVModbus.util.Units;

public class StorageData {
    public String running_status;
    public String charge_and_discharge_power;
    public String current_day_charge_capacity;
    public String current_day_discharge_capacity;

    public void readDataFromMap(Map<Registers, byte[]> map) {
        Log.i("StorageData", "CALL: readDataFromMap()");
        Log.d("StorageData", "readDataFromMap(" + map + ")");

        for (Map.Entry<Registers, byte[]> entry : map.entrySet()) {


                switch (entry.getKey()) {
                    case FIRST_ENERGY_STORAGE_UNIT_RUNNING_STATUS:
                        this.running_status = StorageStatus.values()[Registers.FIRST_ENERGY_STORAGE_UNIT_RUNNING_STATUS.type.<Integer>parse(entry.getValue())].toString();
                        break;

                    case FIRST_ENERGY_STORAGE_UNIT_CHARGE_AND_DISCHARGE_POWER:
                        this.charge_and_discharge_power = Registers.FIRST_ENERGY_STORAGE_UNIT_CHARGE_AND_DISCHARGE_POWER.type.parseToString(entry.getValue()) + Units.W.toString();
                        break;

                    case FIRST_ENERGY_STORAGE_UNIT_CURRENT_DAY_CHARGE_CAPACITY:
                        this.current_day_charge_capacity = StringHelper.longToDecimalString(Registers.FIRST_ENERGY_STORAGE_UNIT_CURRENT_DAY_CHARGE_CAPACITY.type.<Long>parse(entry.getValue()), 2) + Units.KW.toString();
                        break;

                    case FIRST_ENERGY_STORAGE_UNIT_CURRENT_DAY_DISCHARGE_CAPACITY:
                        this.current_day_discharge_capacity = StringHelper.longToDecimalString(Registers.FIRST_ENERGY_STORAGE_UNIT_CURRENT_DAY_DISCHARGE_CAPACITY.type.<Long>parse(entry.getValue()), 2) + Units.KW.toString();
                        break;

                    default:
                        break;
                }


        }
    }

    protected enum StorageStatus {
        OFFLINE("offline"),
        STANDBY("standby"),
        RUNNING("running"),
        FAULT("fault"),
        SLEEP("sleep");

        private final String txt;

        StorageStatus(String txt) {
            this.txt = txt;
        }

        @NonNull
        @Override
        public String toString() {
            return txt;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StorageData that = (StorageData) o;
        return Objects.equals(running_status, that.running_status) && Objects.equals(charge_and_discharge_power, that.charge_and_discharge_power) && Objects.equals(current_day_charge_capacity, that.current_day_charge_capacity) && Objects.equals(current_day_discharge_capacity, that.current_day_discharge_capacity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(running_status, charge_and_discharge_power, current_day_charge_capacity, current_day_discharge_capacity);
    }

    @NonNull
    @Override
    public String toString() {
        return "StorageData{" +
                "running_status='" + running_status + '\'' +
                ", charge_and_discharge_power='" + charge_and_discharge_power + '\'' +
                ", current_day_charge_capacity='" + current_day_charge_capacity + '\'' +
                ", current_day_discharge_capacity='" + current_day_discharge_capacity + '\'' +
                '}';
    }
}
