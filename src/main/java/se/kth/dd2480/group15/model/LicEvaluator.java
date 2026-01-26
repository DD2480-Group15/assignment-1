package se.kth.dd2480.group15.model;

public class LicEvaluator {

    public static final double PI = 3.1415926535;

    public boolean Lic0(int numpoints, Point[] pt, double length1) {
        /**
         * Checks if there exists at least one set of two consecutive data points 
         * that are a distance greater than the specified length.
         *
         * @param numpoints The number of data points in the array.
         * @param pt        An array containing the (x,y) coordinates for each point.
         * @param length1   The threshold distance (must be non-negative).
         * @return {@code true} if any two consecutive points are further apart than length1; 
         * {@code false} otherwise.
         */

        for (int i = 0; i < numpoints-1; i++) {
            double dist_x = pt[i+1].x() - pt[i].x();
            double dist_y = pt[i+1].y() - pt[i].y();

            double euclidian_dist = Math.sqrt(Math.pow(dist_x, 2) + Math.pow(dist_y, 2));

            if(euclidian_dist > length1){
                return true;
            }
        } 
        return false;
    }

    public boolean Lic1() {
        // TODO Implement functionality
        return false;
    }

    public boolean Lic2() {
        // TODO Implement functionality
        return false;
    }

    public boolean Lic3() {
        // TODO Implement functionality
        return false;
    }

    public boolean Lic4() {
        // TODO Implement functionality
        return false;
    }

    public boolean Lic5(int numpoints, Point[] pt) {
        /* 
        Input: n (integer, number of data points), 
               pt (arrays of Point containing the x and y coordinates of the data points)

        Output: boolean (true if the condition is met, false otherwise)       
        
        Functionality: There exists at least one set of two consecutive data points, (X[i],Y[i]) and (X[j],Y[j]), such
        that X[j] - X[i] < 0. (where i = j-1)
        */

        for(int i=1; i<numpoints; i++)
            if(pt[i].x() - pt[i-1].x() < 0)
                return true;
        return false;
    }

    public boolean Lic6() {
        // TODO Implement functionality
        return false;
    }

    /**
     * Checks if there exists at least one set of two data points separated by 
     * exactly K_PTS consecutive intervening points that are a distance 
     * greater than the specified LENGTH1.
     * <p>The condition is not met when NUMPOINTS is less than 3.</p>
     *
     * @param numpoints The total number of data points.
     * @param pt        An array containing the (x,y) coordinates for each point.
     * @param kPts      The number of intervening points (1 <= kPts <= numpoints-2).
     * @param length1   The threshold distance (length1 >= 0).
     * @return {@code true} if any such pair exists; {@code false} otherwise.
     */
    public boolean Lic7(int numpoints, Point[] pt, int kPts, double length1) {
        if (numpoints < 3){
            return false;
        }

        if (kPts < 1 || kPts > numpoints-2){
            return false;
        }

        for (int i = 0; i < numpoints - kPts - 1; i++){
            Point p1 = pt[i];
            Point p2 = pt[i + kPts + 1];

            double dist_x = p2.x() - p1.x();
            double dist_y = p2.y() - p1.y();

            double euclidian_dist = Math.sqrt(Math.pow(dist_x, 2) + Math.pow(dist_y, 2));

            if (euclidian_dist > length1){
                return true;
            }
        }   
        return false;
    }

    public boolean Lic8() {
        // TODO Implement functionality
        return false;
    }

    public boolean Lic9() {
        // TODO Implement functionality
        return false;
    }

    public boolean Lic10() {
        // TODO Implement functionality
        return false;
    }

    /**
     * There exists at least one set of two data points, (X[i],Y[i]) and (X[j],Y[j]), separated by
     * exactly G PTS consecutive intervening points, such that X[j] - X[i] < 0. (where i < j ) The
     * condition is not met when NUMPOINTS < 3.
     * 1 ≤G PTS ≤NUMPOINTS−2
     *
     * @param numpoints the size of the points array (integer)
     * @param pt the array of data points (Point[])
     * @param g the number of intervening points (integer)
     * @return true if the condition is met, false otherwise
    */
    public boolean Lic11(int numpoints, Point[] pt, int g) {

        if(g < 1 || g > numpoints - 2 || numpoints < 3)
            return false;

        for(int i=0; i<numpoints-g-1; i++)
            if(pt[i+g+1].x() - pt[i].x() < 0)
                return true;
        return false;
    }

    public boolean Lic12() {
        // TODO Implement functionality
        return false;
    }

    public boolean Lic13() {
        // TODO Implement functionality
        return false;
    }

    public boolean Lic14() {
        // TODO Implement functionality
        return false;
    }

    public boolean[] evaluateLics() {
        // TODO Implement functionality
        return new  boolean[0];
    }
}
