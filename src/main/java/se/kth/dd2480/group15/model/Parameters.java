package se.kth.dd2480.group15.model;

/**
 * Immutable parameter container for the different parameters used when evaluating the LICs.
 *
 * <p>This class uses the Builder pattern. All values are copied from
 * {@link Builder} at construction time.</p>
 */
public final class Parameters {
    private final double length1;
    private final double radius1;
    private final double epsilon;
    private final double area1;
    private final int qPts;
    private final int quads;
    private final double dist;
    private final int nPts;
    private final int kPts;
    private final int aPts;
    private final int bPts;
    private final int cPts;
    private final int dPts;
    private final int ePts;
    private final int fPts;
    private final int gPts;
    private final double length2;
    private final double radius2;
    private final double area2;

    private Parameters(Builder builder) {
        this.length1 = builder.length1;
        this.radius1 = builder.radius1;
        this.epsilon = builder.epsilon;
        this.area1 = builder.area1;
        this.qPts = builder.qPts;
        this.quads = builder.quads;
        this.dist = builder.dist;
        this.nPts = builder.nPts;
        this.kPts = builder.kPts;
        this.aPts = builder.aPts;
        this.bPts = builder.bPts;
        this.cPts = builder.cPts;
        this.dPts = builder.dPts;
        this.ePts = builder.ePts;
        this.fPts = builder.fPts;
        this.gPts = builder.gPts;
        this.length2 = builder.length2;
        this.radius2 = builder.radius2;
        this.area2 = builder.area2;
    }

    /**
     * Creates a new builder instance for constructing a {@link Parameters} object.
     *
     * @return a new {@link Builder}
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * @return length in LICs 0, 7, 12
     */
    public double length1() {
        return length1;
    }

    /**
     * @return radius in LICs 1, 8, 13
     */
    public double radius1() {
        return radius1;
    }

    /**
     * @return deviation from PI in LICs 2, 9
     */
    public double epsilon() {
        return epsilon;
    }

    /**
     * @return area in LICs 3, 10, 14
     */
    public double area1() {
        return area1;
    }

    /**
     * @return number of consecutive points in LIC 4
     */
    public int qPts() {
        return qPts;
    }

    /**
     * @return number of quadrants in LIC 4
     */
    public int quads() {
        return quads;
    }

    /**
     * @return distance in LIC 6
     */
    public double dist() {
        return dist;
    }

    /**
     * @return number of consecutive points in LIC 6
     */
    public int nPts() {
        return nPts;
    }

    /**
     * @return number of intervening points in LICs 7, 12
     */
    public int kPts() {
        return kPts;
    }

    /**
     * @return first number of intervening points in LICs 8, 13
     */
    public int aPts() {
        return aPts;
    }

    /**
     * @return second number of intervening points in LICs 8, 13
     */
    public int bPts() {
        return bPts;
    }

    /**
     * @return first number of intervening points in LIC 9
     */
    public int cPts() {
        return cPts;
    }

    /**
     * @return second number of intervening points in LIC 9
     */
    public int dPts() {
        return dPts;
    }

    /**
     * @return first number of intervening points in LICs 10, 14
     */
    public int ePts() {
        return ePts;
    }

    /**
     * @return second number of intervening points in LICs 10, 14
     */
    public int fPts() {
        return fPts;
    }

    /**
     * @return number of intervening points in LIC 11
     */
    public int gPts() {
        return gPts;
    }

    /**
     * @return maximum length in LIC 12
     */
    public double length2() {
        return length2;
    }

    /**
     * @return maximum radius in LIC 13
     */
    public double radius2() {
        return radius2;
    }

    /**
     * @return maximum area in LIC 14
     */
    public double area2() {
        return area2;
    }
    /**
     * Builder class used for creating a {@link Parameters} object. If a value
     * is not provided for a certain parameter, then its default value is used.
     *
     * <p>Default values:</p>
     * <ul>
     *     <li>{@link #length1}: 1</li>
     *     <li>{@link #radius1}: 1</li>
     *     <li>{@link #epsilon}: 1</li>
     *     <li>{@link #area1}: 1</li>
     *     <li>{@link #qPts}: 1</li>
     *     <li>{@link #quads}: 1</li>
     *     <li>{@link #dist}: 1</li>
     *     <li>{@link #nPts}: 1</li>
     *     <li>{@link #kPts}: 1</li>
     *     <li>{@link #aPts}: 1</li>
     *     <li>{@link #bPts}: 1</li>
     *     <li>{@link #cPts}: 1</li>
     *     <li>{@link #dPts}: 1</li>
     *     <li>{@link #ePts}: 1</li>
     *     <li>{@link #fPts}: 1</li>
     *     <li>{@link #gPts}: 1</li>
     *     <li>{@link #length2}: 1</li>
     *     <li>{@link #radius2}: 1</li>
     *     <li>{@link #area2}: 1</li>
     * </ul>
     */
    public static final class Builder {

        /**
         * Length in LICs 0, 7, 12.
         */
        private double length1 = 1;

        /**
         * Radius in LICs 1, 8, 13.
         */
        private double radius1 = 1;

        /**
         * Deviation from PI in LICs 2, 9.
         */
        private double epsilon = 1;

        /**
         * Area in LICs 3, 10, 14.
         */
        private double area1 = 1;

        /**
         * Number of consecutive points in LIC 4.
         */
        private int qPts = 1;

        /**
         * Number of quadrants in LIC 4.
         */
        private int quads = 1;

