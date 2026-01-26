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

    /**
     * Verifies that {@code Lic6} returns {@code true} when at least one point in a set 
     * of {@code nPts} lies further than {@code dist} from the line connecting 
     * the first and last points.
     * <p>
     * Test setup: Points (0,0) and (4,0) form a horizontal line. The intermediate 
     * point (2,3) is 3.0 units away perpendicularly, which is greater than dist (2.0).
     * </p>
     */
    @Test
    void lic6_validInput() {
        LicEvaluator evaluator = new LicEvaluator();

        Point[] points = {new Point(0,0), new Point(2,3), new Point(4,0)};
        int numpoints = 3;
        int nPts = 3;
        double dist = 2.0;

        assertTrue(evaluator.Lic6(numpoints, points, nPts, dist));
    }

    /**
     * Verifies that {@code Lic6} returns {@code false} when all intermediate points 
     * lie within the {@code dist} threshold from the line joining the endpoints.
     * <p>
     * Test setup: Intermediate point (2,1) is only 1.0 unit away from the line 
     * between (0,0) and (4,0). Threshold (dist) is set to 2.0.
     * </p>
     */
    void lic6_invalidInput() {
        LicEvaluator evaluator = new LicEvaluator();

        Point[] points = {new Point(0,0), new Point(2,1), new Point(4,0)};
        int numpoints = 3;
        int nPts = 3;
        double dist = 2.0;

        assertFalse(evaluator.Lic6(numpoints, points, nPts, dist));
    }

    /**
     * Verifies that {@code Lic6} returns {@code false} when {@code numpoints}
     * is less than 3, as specified by the requirements.
     * <p>
     * Test setup: Only 2 points are provided, making it impossible to form
     * the set required.
     * </p>
     */
    void lic6_numpoints_lessThanThree() {
        LicEvaluator evaluator = new LicEvaluator();

        Point[] points = {new Point(0,0), new Point(5,5)};
        int numpoints = 2;
        int nPts = 3;
        double dist = 2.0;

        assertFalse(evaluator.Lic6(numpoints, points, nPts, dist));
    }

    /**
     * Verifies that {@code Lic7} returns {@code true} when at least one pair of
     * data points separated by {@code kPts} exceeds the length threshold.
     * <p>
     * Test setup: Points (0,0) and (5,0) are separated by 1 intervening point.
     * The distance (5.0) is strictly greater than {@code length1} 4.0.
     * </p>
     */
    @Test
    void lic7_validInput() {
        LicEvaluator evaluator = new LicEvaluator();

        Point[] points = {new Point(0,0), new Point(1,1), new Point(5,0)};
        int numpoints = 3;
        int kPts = 1;
        double length1 = 4.0;

        assertTrue(evaluator.Lic7(numpoints, points, kPts, length1));
    }

    /**
     * Verifies that {@code Lic7} returns {@code false} when the distance between
     * points separated by {@code kPts} is less than the threshold.
     * <p>
     * Test setup: Points (0,0) and (3,0) are 3.0 units apart. The threshold, i.e.
     * {@code length1} is 4.0, which is greater than 3.0.
     * </p>
     */
    @Test
    void lic7_invalidInput() {
        LicEvaluator evaluator = new LicEvaluator();

        Point[] points = {new Point(0,0), new Point(1,1), new Point(3,0)};
        int numpoints = 3;
        int kPts = 1;
        double length1 = 4.0;

        assertFalse(evaluator.Lic7(numpoints, points, kPts, length1));
    }

    /**
     * Verifies that {@code Lic7} returns {@code false} when {@code numpoints}
     * is less than 3, as specified by the requirements.
     * <p>
     * Test setup: Only 2 points are provided, making it impossible to form the
     * set required.
     * </p>
     */
    @Test
    void lic7_numpointsLessThanThree() {
        LicEvaluator evaluator = new LicEvaluator();

        Point[] points = {new Point(0,0), new Point(5,5)};
        int numpoints = 2;
        int kPts = 1;
        double length1 = 1.0;

        assertFalse(evaluator.Lic7(numpoints, points, kPts, length1));
    }

    /**
     * Verifies that LIC8 returns false when a negative value for bPts is given.
     */
    @Test
    void lic8_invalidInput() {
        LicEvaluator evaluator = new LicEvaluator();

        Point[] points = {
            new Point(0,0),
            new Point(1,2),
            new Point(1, 0),
            new Point(3,1),
            new Point(5,9),
            new Point(4,1)
        };

        Parameters params = Parameters.builder().radius1(5).aPts(2).bPts(-1).build();
        assertFalse(evaluator.Lic8(points.length, points, params));
    }

    /**
     * Verifies that LIC8 returns false when less then aPts+bPts+3 points are given.
     */
    @Test
    void lic8_tooFewPoints() {
        LicEvaluator evaluator = new LicEvaluator();

        Point[] points = {
            new Point(0,0),
            new Point(1,2),
            new Point(3,1),
            new Point(5,9)
        };

        Parameters params = Parameters.builder().radius1(100).aPts(4).bPts(4).build();
        assertFalse(evaluator.Lic8(points.length, points, params));
    }

    /**
     * Verifies that an instance that does not satisfy the conditions of LIC8 returns false.
     */
    @Test
    void lic8_returnsFalse() {
        LicEvaluator evaluator = new LicEvaluator();

        Point[] points = {
            new Point(0,0),
            new Point(1,2),
            new Point(3,1),
            new Point(5,9),
            new Point(5,6),
            new Point(7,2),
            new Point(6,3),
            new Point(2,5),
            new Point(9,0)
        };

        Parameters params = Parameters.builder().radius1(6).aPts(2).bPts(2).build();
        assertFalse(evaluator.Lic8(points.length, points, params));
    }

    /**
     * Verifies that an instance that satisfies the conditions of LIC8 returns true.
     */
    @Test
    void lic8_returnsTrue() {
        LicEvaluator evaluator = new LicEvaluator();

        Point[] points = {
            new Point(0,0),
            new Point(1,2),
            new Point(1, 0),
            new Point(3,1),
            new Point(5,9),
            new Point(1,4)
        };

        Parameters params = Parameters.builder().radius1(1.5).aPts(2).bPts(1).build();
        assertTrue(evaluator.Lic8(points.length, points, params));
    }

    @Test
    void lic9() {
    }

    /**
     * Positive test case
     * 3 points separated correctly with minimum Point array size
     * Expecting the function to return true
     */
    @Test
    void lic10_threePointsSeparatedCorrectly_minimumDataPoints_returnsTrue() {
        Parameters params = Parameters.builder().ePts(1).fPts(1).area1(1.0).build();
        Point[] points = {
            new Point(0,0), // p1
            new Point(0,0),
            new Point(2,0), // p2
            new Point(0,0),
            new Point(0,2), // p3
        };
        LicEvaluator evaluator = new LicEvaluator();
        assertTrue(evaluator.Lic10(points.length, points, params));
    }

    /**
     * Positive test case 
     * 3 points separated correctly
     * Expecting the function to return true
     */
    @Test
    void lic10_threePointsSeparatedCorrectly_returnsTrue() {
        Parameters params = Parameters.builder().ePts(1).fPts(2).area1(1.0).build();
        Point[] points = {
            new Point(0,0), // p1
            new Point(0,0),
            new Point(2,0), // p2
            new Point(0,0),
            new Point(0,0),
            new Point(0,2), // p3
        };
        LicEvaluator evaluator = new LicEvaluator();
        assertTrue(evaluator.Lic10(points.length, points, params));
    }

    /**
     * Positive test case 
     * 3 points separated correctly later in the points array
     * Expecting the function to return true
     */
    @Test
    void lic10_validTriangleLaterInArray_returnsTrue() {
        Parameters params = Parameters.builder().ePts(1).fPts(1).area1(1.0).build();
        Point[] points = {
            new Point(0,0), // ignore
            new Point(0,0), // ignore
            new Point(0,0), // p1
            new Point(0,0),
            new Point(2,0), // p2
            new Point(0,0),
            new Point(0,2), // p3
        };
        LicEvaluator evaluator = new LicEvaluator();
        assertTrue(evaluator.Lic10(points.length, points, params));
    }

    /**
     * Negative test case 
     * 3 points exists but separated incorrectly
     * Expecting the function to return false
     */
    @Test 
    void lic10_threePointsSeparatedIncorrectly_returnsFalse() {
        Parameters params = Parameters.builder().ePts(1).fPts(2).area1(1.0).build();
        Point[] points = {
            new Point(0,0), // p1
            new Point(0,0),
            new Point(2,0), // p2
            new Point(0,0),
            new Point(0,0),
            new Point(1,0), // p3
            new Point(0,2), 
        };
        LicEvaluator evaluator = new LicEvaluator();
        assertFalse(evaluator.Lic10(points.length, points, params));
    }

    /**
     * Negative test case 
     * ePts set to zero
     * Expecting the function to return false
     */
    @Test 
    void lic10_eptsOutOfRange_returnsFalse() {
        Parameters params = Parameters.builder().ePts(0).fPts(2).area1(1.0).build();
        Point[] points = {
            new Point(0,0), // p1
            new Point(2,0), // p2
            new Point(0,0),
            new Point(0,0),
            new Point(0,0), // p3
        };
        LicEvaluator evaluator = new LicEvaluator();
        assertFalse(evaluator.Lic10(points.length, points, params));
    }

    /**
     * Negative test case 
     * points array is too small
     * Expecting the function to return false
     */
    @Test 
    void lic10_pointsArraySizeTooSmall_returnsFalse() {
        Parameters params = Parameters.builder().ePts(1).fPts(1).area1(1.0).build();
        Point[] points = {
            new Point(0,0), // p1
            new Point(2,0), 
            new Point(0,0), // p2
            new Point(0,2),
        };
        LicEvaluator evaluator = new LicEvaluator();
        assertFalse(evaluator.Lic10(points.length, points, params));
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

    /**
     * Verifies that {@code Lic12} returns {@code true} when there is at least one 
     * pair separated by {@code kPts} further than {@code length1} and another 
     * pair further than {@code kPts} closer than {@code length2}. Both parts
     * must be true for the condition to be met.
     * <p>
     * Test setup: Pair (0,0)-(5,0) is dist 5.0 (>4.0), and pair (1,1)-(2,1) 
     * is dist 1.0 (<2.0). Both conditions are satisfied.
     * </p>
     */
    @Test
    void lic12_validInput() {
        LicEvaluator evaluator = new LicEvaluator();

        Point[] points = {new Point(0,0), new Point(1,1), new Point(5,0), new Point(2,1)};
        int numpoints = 4;
        int kPts = 1;
        double length1 = 4.0;
        double length2 = 2.0;

        assertTrue(evaluator.Lic12(numpoints, points, kPts, length1, length2));
    }

    /**
     * Verifies that {@code Lic12} returns {@code false} when only one of the 
     * two required distance conditions is satisfied.
     * <p>
     * Test setup: All pairs have a distance of 5.0. While 5.0 is greater than 
     * {@code length1} (4.0), no pair is less than {@code length2} (2.0).
     * </p>
     */
    @Test
    void lic12_invalidInput() {
        LicEvaluator evaluator = new LicEvaluator();

        Point[] points = {new Point(0,0), new Point(0,0), new Point(5,0), new Point(5,0)};
        int numpoints = 4;
        int kPts = 1;
        double length1 = 4.0;
        double length2 = 2.0;

        assertFalse(evaluator.Lic12(numpoints, points, kPts, length1, length2));

    }

    /**
     * Verifies that {@code Lic12} returns {@code false} when {@code numpoints}
     * is less than 3, as specified by the requirements.
     * <p>
     * Test setup: Only 2 points are provided, making it impossible to form the
     * set required.
     * </p>
     */
    @Test
    void lic12_numpointsLessThanThree() {
        LicEvaluator evaluator = new LicEvaluator();

        Point[] points = {new Point(0,0), new Point(5,5)};
        int numpoints = 2;
        int kPts = 1;
        double length1 = 1.0;
        double length2 = 10.0;

        assertFalse(evaluator.Lic12(numpoints, points, kPts, length1, length2));
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