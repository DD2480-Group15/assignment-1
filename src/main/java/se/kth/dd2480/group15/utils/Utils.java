package se.kth.dd2480.group15.utils;

import se.kth.dd2480.group15.model.Point;

/**
 * Collection of static utility methods used when checking LICs truth values.
 */
public class Utils {

    private final static double PRECISION = 0.000001;

    /**
     * Calculates the angle (in radians) formed at point vertex by the line segments vertex-p1 and vertex-p3.
     * The angle is calculated using the dot product formula and returns a value between 0 and π.
     *
     * @param p1 the first point that defines the first line segment.
     * @param vertex the vertex point at which the angle is formed.
     * @param p3 the third point that defines the second line segment.
     * @return the angle in radians formed at point vertex.
     * @throws IllegalArgumentException if p1 and/or p3 coincides with vertex.
     */
    public static double angleAtVertex(Point p1, Point vertex, Point p3) {
        if (p1.equals(vertex) || p3.equals(vertex)) {
            throw new IllegalArgumentException("Points p1 and/or p3 cannot coincide with vertex.");
        }

        double v1x = p1.x() - vertex.x();
        double v1y = p1.y() - vertex.y();
        double v1len = Math.sqrt(v1x * v1x + v1y * v1y);

        double v2x = p3.x() - vertex.x();
        double v2y = p3.y() - vertex.y();
        double v2len = Math.sqrt(v2x * v2x + v2y * v2y);

        double dot = v1x * v2x + v1y * v2y;

        return Math.acos(dot / (v1len * v2len));
    }

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

    /**
     * Computes the radius of the circle passing through all three given points.
     * 
     * @param p1 The first point.
     * @param p2 The second point.
     * @param p3 The third point.
     * @return the radius of the circle constructed by the three points or -1 if 
     * no such circle can be constructed.
     */
    public static double getCircleRadius(Point p1, Point p2, Point p3) {
        // Get all coordinates
        double x1 = p1.x(), y1 = p1.y();
        double x2 = p2.x(), y2 = p2.y();
        double x3 = p3.x(), y3 = p3.y();

        // Get all square lengths
        double s1 = x1*x1 + y1*y1;
        double s2 = x2*x2 + y2*y2;
        double s3 = x3*x3 + y3*y3;

        // Right-hand side of Cramer's rule
        double bc = (s1 - s2) / 2.0;
        double cd = (s2 - s3) / 2.0;

        // Check collinearity
        double det = (x1-x2) * (y2-y3) - (x2-x3) * (y1-y2);
        if (Math.abs(det) < PRECISION) { return -1; } // No circle can be found

        // Find the center coordinates of the circle (Cramer's rule)
        double centerx = (bc * (y2-y3) - cd * (y1-y2)) / det;
        double centery = (cd * (x1-x2) - bc * (x2-x3)) / det;

        // Find the radius of the circle
        double radius = Math.sqrt( (x2-centerx)*(x2-centerx) + (y2-centery)*(y2-centery) );

        return radius;
    }
}
