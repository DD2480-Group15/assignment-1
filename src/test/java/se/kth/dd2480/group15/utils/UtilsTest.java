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
     * Verifies that doubleCompare returns EQ when the two numbers are equal within precision.
     */
    @Test
    void doubleCompare_equals_returnsEQ() {
        double a = 1.0000001;
        double b = 1.0000000;

        assertEquals(CompType.EQ, Utils.doubleCompare(a, b));
    }

    /**
     * Verifies that doubleCompare returns LT when the first number is smaller than the second.
     */
    @Test
    void doubleCompare_lessThan_returnsLT() {
        double a = 1.0000000;
        double b = 2.0000000;

        assertEquals(CompType.LT, Utils.doubleCompare(a, b));
    }

    /**
     * Verifies that doubleCompare returns GT when the first number is greater than the second.
     */
    @Test
    void doubleCompare_greaterThan_returnsGT() {
        double a = 3.0000000;
        double b = 2.0000000;

        assertEquals(CompType.GT, Utils.doubleCompare(a, b));
    }

    /**
     * Verifies that doubleCompare identifies numbers as not equal when their difference exceeds precision.
     */
    @Test
    void doubleCompare_notEqualWithSlightDifference() {
        double a = 1.000002;
        double b = 1.000000;

        assertEquals(CompType.GT, Utils.doubleCompare(a, b));
    }

    /**
     * Verifies that collinear points return the correct radius.
     */
    @Test
    void getCircleRadius_collinearPoints() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(2, 0);
        Point p3 = new Point(6, 0);

        double radius = Utils.getCircleRadius(p1, p2, p3);
        double expected = 3;

        assertEquals(expected, radius, TEST_PRECISION);
    }

    /**
     * Verifies that the correct radius is calculated for a set of points that make a right triangle.
     */
    @Test 
    void getCircleRadius_rightTriangle() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(4, 0);
        Point p3 = new Point(0, 3);

        double radius = Utils.getCircleRadius(p1, p2, p3);
        double expected = 2.5;

        assertEquals(expected, radius, TEST_PRECISION);
    }

    /**
     * Verifies that the correct radius is calculated for a set of points that make an acute triangle.
     */
    @Test
    void getCircleRadius_acuteTriangle() {
        Point p1 = new Point(0,4);
        Point p2 = new Point(-2,0);
        Point p3 = new Point(2,0);

        double radius = Utils.getCircleRadius(p1, p2, p3);
        double expected = 2.5;

        assertEquals(expected, radius, TEST_PRECISION);
    }

    /**
     * Verifies that the correct radius is calculated for a set of points that make an obtuse triangle.
     */
    @Test
    void getCircleRadius_obtuseTriangle() {
        Point p1 = new Point(1,4);
        Point p2 = new Point(2,4);
        Point p3 = new Point(3,3);

        double radius = Utils.getCircleRadius(p1, p2, p3);
        double expected = Math.sqrt((3-1)*(3-1) + (3-4)*(3-4)) / 2;

        assertEquals(expected, radius, TEST_PRECISION);
    }

    /**
     * Verifies that the correct radius is calculated if all three points coincide.
     */
    @Test
    void getCircleRadius_threePointsCoincide() {
        Point p1 = new Point(1,4);
        Point p2 = new Point(1,4);
        Point p3 = new Point(1,4);

        double radius = Utils.getCircleRadius(p1, p2, p3);
        double expected = 0;

        assertEquals(expected, radius, TEST_PRECISION);
    }

     /**
     * Verifies that the correct radius is calculated if two points coincide.
     */
    @Test
    void getCircleRadius_twoPointsCoincide() {
        Point p1 = new Point(1,4);
        Point p2 = new Point(1,4);
        Point p3 = new Point(5,2);

        double radius = Utils.getCircleRadius(p1, p2, p3);
        double expected = Math.sqrt((5-1)*(5-1) + (2-4)*(2-4)) / 2;

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
        assertEquals(6.0, Utils.getTriangleArea(p1, p2, p3), TEST_PRECISION); //(expected, actual, error margin)
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
        assertEquals(0.0, Utils.getTriangleArea(p1, p2, p3), TEST_PRECISION); //(expected, actual, error margin)
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
