package x47x72x65x67x6Fx72.PVModbus.ui.dto;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.Map;
import java.util.Objects;

import x47x72x65x67x6Fx72.PVModbus.service.huawei.Registers;
import x47x72x65x67x6Fx72.PVModbus.util.StringHelper;
import x47x72x65x67x6Fx72.PVModbus.util.Units;

public class GeneralData {
    public String model_id;
    public String number_of_pv_strings;
    public String number_of_mpp_trackers;
    public String rated_power;
    public String maximum_active_power;
    public String maximum_apparent_power;
    public String maximum_reactive_power_fed_to_grid;
    public String maximum_reactive_power_absorbed_from_grid;
    public String accumulated_energy_yield;
    public String daily_energy_yield;

    public void readDataFromMap(Map<Registers, byte[]> map) {
        Log.i("GeneralData", "CALL: readDataFromMap()");
        Log.d("GeneralData", "readDataFromMap(" + map + ")");

        for (Map.Entry<Registers, byte[]> entry : map.entrySet()) {



                switch (entry.getKey()) {
                    case MODEL_ID:
                        this.model_id = Registers.MODEL_ID.type.parseToString(entry.getValue());
                        break;

                    case NUMBER_OF_PV_STRINGS:
                        this.number_of_pv_strings = Registers.NUMBER_OF_PV_STRINGS.type.parseToString(entry.getValue());
                        break;

                    case NUMBER_OF_MPP_TRACKERS:
                        this.number_of_mpp_trackers = Registers.NUMBER_OF_PV_STRINGS.type.parseToString(entry.getValue());
                        break;

                    case RATED_POWER:
                        this.rated_power = StringHelper.longToDecimalString(Registers.RATED_POWER.type.<Long>parse(entry.getValue()), 3) + Units.KW.toString();
                        break;

                    case MAXIMUM_ACTIVE_POWER:
                        this.maximum_active_power = StringHelper.longToDecimalString(Registers.MAXIMUM_ACTIVE_POWER.type.<Long>parse(entry.getValue()), 3) + Units.KW.toString();
                        break;

                    case MAXIMUM_APPARENT_POWER:
                        this.maximum_apparent_power = StringHelper.longToDecimalString(Registers.MAXIMUM_APPARENT_POWER.type.<Long>parse(entry.getValue()), 3) + Units.KVA.toString();
                        break;

                    case MAXIMUM_REACTIVE_POWER_FED_TO_THE_POWER_GRID:
                        this.maximum_reactive_power_fed_to_grid = StringHelper.longToDecimalString(Registers.MAXIMUM_REACTIVE_POWER_FED_TO_THE_POWER_GRID.type.<Integer>parse(entry.getValue()), 3) + Units.KVAR.toString();
                        break;

                    case MAXIMUM_REACTIVE_POWER_ABSORBED_FROM_THE_POWER_GRID:
                        this.maximum_reactive_power_absorbed_from_grid = StringHelper.longToDecimalString(Registers.MAXIMUM_REACTIVE_POWER_ABSORBED_FROM_THE_POWER_GRID.type.<Integer>parse(entry.getValue()), 3) + Units.KVAR.toString();
                        break;

                    case ACCUMULATED_ENERGY_YIELD:
                        this.accumulated_energy_yield = StringHelper.longToDecimalString(Registers.ACCUMULATED_ENERGY_YIELD.type.<Long>parse(entry.getValue()), 3) + Units.KWH.toString();
                        break;

                    case DAILY_ENERGY_YIELD:
                        this.daily_energy_yield = StringHelper.longToDecimalString(Registers.DAILY_ENERGY_YIELD.type.<Long>parse(entry.getValue()), 3) + Units.KWH.toString();
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
        GeneralData that = (GeneralData) o;
        return Objects.equals(model_id, that.model_id) && Objects.equals(number_of_pv_strings, that.number_of_pv_strings) && Objects.equals(number_of_mpp_trackers, that.number_of_mpp_trackers) && Objects.equals(rated_power, that.rated_power) && Objects.equals(maximum_active_power, that.maximum_active_power) && Objects.equals(maximum_apparent_power, that.maximum_apparent_power) && Objects.equals(maximum_reactive_power_fed_to_grid, that.maximum_reactive_power_fed_to_grid) && Objects.equals(maximum_reactive_power_absorbed_from_grid, that.maximum_reactive_power_absorbed_from_grid) && Objects.equals(accumulated_energy_yield, that.accumulated_energy_yield) && Objects.equals(daily_energy_yield, that.daily_energy_yield);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model_id, number_of_pv_strings, number_of_mpp_trackers, rated_power, maximum_active_power, maximum_apparent_power, maximum_reactive_power_fed_to_grid, maximum_reactive_power_absorbed_from_grid, accumulated_energy_yield, daily_energy_yield);
    }

    @NonNull
    @Override
    public String toString() {
        return "GeneralData{" +
                "model_id='" + model_id + '\'' +
                ", number_of_pv_strings='" + number_of_pv_strings + '\'' +
                ", number_of_mpp_trackers='" + number_of_mpp_trackers + '\'' +
                ", rated_power='" + rated_power + '\'' +
                ", maximum_active_power='" + maximum_active_power + '\'' +
                ", maximum_apparent_power='" + maximum_apparent_power + '\'' +
                ", maximum_reactive_power_fed_to_grid='" + maximum_reactive_power_fed_to_grid + '\'' +
                ", maximum_reactive_power_absorbed_from_grid='" + maximum_reactive_power_absorbed_from_grid + '\'' +
                ", accumulated_energy_yield='" + accumulated_energy_yield + '\'' +
                ", daily_energy_yield='" + daily_energy_yield + '\'' +
                '}';
    }
}
