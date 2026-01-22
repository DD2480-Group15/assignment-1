package se.kth.dd2480.group15.utils;

/**
 * Collection of static utility methods used when checking LICs truth values.
 */
public class Utils {

    private final static double PRECISION = 0.000001;

    /**
     * Compares two double values with a certain precision tolerance. Determines
     * if the first value is less than, equal to, or greater than the second value.
     *
     * @param a the first double value to compare
     * @param b the second double value to compare
     * @return a {@link CompType} indicating the comparison result:
     * <ul>
     *     <li>{@code EQ} if the values are approximately equal,</li>
     *     <li>{@code LT} if the first value is less than the second,</li>
     *     <li>{@code GT} if the first value is greater than the second</li>
     * </ul>
     */
    public static CompType doubleCompare(double a, double b) {
        if (Math.abs(a - b) < PRECISION) return CompType.EQ;
        if (a < b) return CompType.LT;
        return CompType.GT;
    }
}
