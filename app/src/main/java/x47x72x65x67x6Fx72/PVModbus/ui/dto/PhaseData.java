package x47x72x65x67x6Fx72.PVModbus.ui.dto;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.Map;
import java.util.Objects;

import x47x72x65x67x6Fx72.PVModbus.service.huawei.Registers;
import x47x72x65x67x6Fx72.PVModbus.util.StringHelper;
import x47x72x65x67x6Fx72.PVModbus.util.Units;

public class PhaseData {
    public String input_power;
    public String line_voltage_between_a_and_b;
    public String line_voltage_between_b_and_c;
    public String line_voltage_beween_c_and_a;
    public String a_voltage;
    public String b_voltage;
    public String c_voltage;
    public String a_current;
    public String b_current;
    public String c_current;

    public void readDataFromMap(Map<Registers, byte[]> map) {
        Log.i("PhaseData", "CALL: readDataFromMap()");
        Log.d("PhaseData", "readDataFromMap(" + map + ")");

        for (Map.Entry<Registers, byte[]> entry : map.entrySet()) {


                switch (entry.getKey()) {
                    case INPUT_POWER:
                        this.input_power = StringHelper.longToDecimalString(Registers.INPUT_POWER.type.<Integer>parse(entry.getValue()), 3) + Units.KW.toString();
                        break;

                    case LINE_VOLTAGE_BETWEEN_PHASES_A_AND_B:
                        this.line_voltage_between_a_and_b = Registers.LINE_VOLTAGE_BETWEEN_PHASES_A_AND_B.type.parseToString(entry.getValue()) + Units.V.toString();
                        break;

                    case LINE_VOLTAGE_BETWEEN_PHASES_B_AND_C:
                        this.line_voltage_between_b_and_c = Registers.LINE_VOLTAGE_BETWEEN_PHASES_B_AND_C.type.parseToString(entry.getValue()) + Units.V.toString();
                        break;

                    case LINE_VOLTAGE_BETWEEN_PHASES_C_AND_A:
                        this.line_voltage_beween_c_and_a = Registers.LINE_VOLTAGE_BETWEEN_PHASES_C_AND_A.type.parseToString(entry.getValue()) + Units.V.toString();
                        break;

                    case PHASE_A_VOLTAGE:
                        this.a_voltage = Registers.PHASE_A_VOLTAGE.type.parseToString(entry.getValue()) + Units.V.toString();
                        break;

                    case PHASE_B_VOLTAGE:
                        this.b_voltage = Registers.PHASE_B_VOLTAGE.type.parseToString(entry.getValue()) + Units.V.toString();
                        break;

                    case PHASE_C_VOLTAGE:
                        this.c_voltage = Registers.PHASE_C_VOLTAGE.type.parseToString(entry.getValue()) + Units.V.toString();
                        break;

                    case PHASE_A_CURRENT:
                        this.a_current = Registers.PHASE_A_CURRENT.type.parseToString(entry.getValue()) + Units.A.toString();
                        break;

                    case PHASE_B_CURRENT:
                        this.b_current = Registers.PHASE_B_CURRENT.type.parseToString(entry.getValue()) + Units.A.toString();
                        break;

                    case PHASE_C_CURRENT:
                        this.c_current = Registers.PHASE_C_CURRENT.type.parseToString(entry.getValue()) + Units.A.toString();
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
        PhaseData phaseData = (PhaseData) o;
        return Objects.equals(input_power, phaseData.input_power) && Objects.equals(line_voltage_between_a_and_b, phaseData.line_voltage_between_a_and_b) && Objects.equals(line_voltage_between_b_and_c, phaseData.line_voltage_between_b_and_c) && Objects.equals(line_voltage_beween_c_and_a, phaseData.line_voltage_beween_c_and_a) && Objects.equals(a_voltage, phaseData.a_voltage) && Objects.equals(b_voltage, phaseData.b_voltage) && Objects.equals(c_voltage, phaseData.c_voltage) && Objects.equals(a_current, phaseData.a_current) && Objects.equals(b_current, phaseData.b_current) && Objects.equals(c_current, phaseData.c_current);
    }

    @Override
    public int hashCode() {
        return Objects.hash(input_power, line_voltage_between_a_and_b, line_voltage_between_b_and_c, line_voltage_beween_c_and_a, a_voltage, b_voltage, c_voltage, a_current, b_current, c_current);
    }

    @NonNull
    @Override
    public String toString() {
        return "PhaseData{" +
                "input_power='" + input_power + '\'' +
                ", line_voltage_between_a_and_b='" + line_voltage_between_a_and_b + '\'' +
                ", line_voltage_between_b_and_c='" + line_voltage_between_b_and_c + '\'' +
                ", line_voltage_beween_c_and_a='" + line_voltage_beween_c_and_a + '\'' +
                ", a_voltage='" + a_voltage + '\'' +
                ", b_voltage='" + b_voltage + '\'' +
                ", c_voltage='" + c_voltage + '\'' +
                ", a_current='" + a_current + '\'' +
                ", b_current='" + b_current + '\'' +
                ", c_current='" + c_current + '\'' +
                '}';
    }
}
