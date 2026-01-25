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
}
