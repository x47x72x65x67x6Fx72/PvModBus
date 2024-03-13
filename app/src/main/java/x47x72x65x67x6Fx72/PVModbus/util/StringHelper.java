package x47x72x65x67x6Fx72.PVModbus.util;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * Class that holds static Methods to generate Strings from values
 */
public class StringHelper {

    public static String longToDecimalString(long number, int offset) {
        StringBuilder builder = new StringBuilder();
        char[] digits = Long.toString(number).toCharArray();

        int length_first_half = digits.length - offset;

        int digitsPtr;
        for (digitsPtr = 0; digitsPtr < length_first_half; digitsPtr++) {
            builder.append(digits[digitsPtr]);
        }

        if (length_first_half <= 0) builder.append("0"); // append leading zero

        builder.append("."); // halftime :D

        for (int i = length_first_half; i < 0; i++) { // append leading zeros
            builder.append("0");
        }

        for (int i = digitsPtr; i < digits.length; i++) {
            builder.append(digits[i]);
        }

        return builder.toString();
    }

    public static String longToEpochSecondsString(long epochSeconds) {
        LocalDateTime time = LocalDateTime.ofEpochSecond(epochSeconds,0, ZoneOffset.ofHours(0));
        return time.toString();
    }
}
