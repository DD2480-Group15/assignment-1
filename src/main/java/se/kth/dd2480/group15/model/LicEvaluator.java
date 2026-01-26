package se.kth.dd2480.group15.model;

import se.kth.dd2480.group15.utils.CompType;
import se.kth.dd2480.group15.utils.Utils;

import java.util.Arrays;

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
    
    /**
     * Checks if there exists at least one set of three 
     * consecutive data points that are the vertices of
     * a triangle with area greater than AREA1. (AREA1 >= 0).
     * 
     * @param numPoints number of coordinates in coords
     * @param coords an array of Point elements
     * @param area1 the value of the area that the coordinates area need to be greater than
     * @return true if the condition is met, false otherwise
     */
    public boolean Lic3(double numPoints, Point[] coords, double area1) {
        // area1 must be equal or greater than 0
        if(area1 < 0){
            return false;
        }

        // need at least 3 coordinates
        if(numPoints >= 3){
            for(int i = 0; i <= (numPoints-3); i++){
                Point p1 = coords[i];
                Point p2 = coords[i+1];
                Point p3 = coords[i+2];

                // get positive area of a triangle from 3 coordinates
                double area = (0.5)*Math.abs(
                    (p1.x() * (p2.y()- p3.y())) + 
                    (p2.x() * (p3.y()- p1.y())) + 
                    (p3.x() * (p1.y()- p2.y())));
                
                // if area > area1 
                if (Utils.doubleCompare(area, area1) == CompType.GT) 
                    return true;
            }
        }
        return false;
    }

    /**
     * Checks whether there exists at least one set of {@code qPts} consecutive data
     * points which lie in more than {@code quads} quadrants.
     *
     * @param numPoints the number of data points in the array
     * @param points an array containing the (x,y) coordinates for each point.
     * @param params a Parameters object containing the {@code qPts} and {@code quads} values for the evaluation.
     * @return {@code true} if there exists at least one set of points that fulfills the
     *         requirements described above; {@code false} otherwise.
     */
    public boolean Lic4(int numPoints, Point[] points, Parameters params) {
        int qPts = params.qPts();
        int quads = params.quads();

        if (qPts < 2 || qPts > numPoints || quads < 1 || quads > 3) return false;

        boolean[] coveredQuads = new boolean[4];
        for (int start = 0; start < numPoints - qPts + 1; start++) {
            Arrays.fill(coveredQuads, false); // "Reset" quadrants

            for (int j = start; j < start + qPts; j++) {
                double x = points[j].x(), y = points[j].y();

                if (x >= 0 && y >= 0) coveredQuads[0] = true;
                else if (x < 0 && y >= 0) coveredQuads[1] = true;
                else if (x <= 0 && y < 0) coveredQuads[2] = true;
                else coveredQuads[3] = true;
            }

            int numCoveredQuads = 0;
            for (boolean quad : coveredQuads) if (quad) numCoveredQuads++;
            if (numCoveredQuads > quads) return true;
        }

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

        if(dist > 0){
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

    public boolean Lic8(int numPoints, Point[] pt, Parameters params) {
        int aPts = params.aPts(), bPts = params.bPts();
        double radius1 = params.radius1();
        if (numPoints < 5 || aPts < 1 || bPts < 1 || aPts+bPts > numPoints-3 || radius1 < 0) { return false; }

        for (int i = 0; i < numPoints-aPts-bPts-2; i++) {
            Point p1 = pt[i], p2 = pt[i+aPts+1], p3 = pt[i + aPts+1 + bPts+1];

            double radius = Utils.getCircleRadius(p1, p2, p3);

            if (radius > radius1) { return true; }
        }

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
        results[1] = Lic1(numpoints, pt, params);
        results[2] = Lic2(numpoints, pt, params);
        results[3] = Lic3(numpoints, pt, params.area1());
        results[4] = Lic4(numpoints, pt, params);
        results[5] = Lic5(numpoints, pt);
        results[6] = Lic6();
        results[7] = Lic7();
        results[8] = Lic8(numpoints, pt, params);
        results[9] = Lic9();
        results[10] = Lic10(numpoints, pt, params);
        results[11] = Lic11(numpoints, pt, params.gPts());
        results[12] = Lic12();
        results[13] = Lic13();
        results[14] = Lic14();
        return results;
    }
}
