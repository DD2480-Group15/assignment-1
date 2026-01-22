package se.kth.dd2480.group15.utils;

/**
 * Used to express how two numeric values relate to each other, where one value
 * is less than, approximately equal to, or greater than another value.
 *
 * @see #EQ
 * @see #LT
 * @see #GT
 */
public enum CompType {

    /**
     * Indicates that the compared values are considered equal within a certain precision tolerance.
     */
    EQ,

    /**
     * Indicates that the first value is strictly less than the second value.
     */
    LT,

    /**
     * Indicates that the first value is strictly greater than the second value.
     */
    GT
}