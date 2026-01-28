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
     * @param numPoints The number of data points in the array.
     * @param points        An array containing the (x,y) coordinates for each point.
     * @param params a Parameters object containing the parameter values used for the
     *               evaluation ({@code length1}).
     * @return {@code true} if any two consecutive points are further apart than length1; 
     * {@code false} otherwise.
     */
    public boolean Lic0(int numPoints, Point[] points, Parameters params) {
        double length1 = params.length1();

        for (int i = 0; i < numPoints-1; i++) {
            double dist_x = points[i+1].x() - points[i].x();
            double dist_y = points[i+1].y() - points[i].y();

            double euclidian_dist = Math.sqrt(Math.pow(dist_x, 2) + Math.pow(dist_y, 2));

            if(euclidian_dist > length1){
                return true;
            }
        } 
        return false;
    }

    public boolean Lic1(int numPoints, Point[] points, Parameters params) {
        if (params.radius1() < 0 || numPoints < 3) { return false; }

        for (int i = 0; i < numPoints - 2; i++) {
            Point p1 = points[i], p2 = points[i+1], p3 = points[i+2];

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
     * @param params a Parameters object containing the parameter values used for the
     *               evaluation ({@code epsilon}).
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
     * @param numPoints the number of data points in the array
     * @param points an array of Point elements
     * @param params a Parameters object containing the parameter values used for the
     *               evaluation ({@code area1}).
     * @return true if the condition is met, false otherwise
     */
    public boolean Lic3(double numPoints, Point[] points, Parameters params) {
        double area1 = params.area1();
        // area1 must be equal or greater than 0
        if(area1 < 0){
            return false;
        }

        // need at least 3 coordinates
        if(numPoints >= 3){
            for(int i = 0; i <= (numPoints-3); i++){
                Point p1 = points[i];
                Point p2 = points[i+1];
                Point p3 = points[i+2];

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
     * @param params a Parameters object containing the parameter values used for the
     *               evaluation ({@code qPts}, {@code quads}).
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

    public boolean Lic5(int numPoints, Point[] points) {
        /* 
        Input: n (integer, number of data points), 
               points (arrays of Point containing the x and y coordinates of the data points)

        Output: boolean (true if the condition is met, false otherwise)       
        
        Functionality: There exists at least one set of two consecutive data points, (X[i],Y[i]) and (X[j],Y[j]), such
        that X[j] - X[i] < 0. (where i = j-1)
        */

        for(int i=1; i<numPoints; i++)
            if(points[i].x() - points[i-1].x() < 0)
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
     * @param numPoints The total number of data points.
     * @param points        An array containing the (x,y) coordinates for each point.
     * @param params a Parameters object containing the parameter values used for the
     *               evaluation ({@code nPts}, {@code dist}, {@code epsilon}).
     * @return {@code true} if any point in a set is further than dist from the line 
     * (or from the coincident point if the first and last points are identical); 
     * {@code false} otherwise.
     */
    public boolean Lic6(int numPoints, Point[] points, Parameters params) {
        int nPts = params.nPts();
        double dist = params.dist();

        if(nPts > numPoints || nPts < 3){
            return false;
        } 

        if(dist > 0){
            return false;
        }

        for (int i = 0; i <= numPoints - nPts; i++) {
            Point first_pt = points[i];
            Point last_pt = points[i + nPts - 1];

            for (int j = i+1; j < i + nPts - 1; j++){
                double pt_dist;
                Point current_pt = points[j];

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
     * @param numPoints The total number of data points.
     * @param points        An array containing the (x,y) coordinates for each point.
     * @param params a Parameters object containing the parameter values used for the
     *               evaluation ({@code kPts}, {@code length1}).
     * @return {@code true} if any such pair exists; {@code false} otherwise.
     */
    public boolean Lic7(int numPoints, Point[] points, Parameters params) {
        int kPts = params.kPts();
        double length1 = params.length1();

        if (numPoints < 3){
            return false;
        }

        if (kPts < 1 || kPts > numPoints-2){
            return false;
        }

        for (int i = 0; i < numPoints - kPts - 1; i++){
            Point p1 = points[i];
            Point p2 = points[i + kPts + 1];

            double dist_x = p2.x() - p1.x();
            double dist_y = p2.y() - p1.y();

            double euclidian_dist = Math.sqrt(Math.pow(dist_x, 2) + Math.pow(dist_y, 2));

            if (euclidian_dist > length1){
                return true;
            }
        }   
        return false;
    }

    public boolean Lic8(int numPoints, Point[] points, Parameters params) {
        int aPts = params.aPts(), bPts = params.bPts();
        double radius1 = params.radius1();
        if (numPoints < 5 || aPts < 1 || bPts < 1 || aPts+bPts > numPoints-3 || radius1 < 0) { return false; }

        for (int i = 0; i < numPoints-aPts-bPts-2; i++) {
            Point p1 = points[i], p2 = points[i+aPts+1], p3 = points[i + aPts+1 + bPts+1];

            double radius = Utils.getCircleRadius(p1, p2, p3);

            if (radius > radius1) { return true; }
        }

        return false;
    }

    /**
     * Evaluates whether there exists at least one set of three points separated by exactly
     * {@code cPts} and {@code dPts} consecutive intervening points, which form an angle that
     * is outside the range defined by pi ± {@code epsilon}.
     *
     * @param numPoints the number of points in the array
     * @param points an array containing the (x,y) coordinates for each point.
     * @param params a Parameters object containing the parameter values used for the
     *               evaluation ({@code cPts}, {@code dPts}, {@code epsilon}).
     * @return {@code true} if there exists at least one set of points that fulfills the
     *         requirements described above; {@code false} otherwise.
     */
    public boolean Lic9(int numPoints, Point[] points, Parameters params) {
        int cPts = params.cPts();
        int dPts = params.dPts();

        if (cPts < 1 || dPts < 1 || cPts + dPts > numPoints - 3 || numPoints < 5) return false;

        for (int i = 0; i < numPoints - cPts - dPts - 2; i++) {
            Point[] selectedPoints = new Point[]{
                    points[i],
                    points[i + cPts + 1],
                    points[i + cPts + dPts + 2]
            };
            if (Lic2(selectedPoints.length, selectedPoints, params)) return true;
        }

        return false;
    }

    /**
     * Checks if there exists at least 1 set of 3 data points: separated by exactly e_pts and f_pts
     * consecutive intervening points, respectively, that can create a triangle area greater than area1. 
     * (Condition is not met when numPoints < 5).
     * @param numPoints the number of points in the array (must be at least 5)
     * @param points an array of Point objects representing the coordinates of points 
     * @param params a Parameters object containing the parameter values used for the
     *               evaluation ({@code e_pts}, {@code f_pts}, {@code area1}).
     * @return true if at least one triangle, where the condition is met, is found. False otherwise.
     */
    public boolean Lic10(int numPoints, Point[] points, Parameters params) {
        int e_pts = params.ePts();
        int f_pts = params.fPts();
        double area1 = params.area1();

        // verify input
        if (!(e_pts >= 1 && f_pts >= 1 && (e_pts + f_pts) <= (numPoints - 3) && numPoints >= 5))
            return false;

        /**
         * We want e_pts between p1 och p2 
         * And at the same time f_pts between p2 and p3 
         */
        for(int i = 0; i < numPoints - (e_pts + f_pts + 2); i++){
            Point p1 = points[i];
            Point p2 = points[i + e_pts + 1];
            Point p3 = points[i + e_pts + f_pts + 2];

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
     * @param numPoints the size of the points array (integer)
     * @param points the array of data points (Point[])
     * @param params a Parameters object containing the parameter values used for the
     *               evaluation ({@code gPts}).
     * @return true if the condition is met, false otherwise
    */
    public boolean Lic11(int numPoints, Point[] points, Parameters params) {
        int gPts = params.gPts();
        if(gPts < 1 || gPts > numPoints - 2 || numPoints < 3)
            return false;

        for(int i=0; i<numPoints-gPts-1; i++)
            if(points[i+gPts+1].x() - points[i].x() < 0)
                return true;
        return false;
    }
    
    /**
     * Checks if there exists at least one pair of data points separated by K_PTS 
     * that are a distance greater than LENGTH1 apart, and at least one pair 
     * separated by K_PTS that are a distance less than LENGTH2 apart. Both parts
     * must be true for the LIC to be true.
     * <p>The condition is not met when NUMPOINTS is less than 3.</p>
     *
     * @param numPoints The total number of data points.
     * @param points        An array containing the (x,y) coordinates for each point.
     * @param params a Parameters object containing the parameter values used for the
     *               evaluation ({@code kPts}, {@code length1}, {@code length2}).
     * @return {@code true} if both distance conditions are met; {@code false} otherwise.
     */
    public boolean Lic12(int numPoints, Point[] points, Parameters params) {
        int kPts = params.kPts();
        double length1 = params.length1();
        double length2 = params.length2();

        if(numPoints < 3){
            return false;
        }

        if(length2 < 0){
            return false;
        }

        boolean foundGreater = false;
        boolean foundLess = false;

        for(int i = 0; i < numPoints - kPts - 1; i++){
            Point p1 = points[i];
            Point p2 = points[i + kPts + 1];

            double dist_x = p2.x() - p1.x();
            double dist_y = p2.y() - p1.y();

            double dist = Math.hypot(dist_x, dist_y);

            if(dist > length1){
                foundGreater = true;
            }
            if(dist < length2){
                foundLess = true;
            }

            if(foundGreater && foundLess){
                return true;
            }
        }

        return false;
    }

    /**
     * Checks whether there extists at least one set of three data points, separated by exactly A_PTS and B_PTS
     * consecutive intervening points, respectively, that cannot be contained within or on a circle of
     * radius RADIUS1. In addition, there exists at least one set of three data points (which can be
     * the same or different from the three data points just mentioned) separated by exactly A_PTS
     * and B_PTS consecutive intervening points, respectively, that can be contained in or on a
     * circle of radius RADIUS2. Both parts must be true for the LIC to be true. The condition is
     * not met when NUMPOINTS < 5.
     * 
     * @param numPoints The number of points in the array.
     * @param points the array of data points.
     * @param params a Parameters object containing the parameter values used for the
     *               evaluation ({@code aPts}, {@code bPts}, {@code radius1}, {@code radius2}).
     * @return true if the conditions of LIC13 are satisfied (see above); false otherwise.
     */
    public boolean Lic13(int numPoints, Point[] points, Parameters params) {
        int aPts = params.aPts(), bPts = params.bPts();
        double radius1 = params.radius1();
        double radius2 = params.radius2();
        if (numPoints < 5 || aPts < 1 || bPts < 1 || aPts+bPts > numPoints-3 || radius1 < 0 || radius2 < 0) { return false; }

        boolean foundCan = false;
        boolean foundCannot = false;

        for (int i = 0; i < numPoints-aPts-bPts-2; i++) {
            Point p1 = points[i], p2 = points[i+aPts+1], p3 = points[i + aPts+1 + bPts+1];

            double radius = Utils.getCircleRadius(p1, p2, p3);

            if (radius > radius1) { foundCannot = true; }
            if (radius <= radius2) {foundCan = true; }
        }

        if (foundCan && foundCannot) { return true; }

        return false;
    }

    /**
     * Checks if:
     * 1) There exists at least 1 set of 3 data points, separated by exactly e_pts and f_pts 
     *    consecutive intervening points, respectively, that make up a triangle with area greater than area1.
     * 2) There exists 3 data points (same as in req 1) or different) , separated by exactly 
     *    e_pts and f_pts intervening points, that make up a triangle with area less than area2.
     * Both requirements must be true for function to return true.
     * (The condition is not met if numpoints < 5).
     * @return
     */
    public boolean Lic14(int numPoints, Point[] points, Parameters params) {
        double area1 = params.area1();
        double area2 = params.area2();
        int e_pts = params.ePts();
        int f_pts = params.fPts();

        if(!(numPoints >= 5 && area1 >= 0 && area2 >= 0 && e_pts >= 1 && f_pts >= 1)) return false;
        
        boolean isGreaterThanArea1 = false;
        boolean isLessThanArea2 = false;

        for(int i = 0; i < numPoints - (e_pts + f_pts + 2); i++) {
            Point p1 = points[i];
            Point p2 = points[i + e_pts + 1];
            Point p3 = points[i + e_pts + f_pts + 2];

            double area = Utils.getTriangleArea(p1, p2, p3);
            // check each iteration
            if (Utils.doubleCompare(area, area1) == CompType.GT) isGreaterThanArea1 = true;
            if (Utils.doubleCompare(area, area2) == CompType.LT) isLessThanArea2 = true;
            // as soon as both condition are found
            if (isGreaterThanArea1 && isLessThanArea2) return true;
        }

        return false;
    }
    
    /**
    * Returns an array of boolean values indicating whether each of the 15 LICs is satisfied.
    *
    * @param  numPoints the number of data points
    * @param  points        an array of Point objects representing the data points
     * @param params a Parameters object containing the parameter values used for LIC evaluation.
    * @return      an array of boolean values where each index corresponds to a LIC (0-14)
    */
    public boolean[] evaluateLics(int numPoints, Point[] points, Parameters params) {
        boolean results[] = new boolean[15];
        results[0] = Lic0(numPoints, points, params);
        results[1] = Lic1(numPoints, points, params);
        results[2] = Lic2(numPoints, points, params);
        results[3] = Lic3(numPoints, points, params);
        results[4] = Lic4(numPoints, points, params);
        results[5] = Lic5(numPoints, points);
        results[6] = Lic6(numPoints, points, params);
        results[7] = Lic7(numPoints, points, params);
        results[8] = Lic8(numPoints, points, params);
        results[9] = Lic9(numPoints, points, params);
        results[10] = Lic10(numPoints, points, params);
        results[11] = Lic11(numPoints, points, params);
        results[12] = Lic12(numPoints, points, params);
        results[13] = Lic13(numPoints, points, params);
        results[14] = Lic14(numPoints, points, params);
        return results;
    }
}
