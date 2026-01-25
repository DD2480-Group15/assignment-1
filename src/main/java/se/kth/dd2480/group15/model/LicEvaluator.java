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

    /**
     * Checks if there exists at least one set of N_PTS consecutive data points such that 
     * at least one point lies a distance greater than DIST from the line joining 
     * the first and last of these N_PTS points.
     *
     * If the first and last points are identical, the distance is calculated as the 
     * Euclidian distance between the coincident point to all other consecutive points.
     * Otherwise if not identical, the distance from a point (x0, y0)  to the line 
     * Ax + By + C = 0 is calculated using the point-to-plane formula:
     * 
     * distance = |A*x0 + B*y0 + C| / sqrt(A^2 + B^2)
     * 
     * The condition is not met if NUMPOINTS is less than 3.
     * 
     * @param numpoints The total number of data points.
     * @param pt        An array containing the (x,y) coordinates for each point.
     * @param nPts      The number of consecutive points to evaluate (3 <= nPts <= numpoints).
     * @param dist      The threshold distance (must be non-negative).
     * @return {@code true} if any point in a set is further than dist from the line 
     * (or from the coincident point if the first and last points are identical); 
     * {@code false} otherwise.
     */
    public boolean Lic6(int numpoints, Point[] pt, int nPts, double dist) { 
        if(nPts > numpoints || nPts < 3){
            return false;
        } 

        for (int i = 0; i <= numpoints - nPts; i++) {
            Point first_pt = pt[i];
            Point last_pt = pt[i + nPts - 1];  

            for (int j = i+1; j < i + nPts - 1; j++){
                double pt_dist;
                Point current_pt = pt[j];

                if (first_pt.x() == last_pt.x() && first_pt.y() == last_pt.y()){
                            double dist_x = current_pt.x() - first_pt.x();
                            double dist_y = current_pt.y() - first_pt.y();

                            pt_dist = Math.sqrt(Math.pow(dist_x, 2) + Math.pow(dist_y, 2));
                        }

                else{
                    double dy = last_pt.y() - first_pt.y();
                    double dx = last_pt.x() - first_pt.x();
                    double constant = (last_pt.x() * first_pt.y()) - (last_pt.y() * first_pt.x());
                    
                    double num = Math.abs(dy * current_pt.x() + dx * current_pt.y() + constant);
                    double denom = Math.sqrt(Math.pow(dy,2) + Math.pow(dx, 2));

                    pt_dist = num / denom;
                }

                if(pt_dist > dist){
                    return true;
                }         

            }

        }
        return false;
    }

    public boolean Lic7() {
        // TODO Implement functionality
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
