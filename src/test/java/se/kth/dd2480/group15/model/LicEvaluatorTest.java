package se.kth.dd2480.group15.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class LicEvaluatorTest {

    /**
     * Verifies that {@code Lic0} returns {@code true} when at least one pair 
     * of consecutive points exceeds the specified distance threshold.
     * <p>
     * Test setup: Uses a configuration where the distance (5.0)
     * is strictly greater than the length threshold (4.0).
     * </p>
     */
    @Test
    void lic0_validInput() {
        LicEvaluator evaluator = new LicEvaluator();

        Point[] points = {new Point(1,1), new Point(4,5)};
        double length1 = 3.0;
        int numpoints = 2;

        assertTrue(evaluator.Lic0(numpoints, points, length1));
    }

    /**
     * Verifies that {@code Lic0} returns {@code false} when the distance between
     * consecutive points is less than the specified threshold.
     * <p>
     * Test setup: Points are 5.0 units apart, and the threshold is 6.0.
     * </p>
     */
    @Test
    void lic0_invalidInput() {
        LicEvaluator evaluator = new LicEvaluator();

        Point[] points = {new Point(1,1), new Point(4,5)};
        double length1 = 6.0;
        int numpoints = 2;

        assertFalse(evaluator.Lic0(numpoints, points, length1));
    }

    /**
     * Verifies the "strictly greater than" requirement of LIC0.
     * <p>
     * If the distance is exactly equal to {@code length1}, the condition 
     * should not be met.
     * </p>
     */
    @Test
    void lic0_singlePoint() {
        LicEvaluator evaluator = new LicEvaluator();

        Point[] points = {new Point(1,1)};
        double length1 = 2.0;
        int numpoints = 1;

        assertFalse(evaluator.Lic0(numpoints, points, length1));
    }

    /**
     * Verifies that a negative radius returns false.
     */
    @Test
    void lic1_invalidInput() {
        LicEvaluator evaluator = new LicEvaluator();

        Point[] points = {
            new Point(0,0),
            new Point(1,1)
        };

        Parameters params = Parameters.builder().radius1(-5).build();
        assertFalse(evaluator.Lic1(points.length, points, params));
    }

    /**
     * Verifies that an instance with less than three points returns false.
     */
    @Test
    void lic1_tooFewPoints() {
        LicEvaluator evaluator = new LicEvaluator();

        Point[] points = {
            new Point(0,0),
            new Point(1,2)
        };

        Parameters params = Parameters.builder().radius1(5).build();
        assertFalse(evaluator.Lic1(points.length, points, params));
    }

    /**
     * Verifies that an instance with three consectuive points that are further apart than the
     * given radius returns true.
     */
    @Test
    void lic1_returnsTrue() {
        LicEvaluator evaluator = new LicEvaluator();

        Point[] points = {
            new Point(0,0),
            new Point(1,2),
            new Point(2,8),
            new Point(16,20)
        };

        Parameters params = Parameters.builder().radius1(5).build();
        assertTrue(evaluator.Lic1(points.length, points, params));
    }

    /**
     * Verifies that an instance without three consectuive points that are further apart than the
     * given radius returns false.
     */
    @Test
    void lic1_returnsFalse() {
        LicEvaluator evaluator = new LicEvaluator();

        Point[] points = {
            new Point(0,0),
            new Point(1,2),
            new Point(2,8),
            new Point(16,20)
        };

        Parameters params = Parameters.builder().radius1(100).build();
        assertFalse(evaluator.Lic1(points.length, points, params));
    }

    /**
     * Verifies that LIC2 evaluates to true for an angle that is smaller than pi - epsilon.
     */
    @Test
    void lic2_rightAngleEpsilonPiOverSix_returnsTrue() {
        LicEvaluator evaluator = new LicEvaluator();
        Point[] points = {
                new Point(0, 0),
                new Point(0, 1),
                new Point(1, 1)
        };
        Parameters params = Parameters.builder().epsilon(Math.PI / 6).build();
        assertTrue(evaluator.Lic2(points.length, points, params));
    }

    /**
     * Verifies that LIC2 evaluates to false for an angle that is larger
     * than pi - epsilon and smaller than pi + epsilon.
     */
    @Test
    void lic2_straightAngleEpsilonPiOverTwo_returnsFalse() {
        LicEvaluator evaluator = new LicEvaluator();
        Point[] points = {
                new Point(0, 0),
                new Point(1, 1),
                new Point(2, 2)
        };
        Parameters params = Parameters.builder().epsilon(Math.PI / 2).build();
        assertFalse(evaluator.Lic2(points.length, points, params));
    }

    /**
     * Verifies that LIC2 evaluates to false for an epsilon that is larger than or equal to pi.
     */
    @Test
    void lic2_invalidEpsilon_returnFalse() {
        LicEvaluator evaluator = new LicEvaluator();
        Point[] points = {
                new Point(0, 0),
                new Point(0, 1),
                new Point(1, 1)
        };
        Parameters params = Parameters.builder().epsilon(Math.PI).build();

        assertFalse(evaluator.Lic2(points.length, points, params));
    }

    /**
     * Verifies that LIC3 evaluates to true when 3 consecutive points, create a triangle
     * with area 2.0, is correctly identified as greater than area1 = 1.0.
     */
    @Test
    void lic3_shouldReturnTrueWhenAreaIsGreater() {
        LicEvaluator evaluator = new LicEvaluator();
        // Create right angled triangle with base 2 and height 2 -> area = 2*2/2 = 2
        Point[] points = {
            new Point(0,0),
            new Point(2,0),
            new Point(2,2)
        };
        
        double area1 = 1.0;

        assertTrue(evaluator.Lic3(points.length, points, area1));
    }

    /**
     * Verifies that LIC3 evaluates to false when the triangle area (2.0) is exactly 
     * equal to area1 (2.0), as the requirement is "strictly greater than".
     */
    @Test
    void lic3_shouldReturnFalseWhenAreaIsExactlyEqual() {
        LicEvaluator evaluator = new LicEvaluator();
        // Create right angled triangle with base 2 and height 2 -> area = 2*2/2 = 2
        Point[] points = {
            new Point(0,0),
            new Point(2,0),
            new Point(2,2)
        };

        double area1 = 2.0;

        assertFalse(evaluator.Lic3(points.length, points, area1));

    }

    /**
     * Verifies that LIC3 evaluates to false when three consecutive points are on a straight 
     * line, resulting in an area of 0.0, which can never greater than area1.
     */
    @Test
    void lic3_shoudlReturnFalseWhenPointsAreCollinear(){
        LicEvaluator evaluator = new LicEvaluator();
        // Three points on a straight line
        Point[] points = {
            new Point(0,0),
            new Point(1,0),
            new Point(2,0)
        };

        double area1 = 0.0;

        // 0 > 0 is false
        assertFalse(evaluator.Lic3(points.length, points, area1));
    }

    /**
     * Verifies that LIC4 evaluates to true when there is at least one set of {@code qPts} points
     * that lie in more than {@code quads} quadrants.
     * <p>
     * Test setup: one point in each quadrant, {@code qPts} = 4, {@code quads} = 2
     */
    @Test
    void lic4_pointsInEveryQuad_returnsTrue() {
        LicEvaluator evaluator = new LicEvaluator();
        Point[] points = {
                new Point(1, 1),
                new Point(-1, 1),
                new Point(-1, -1),
                new Point(1, -1)
        };
        Parameters params = Parameters.builder().qPts(4).quads(2).build();
        assertTrue(evaluator.Lic4(points.length, points, params));
    }

    /**
     * Verifies that LIC4 correctly identifies which quadrant a point belongs
     * to, even when there is ambiguity in which quadrant this is.
     * <p>
     * Test setup: three points on the x-axis (two in quadrant 1, one in quadrant 2),
     * {@code qPts} = 3, {@code quads} = 2
     */
    @Test
    void lic4_pointsOnAxes_returnsFalse() {
        LicEvaluator evaluator = new LicEvaluator();
        Point[] points = {
                new Point(0, 0),
                new Point(1, 0),
                new Point(-1, 0),
        };
        Parameters params = Parameters.builder().qPts(3).quads(2).build();
        assertFalse(evaluator.Lic4(points.length, points, params));
    }

    /**
     * Verifies that LIC4 correctly checks the bounds for the value of {@code qPts}
     * when it is larger than the number of points.
     * <p>
     * Test setup: two points, {@code qPts} = 3, {@code quads} = 1
     */
    @Test
    void lic4_qPtsGreaterThanNumPoints_returnsFalse() {
        LicEvaluator evaluator = new LicEvaluator();
        Point[] points = {
                new Point(1, 1),
                new Point(-1, 1)
        };
        Parameters params = Parameters.builder().qPts(3).quads(2).build();
        assertFalse(evaluator.Lic4(points.length, points, params));
    }

    /**
     * Test case where there are two consecutive points with decreasing x-coordinates.
     * Expecting the function to return true
     */
    @Test
    void lic5_returnsTrue() {
        Point[] points = {new Point(1, 2), new Point(3, 4), new Point(2, 5)};
        LicEvaluator evaluator = new LicEvaluator();
        assertTrue(evaluator.Lic5(3, points));
    }

    /**
     * Test case where there are no two consecutive points with decreasing x-coordinates
     * Expecting the function to return false
     */
    @Test
    void lic5_returnsFalse() {
        Point[] points = {new Point(1, 2), new Point(2, 4), new Point(2, 6)};
        LicEvaluator evaluator = new LicEvaluator();
        assertFalse(evaluator.Lic5(3, points));
    }

    /**
     * Test case with only one point.
     * Expecting the function to return false
     */
    @Test
    void lic5_onePoint_returnsFalse() {
        Point[] points = {new Point(5, 2)};
        LicEvaluator evaluator = new LicEvaluator();
        assertFalse(evaluator.Lic5(1, points));
    }


    @Test
    void lic6() {
    }

    @Test
    void lic7() {
    }

    @Test
    void lic8() {
    }

    @Test
    void lic9() {
    }

    @Test
    void lic10() {
    }

    //LIC11
    @Test
    void lic11_returnsTure() {
        // Test case where there are two points separated by G_PTS with decreasing x-coordinates
        Point[] points = {new Point(1, 2), new Point(3, 4), new Point(5, 6), new Point(2, 5), new Point(2, 7)};
        LicEvaluator evaluator = new LicEvaluator();
        assertTrue(evaluator.Lic11(5, points, 2));
    }

    @Test
    void lic11_returnsFalse() {
        // Test case where there are no two points separated by G_PTS with decreasing x-coordinates
        Point[] points = {new Point(1, 2), new Point(2, 4), new Point(1, 5), new Point(4, 6), new Point(5, 7)};
        LicEvaluator evaluator = new LicEvaluator();
        assertFalse(evaluator.Lic11(5, points, 2));
    }

    @Test
    void lic11_GptsOutofRange_returnsFalse() {
        // not meet 1 ≤G_PTS ≤NUMPOINTS−2
        Point[] points = {new Point(1, 2), new Point(3, 4), new Point(5, 6)};
        LicEvaluator evaluator = new LicEvaluator();
        assertFalse(evaluator.Lic11(3, points, 2));
    }

    @Test
    void lic11_numPointsLessThan_returnsFalse() {
        // Test case where NUMPOINTS < 3
        Point[] points = {new Point(1, 2), new Point(3, 4)};
        LicEvaluator evaluator = new LicEvaluator();
        assertFalse(evaluator.Lic11(2, points, 1));
    }

    @Test
    void lic12() {
    }

    @Test
    void lic13() {
    }

    @Test
    void lic14() {
    }

    /**
     * Test the functionality of evaluateLics method by providing a set of points and parameters,
     * and verifying that the returned boolean array has the correct length of 15.
     */
    @Test
    void evaluateLics() {
        LicEvaluator evaluator = new LicEvaluator();
        Point[] points = {new Point(1,1), new Point(4,5)};
        int numpoints = 2;
        Parameters params = Parameters.builder()
                .length1(3) // Set parameter values
                .radius1(1)
                .cPts(2)
                .build();  
        boolean[] results = evaluator.evaluateLics(numpoints, points, params);
        assertTrue(results.length == 15);
    }
}