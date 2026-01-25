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

    public boolean Lic1() {
        // TODO Implement functionality
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

    public boolean Lic6() {
        // TODO Implement functionality
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

    /**
     * Checks if there exists at least 1 set of 3 data points: separated by exactly e_pts and f_pts
     * consecutive intervening points, respectively, that can create a triangle area greater than area1. 
     * (Condition is not met when numpoints < 5).
     * @param numpoints the number of points in the array (must be at least 5)
     * @param points an array of Point objects representing the coordinates of points 
     * @param params a Parameters object containing the e_pts, f_pts and area1 value for the evaluation
     * @return true if at least one triangle, where the condition is met, is found. False otherwise.
     */
    public boolean Lic10(int numpoints, Point[] pt, Parameters params) {
        int e_pts = params.ePts();
        int f_pts = params.fPts();
        double area1 = params.area1();

        // verify input
        if (!(e_pts >= 1 && f_pts >= 1 && (e_pts + f_pts) <= (numpoints - 3) && numpoints >= 5))
            return false;

        /**
         * We want e_pts between p1 och p2 
         * And at the same time f_pts between p2 and p3 
         */
        for(int i = 0; i < numpoints - (e_pts + f_pts + 2); i++){
            Point p1 = pt[i];
            Point p2 = pt[i + e_pts + 1];
            Point p3 = pt[i + e_pts + f_pts + 2];

            // get positive area of a triangle from 3 coordinates
            double area = (0.5)*Math.abs(
                (p1.x() * (p2.y()- p3.y())) + 
                (p2.x() * (p3.y()- p1.y())) + 
                (p3.x() * (p1.y()- p2.y())));

            // if area > area1 
            if (Utils.doubleCompare(area, area1) == CompType.GT) 
                return true;
            }

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
        results[1] = Lic1();
        results[2] = Lic2(numpoints, pt, params);
        results[3] = Lic3();
        results[4] = Lic4();            
        results[5] = Lic5(numpoints, pt);
        results[6] = Lic6();
        results[7] = Lic7();
        results[8] = Lic8();
        results[9] = Lic9();
        results[10] = Lic10(numpoints, pt, params);
        results[11] = Lic11(numpoints, pt, params.gPts());
        results[12] = Lic12();
        results[13] = Lic13();
        results[14] = Lic14();
        return results;
    }
}
