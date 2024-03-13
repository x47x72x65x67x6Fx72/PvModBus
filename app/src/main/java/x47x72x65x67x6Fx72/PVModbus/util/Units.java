package x47x72x65x67x6Fx72.PVModbus.util;

import androidx.annotation.NonNull;

/**
 * Holds Enums for displaying technical data
 */
public enum Units {
    KW("kW"),
    KVA("kVA"),
    KVAR("kVar"),
    KWH("kWh"),
    V("V"),
    A("A"),
    W("W"),
    HZ("HZ"),
    PERCENT("%"),
    TEMP("°C"),
    MOHM("MΩ"),
    SECONDS("s");

    private final String sign;

    Units(String sign) {
        this.sign = sign;
    }

    @NonNull
    @Override
    public String toString() {
        return " " + this.sign;
    }
}
