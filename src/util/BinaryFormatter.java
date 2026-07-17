package util;

/**
 * BinaryFormatter
 *
 * Utility class for displaying
 * CPU values in readable binary form.
 *
 * The CPU uses 16-bit words,
 * so all values are padded to 16 bits.
 *
 * Example:
 *
 * Input:
 * 4106
 *
 * Output:
 * 0000100000001010
 */

public class BinaryFormatter {
    // Converts an integer to a 16-bit binary string
    public static String toBinaryString(int value) {
        return String.format(
            "%16s", Integer.toBinaryString(value)
        ).replace(' ', '0');
    }
}
