package x47x72x65x67x6Fx72.PVModbus.ui.dto;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.Map;
import java.util.Objects;

import x47x72x65x67x6Fx72.PVModbus.service.huawei.Registers;
import x47x72x65x67x6Fx72.PVModbus.util.Units;

public class PvData {
    public String pv1_voltage;
    public String pv1_current;
    public String pv2_voltage;
    public String pv2_current;
    public String pv3_voltage;
    public String pv3_current;
    public String pv4_voltage;
    public String pv4_current;

    public void readDataFromMap(Map<Registers, byte[]> map) {
        Log.i("PvData", "CALL: readDataFromMap()");
        Log.d("PvData", "readDataFromMap(" + map + ")");

        for (Map.Entry<Registers, byte[]> entry : map.entrySet()) {


                switch (entry.getKey()) {
                    case PV1_VOLTAGE:
                        this.pv1_voltage = Registers.PV1_VOLTAGE.type.parseToString(entry.getValue()) + Units.V.toString();
                        break;

                    case PV1_CURRENT:
                        this.pv1_current = Registers.PV1_CURRENT.type.parseToString(entry.getValue()) + Units.A.toString();
                        break;

                    case PV2_VOLTAGE:
                        this.pv2_voltage = Registers.PV2_VOLTAGE.type.parseToString(entry.getValue()) + Units.V.toString();
                        break;

                    case PV2_CURRENT:
                        this.pv2_current = Registers.PV2_CURRENT.type.parseToString(entry.getValue()) + Units.A.toString();
                        break;

                    case PV3_VOLTAGE:
                        this.pv3_voltage = Registers.PV3_VOLTAGE.type.parseToString(entry.getValue()) + Units.V.toString();
                        break;

                    case PV3_CURRENT:
                        this.pv3_current = Registers.PV3_CURRENT.type.parseToString(entry.getValue()) + Units.A.toString();
                        break;

                    case PV4_VOLTAGE:
                        this.pv4_voltage = Registers.PV4_VOLTAGE.type.parseToString(entry.getValue()) + Units.V.toString();
                        break;

                    case PV4_CURRENT:
                        this.pv4_current = Registers.PV4_CURRENT.type.parseToString(entry.getValue()) + Units.A.toString();
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
        PvData pvData = (PvData) o;
        return Objects.equals(pv1_voltage, pvData.pv1_voltage) && Objects.equals(pv1_current, pvData.pv1_current) && Objects.equals(pv2_voltage, pvData.pv2_voltage) && Objects.equals(pv2_current, pvData.pv2_current) && Objects.equals(pv3_voltage, pvData.pv3_voltage) && Objects.equals(pv3_current, pvData.pv3_current) && Objects.equals(pv4_voltage, pvData.pv4_voltage) && Objects.equals(pv4_current, pvData.pv4_current);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pv1_voltage, pv1_current, pv2_voltage, pv2_current, pv3_voltage, pv3_current, pv4_voltage, pv4_current);
    }

    @NonNull
    @Override
    public String toString() {
        return "PvData{" +
                "pv1_voltage='" + pv1_voltage + '\'' +
                ", pv1_current='" + pv1_current + '\'' +
                ", pv2_voltage='" + pv2_voltage + '\'' +
                ", pv2_current='" + pv2_current + '\'' +
                ", pv3_voltage='" + pv3_voltage + '\'' +
                ", pv3_current='" + pv3_current + '\'' +
                ", pv4_voltage='" + pv4_voltage + '\'' +
                ", pv4_current='" + pv4_current + '\'' +
                '}';
    }
}