        /**
         * Distance in LIC 6.
         */
        private double dist = 1;

        /**
         * Number of consecutive points in LIC 6.
         */
        private int nPts = 1;

        /**
         * Number of intervening points in LICs 7, 12.
         */
        private int kPts = 1;

        /**
         * First number of intervening points in LICs 8, 13.
         */
        private int aPts = 1;

        /**
         * Second number of intervening points in LICs 8, 13.
         */
        private int bPts = 1;

        /**
         * First number of intervening points in LIC 9.
         */
        private int cPts = 1;

        /**
         * Second number of intervening points in LIC 9.
         */
        private int dPts = 1;

        /**
         * First number of intervening points in LICs 10, 14.
         */
        private int ePts = 1;

        /**
         * Second number of intervening points in LICs 10, 14.
         */
        private int fPts = 1;

        /**
         * Number of intervening points in LIC 11.
         */
        private int gPts = 1;

        /**
         * Maximum length in LIC 12.
         */
        private double length2 = 1;

        /**
         * Maximum radius in LIC 13.
         */
        private double radius2 = 1;

        /**
         * Maximum area in LIC 14.
         */
        private double area2 = 1;

        /**
         * Sets {@link #length1}.
         *
         * @param value length in LICs 0, 7, 12
         * @return this builder instance
         */
        public Builder length1(double value) {
            this.length1 = value;
            return this;
        }

        /**
         * Sets {@link #radius1}.
         *
         * @param value radius in LICs 1, 8, 13
         * @return this builder instance
         */
        public Builder radius1(double value) {
            this.radius1 = value;
            return this;
        }

        /**
         * Sets {@link #epsilon}.
         *
         * @param value deviation from PI in LICs 2, 9
         * @return this builder instance
         */
        public Builder epsilon(double value) {
            this.epsilon = value;
            return this;
        }

        /**
         * Sets {@link #area1}.
         *
         * @param value area in LICs 3, 10, 14
         * @return this builder instance
         */
        public Builder area1(double value) {
            this.area1 = value;
            return this;
        }

        /**
         * Sets {@link #qPts}.
         *
         * @param value number of consecutive points in LIC 4
         * @return this builder instance
         */
        public Builder qPts(int value) {
            this.qPts = value;
            return this;
        }

        /**
         * Sets {@link #quads}.
         *
         * @param value number of quadrants in LIC 4
         * @return this builder instance
         */
        public Builder quads(int value) {
            this.quads = value;
            return this;
        }

        /**
         * Sets {@link #dist}.
         *
         * @param value distance in LIC 6
         * @return this builder instance
         */
        public Builder dist(double value) {
            this.dist = value;
            return this;
        }

        /**
         * Sets {@link #nPts}.
         *
         * @param value number of consecutive points in LIC 6
         * @return this builder instance
         */
        public Builder nPts(int value) {
            this.nPts = value;
            return this;
        }

        /**
         * Sets {@link #kPts}.
         *
         * @param value number of intervening points in LICs 7, 12
         * @return this builder instance
         */
        public Builder kPts(int value) {
            this.kPts = value;
            return this;
        }

        /**
         * Sets {@link #aPts}.
         *
         * @param value first number of intervening points in LICs 8, 13
         * @return this builder instance
         */
        public Builder aPts(int value) {
            this.aPts = value;
            return this;
        }

        /**
         * Sets {@link #bPts}.
         *
         * @param value second number of intervening points in LICs 8, 13
         * @return this builder instance
         */
        public Builder bPts(int value) {
            this.bPts = value;
            return this;
        }

        /**
         * Sets {@link #cPts}.
         *
         * @param value first number of intervening points in LIC 9
         * @return this builder instance
         */
        public Builder cPts(int value) {
            this.cPts = value;
            return this;
        }

        /**
         * Sets {@link #dPts}.
         *
         * @param value second number of intervening points in LIC 9
         * @return this builder instance
         */
        public Builder dPts(int value) {
            this.dPts = value;
            return this;
        }

        /**
         * Sets {@link #ePts}.
         *
         * @param value first number of intervening points in LICs 10, 14
         * @return this builder instance
         */
        public Builder ePts(int value) {
            this.ePts = value;
            return this;
        }

        /**
         * Sets {@link #fPts}.
         *
         * @param value second number of intervening points in LICs 10, 14
         * @return this builder instance
         */
        public Builder fPts(int value) {
            this.fPts = value;
            return this;
        }

        /**
         * Sets {@link #gPts}.
         *
         * @param value number of intervening points in LIC 11
         * @return this builder instance
         */
        public Builder gPts(int value) {
            this.gPts = value;
            return this;
        }

        /**
         * Sets {@link #length2}.
         *
         * @param value maximum length in LIC 12
         * @return this builder instance
         */
        public Builder length2(double value) {
            this.length2 = value;
            return this;
        }

        /**
         * Sets {@link #radius2}.
         *
         * @param value maximum radius in LIC 13
         * @return this builder instance
         */
        public Builder radius2(double value) {
            this.radius2 = value;
            return this;
        }

        /**
         * Sets {@link #area2}.
         *
         * @param value maximum area in LIC 14
         * @return this builder instance
         */
        public Builder area2(double value) {
            this.area2 = value;
            return this;
        }

        /**
         * Builds an immutable {@link Parameters} instance using the values set on
         * this builder, using defaults for any values not explicitly provided.
         *
         * @return a new {@link Parameters} instance
         */
        public Parameters build() {
            return new Parameters(this);
        }
    }
}
