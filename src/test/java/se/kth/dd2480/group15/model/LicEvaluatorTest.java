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

    @Test
    void lic3() {
    }

    @Test
    void lic4() {
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
    void lic11_returnsFalse(){
        // Test case where there are no two points separated by G_PTS with decreasing x-coordinates
        Point[] points = {new Point(1, 2), new Point(2, 4), new Point(1, 5), new Point(4, 6), new Point(5, 7)}; 
        LicEvaluator evaluator = new LicEvaluator();    
        assertFalse(evaluator.Lic11(5, points, 2));
    }

    @Test 
    void lic11_GptsOutofRange_returnsFalse(){
        // not meet 1 ≤G_PTS ≤NUMPOINTS−2
        Point[] points = {new Point(1, 2), new Point(3, 4), new Point(5, 6)};
        LicEvaluator evaluator = new LicEvaluator();
        assertFalse(evaluator.Lic11(3, points, 2));
    }

    @Test 
    void lic11_numPointsLessThan_returnsFalse(){
        // Test case where NUMPOINTS < 3
        Point[] points = {new Point(1, 2), new Point(3, 4)};
        LicEvaluator evaluator = new LicEvaluator();
        assertFalse(evaluator.Lic11(2, points, 1));
    }

    @Test
    void lic12() {
    }

    /**
     * Verifies that a negative radius2 returns false.
     */
    @Test
    void lic13_invalidInput() {
        LicEvaluator evaluator = new LicEvaluator();

        Point[] points = {
            new Point(0,0),
            new Point(1,2),
            new Point(2,8),
            new Point(16,20),
            new Point(4,8),
            new Point(1,4),
            new Point(9,0)
        };

        Parameters params = Parameters.builder().radius1(9).radius2(-80).aPts(2).bPts(2).build();
        assertFalse(evaluator.Lic13(points.length, points, params));
    }

    /**
     * Verifies that an instance with too few points returns false.
     */
    @Test
    void lic13_tooFewPoints() {
        LicEvaluator evaluator = new LicEvaluator();

        Point[] points = {
            new Point(0,0),
            new Point(1,2),
            new Point(2,8),
            new Point(16,20),
            new Point(4,8),
            new Point(1,4),
            new Point(9,0)
        };

        Parameters params = Parameters.builder().radius1(9).radius2(8).aPts(8).bPts(2).build();
        assertFalse(evaluator.Lic13(points.length, points, params));
    }

    /**
     * Verifies that LIC13 returns true when given an instance that fulfills the requirements.
     */
    @Test 
    void lic13_returnsTrue() {
        LicEvaluator evaluator = new LicEvaluator();

        Point[] points = {
            new Point(0,0),
            new Point(1,2),
            new Point(2,4),
            new Point(16,20),
            new Point(4,8),
            new Point(1,4),
            new Point(3,4)
        };

        Parameters params = Parameters.builder().radius1(3).radius2(5).aPts(1).bPts(2).build();
        assertTrue(evaluator.Lic13(points.length, points, params));
    }

    /**
     * Verifies that LIC13 returns false when given an instance that does not fulfill both requirements.
     */
    @Test
    void lic13_returnsFalse() {
        LicEvaluator evaluator = new LicEvaluator();

        Point[] points = {
            new Point(0,0),
            new Point(1,2),
            new Point(2,4),
            new Point(16,20),
            new Point(4,8),
            new Point(1,4),
            new Point(3,4)
        };

        Parameters params = Parameters.builder().radius1(3).radius2(1).aPts(1).bPts(2).build();
        assertFalse(evaluator.Lic13(points.length, points, params));
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