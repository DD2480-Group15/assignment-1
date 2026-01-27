package se.kth.dd2480.group15.utils;

import org.junit.jupiter.api.Test;
import se.kth.dd2480.group15.model.Point;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    private static final double TEST_PRECISION = 1e-7;

    private final Point p1 = new Point(0, 0);
    private final Point p3 = new Point(2, 2);

    /**
     * Verifies that a right angle at the vertex is computed correctly.
     */
    @Test
    void angleAtVertex_rightAngle_returnsPiOverTwo() {
        Point vertex = new Point(0, 2);

        double angle = Utils.angleAtVertex(p1, vertex, p3);

        assertEquals(Math.PI / 2, angle, TEST_PRECISION);
    }

    /**
     * Verifies that collinear points forming a straight angle yield pi radians.
     */
    @Test
    void angleAtVertex_straightAngle_returnsPi() {
        Point vertex = new Point(1, 1);

        double angle = Utils.angleAtVertex(p1, vertex, p3);

        assertEquals(Math.PI, angle, TEST_PRECISION);
    }

    /**
     * Verifies that collinear points in the same direction yield an angle of 0 radians.
     */
    @Test
    void angleAtVertex_collinearSameDirection_returnsZero() {
        Point vertex = new Point(3, 3);

        double angle = Utils.angleAtVertex(p1, vertex, p3);

        assertEquals(0.0, angle, TEST_PRECISION);
    }

    /**
     * Verifies that the angle is undefined (method throws exception) when
     * the first point coincides with the vertex.
     */
    @Test
    void angleAtVertex_firstPointCoincidesVertex_throwsException() {
        Point vertex = new Point(0, 0);

        assertThrows(IllegalArgumentException.class,
                () -> Utils.angleAtVertex(p1, vertex, p3));
    }

    /**
     * Verifies that the angle is undefined (method throws exception) when
     * the last point coincides with the vertex.
     */
    @Test
    void angleAtVertex_lastPointCoincidesVertex_throwsException() {
        Point vertex = new Point(2, 2);

        assertThrows(IllegalArgumentException.class,
                () -> Utils.angleAtVertex(p1, vertex, p3));
    }


    /**
     * Verifies that collinear points return -1.
     */
    @Test
    void getCircleRadius_collinearPoints() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(1, 0);
        Point p3 = new Point(2, 0);

        double radius = Utils.getCircleRadius(p1, p2, p3);

        assertEquals(-1, radius, TEST_PRECISION);
    }

    /**
     * Verifies that the correct radius is calculated for a simple example
     */
    @Test 
    void getCircleRadius_simpleExample() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(4, 0);
        Point p3 = new Point(0, 3);

        double radius = Utils.getCircleRadius(p1, p2, p3);
        double expected = 2.5;

        assertEquals(expected, radius, TEST_PRECISION);
    }

    /**
     * Verifies that the correct radius is calculated for a set of points with non-vertical and non-horizontal lines
     */
    @Test
    void getCircleRadius_nonVerticalnonHorizontal() {
        Point p1 = new Point(1,2);
        Point p2 = new Point(4,5);
        Point p3 = new Point(6,3);

        double radius = Utils.getCircleRadius(p1, p2, p3);
        double expected = Math.sqrt((1-3.5)*(1-3.5) + (2-2.5)*(2-2.5));

        assertEquals(expected, radius, TEST_PRECISION);
    }

    /**
     * Verifies that the triangle area is calculated correctly for a right-angled triangle.
     */
    @Test
    void getTriangleArea_standardTriangle_returnsCorrectArea() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(4, 0);
        Point p3 = new Point(0, 3);
        // Area = (4 * 3) / 2 = 6.0
        assertEquals(6.0, Utils.getTriangleArea(p1, p2, p3), 0.00001); //(expected, actual, error margin)
    }

    /**
     * Verifies that the area is zero when all three points lie on the same straight line.
     */
    @Test
    void getTriangleArea_collinearPoints_returnsZero() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(1, 1);
        Point p3 = new Point(2, 2);
        // Area should be exactly 0.0 for collinear points
        assertEquals(0.0, Utils.getTriangleArea(p1, p2, p3), 0.00001); //(expected, actual, error margin)
    }
    
    /**
     * Verifies that allTrue returns true when all elements are true, and false otherwise.
     */
    @Test
    void AllTrue_returnsTrue() {
        boolean [] boolArray = {true, true, true};
        boolean result = Utils.allTrue(boolArray);
        assertTrue(result);
    }

    /**
     * Verifies that allTrue returns false when at least one element is false.
     */
    @Test
    void AllTrue_returnsFalse() {
        boolean [] boolArray = {true, false, true};
        boolean result = Utils.allTrue(boolArray);
        assertFalse(result);
    }
}
