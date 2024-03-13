package x47x72x65x67x6Fx72.PVModbus.ui.dto;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.Map;
import java.util.Objects;

import x47x72x65x67x6Fx72.PVModbus.service.huawei.Registers;
import x47x72x65x67x6Fx72.PVModbus.util.StringHelper;
import x47x72x65x67x6Fx72.PVModbus.util.Units;

public class PowerData {
    public String peak_active_power;
    public String active_power;
    public String reactive_power;
    public String power_factor;
    public String grid_frequency;
    public String efficiency;
    public String internal_temperature;
    public String insulation_resistance;
    public String powermeter_collection_active_power;

    public void readDataFromMap(Map<Registers, byte[]> map) {
        Log.i("PowerData", "CALL: readDataFromMap()");
        Log.d("PowerData", "readDataFromMap(" + map + ")");

        for (Map.Entry<Registers, byte[]> entry : map.entrySet()) {


                switch (entry.getKey()) {
                    case PEAK_ACTIVE_POWER_OF_CURRENT_DAY:
                        this.peak_active_power = StringHelper.longToDecimalString(Registers.PEAK_ACTIVE_POWER_OF_CURRENT_DAY.type.<Integer>parse(entry.getValue()),3) + Units.KW.toString();
                        break;

                    case ACTIVE_POWER:
                        this.active_power = StringHelper.longToDecimalString(Registers.ACTIVE_POWER.type.<Integer>parse(entry.getValue()), 3) + Units.KW.toString();
                        break;

                    case REACTIVE_POWER:
                        this.reactive_power = Registers.REACTIVE_POWER.type.parseToString(entry.getValue()) + Units.KVAR.toString();
                        break;

                    case POWER_FACTOR:
                        this.power_factor = StringHelper.longToDecimalString(Registers.POWER_FACTOR.type.<Integer>parse(entry.getValue()), 3);
                        break;

                    case GRID_FREQUENCY:
                        this.grid_frequency = StringHelper.longToDecimalString(Registers.GRID_FREQUENCY.type.<Integer>parse(entry.getValue()), 2) + Units.HZ.toString();
                        break;

                    case EFFICIENCY:
                        this.efficiency = StringHelper.longToDecimalString(Registers.EFFICIENCY.type.<Integer>parse(entry.getValue()), 2) + Units.PERCENT.toString();
                        break;

                    case INTERNAL_TEMPERATURE:
                        this.internal_temperature = StringHelper.longToDecimalString(Registers.INTERNAL_TEMPERATURE.type.<Integer>parse(entry.getValue()), 1) + Units.TEMP.toString();
                        break;

                    case INSULATION_RESISTANCE:
                        this.insulation_resistance = Registers.INSULATION_RESISTANCE.type.parseToString(entry.getValue()) + Units.MOHM.toString();
                        break;

                    case POWERMETER_COLLECTION_ACTIVE_POWER:
                        this.powermeter_collection_active_power = Registers.POWERMETER_COLLECTION_ACTIVE_POWER.type.parseToString(entry.getValue()) + Units.W.toString();
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
        PowerData powerData = (PowerData) o;
        return Objects.equals(peak_active_power, powerData.peak_active_power) && Objects.equals(active_power, powerData.active_power) && Objects.equals(reactive_power, powerData.reactive_power) && Objects.equals(power_factor, powerData.power_factor) && Objects.equals(grid_frequency, powerData.grid_frequency) && Objects.equals(efficiency, powerData.efficiency) && Objects.equals(internal_temperature, powerData.internal_temperature) && Objects.equals(insulation_resistance, powerData.insulation_resistance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(peak_active_power, active_power, reactive_power, power_factor, grid_frequency, efficiency, internal_temperature, insulation_resistance);
    }

    @NonNull
    @Override
    public String toString() {
        return "PowerData{" +
                "peak_active_power='" + peak_active_power + '\'' +
                ", active_power='" + active_power + '\'' +
                ", reactive_power='" + reactive_power + '\'' +
                ", power_factor='" + power_factor + '\'' +
                ", grid_frequency='" + grid_frequency + '\'' +
                ", efficiency='" + efficiency + '\'' +
                ", internal_temperature='" + internal_temperature + '\'' +
                ", insulation_resistance='" + insulation_resistance + '\'' +
                '}';
    }
}
