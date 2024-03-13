package x47x72x65x67x6Fx72.PVModbus.ui.dto;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.Map;
import java.util.Objects;

import x47x72x65x67x6Fx72.PVModbus.service.huawei.Registers;

public class HeaderData {
    public String model;
    public String sn;
    public String pn;

    public void readDataFromMap(Map<Registers, byte[]> map) {
        Log.i("HeaderData", "CALL: readDataFromMap()");
        Log.d("HeaderData", "readDataFromMap(" + map + ")");

        for (Map.Entry<Registers, byte[]> entry : map.entrySet()) {


                switch (entry.getKey()) {
                    case MODEL:
                        this.model = Registers.MODEL.type.parseToString(entry.getValue());
                        break;

                    case SN:
                        this.sn = Registers.SN.type.parseToString(entry.getValue());
                        break;

                    case PN:
                        this.pn = Registers.PN.type.parseToString(entry.getValue());
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
        HeaderData that = (HeaderData) o;
        return Objects.equals(model, that.model) && Objects.equals(sn, that.sn) && Objects.equals(pn, that.pn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, sn, pn);
    }

    @NonNull
    @Override
    public String toString() {
        return "HeaderData{" +
                "model='" + model + '\'' +
                ", sn='" + sn + '\'' +
                ", pn='" + pn + '\'' +
                '}';
    }
}
