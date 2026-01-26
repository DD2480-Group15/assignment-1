package se.kth.dd2480.group15.model;

import se.kth.dd2480.group15.utils.CompType;
import se.kth.dd2480.group15.utils.Utils;

import static se.kth.dd2480.group15.utils.Utils.doubleCompare;

public class LicEvaluator {

    public static final double PI = 3.1415926535;

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
    public boolean Lic0(int numpoints, Point[] pt, double length1) {

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

    public boolean Lic1(int numPoints, Point[] pt, Parameters params) {
        if (params.radius1() < 0 || numPoints < 3) { return false; }

        for (int i = 0; i < numPoints - 2; i++) {
            Point p1 = pt[i], p2 = pt[i+1], p3 = pt[i+2];

            double radius = Utils.getCircleRadius(p1, p2, p3);

            if (radius > params.radius1()) { return true; }
        }
        return false;
    }

    /**
     * Evaluates whether there exists at least one set of three consecutive data points which
     * form an angle that is outside the range defined by pi ± epsilon.
     *
     * @param numPoints the number of points in the array (must be at least 3)
     * @param points an array of Point objects representing the coordinates of points
     * @param params a Parameters object containing the epsilon value for the evaluation
     * @return true if at least one set of three consecutive points form an angle outside pi ± epsilon; false otherwise
     */
    public boolean Lic2(int numPoints, Point[] points, Parameters params) {
        double epsilon = params.epsilon();
        if (epsilon < 0 || epsilon >= PI || numPoints < 3) return false;

        for (int i = 0; i < numPoints - 2; i++) {
            Point p1 = points[i];
            Point vertex = points[i + 1];
            Point p3 = points[i + 2];

            try {
                double angle = Utils.angleAtVertex(p1, vertex, p3);
                if (doubleCompare(angle, PI - epsilon) == CompType.LT ||
                    doubleCompare(angle, PI + epsilon) == CompType.GT) return true;
            } catch (IllegalArgumentException _) {
            }
        }

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
    
    /**
    * Returns an array of boolean values indicating whether each of the 15 LICs is satisfied.
    *
    * @param  numpoints the number of data points
    * @param  pt        an array of Point objects representing the data points
    * @param  params    a Parameters object containing the necessary parameters for LIC evaluation
    * @return      an array of boolean values where each index corresponds to a LIC (0-14)
    */
    public boolean[] evaluateLics(int numpoints, Point[] pt, Parameters params) {
        boolean results[] = new boolean[15];
        results[0] = Lic0(numpoints, pt, params.length1());
        results[1] = Lic1(numpoints, pt, params);
        results[2] = Lic2(numpoints, pt, params);
        results[3] = Lic3();
        results[4] = Lic4();            
        results[5] = Lic5(numpoints, pt);
        results[6] = Lic6();
        results[7] = Lic7();
        results[8] = Lic8();
        results[9] = Lic9();
        results[10] = Lic10();
        results[11] = Lic11(numpoints, pt, params.gPts());
        results[12] = Lic12();
        results[13] = Lic13();
        results[14] = Lic14();
        return results;
    }
}
