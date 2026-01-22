package se.kth.dd2480.group15.model;

/**
 * Represents the logical connectors that determine how the truth values of
 * two LICs are combined when constructing the Preliminary Unlocking Matrix (PUM):
 *
 * <ul>
 *   <li>{@link #ANDD}: both LICs must evaluate to {@code true}</li>
 *   <li>{@link #ORR}: at least one LIC must evaluate to {@code true}</li>
 *   <li>{@link #NOTUSED}: the corresponding LIC pair is not evaluated</li>
 * </ul>
 */
public enum Connectors {
    /**
     * Logical AND connector.
     *
     * <p>
     * The combined result is {@code true} if and only if both LICs
     * are {@code true}.
     * </p>
     */
    ANDD,

    /**
     * Logical OR connector.
     *
     * <p>
     * The combined result is {@code true} if at least one of the
     * LICs are {@code true}.
     * </p>
     */
    ORR,

    /**
     * Indicates that no logical connector is applied to the LIC pair.
     *
     * <p>
     * The corresponding entry in the PUM is set to true.
     * </p>
     */
    NOTUSED
}
