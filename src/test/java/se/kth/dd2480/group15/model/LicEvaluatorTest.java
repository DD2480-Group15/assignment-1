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

    @Test
    void lic1() {
    }

    @Test
    void lic2() {
    }

    @Test
    void lic3() {
    }

    @Test
    void lic4() {
    }

    @Test
    void lic5() {
        // Test case where there are two consecutive points with decreasing x-coordinates
        Point[] points = {new Point(1, 2), new Point(3, 4), new Point(2, 5)};
        LicEvaluator evaluator = new LicEvaluator();
        assertTrue(evaluator.Lic5(3, points));

        // Test case where there are no two consecutive points with decreasing x-coordinates
        Point[] points2 = {new Point(1, 2), new Point(2, 4), new Point(5, 6)};
        assertFalse(evaluator.Lic5(3, points2));

        // Test case with only one point
        Point[] points3 = {new Point(5, 2)};
        assertFalse(evaluator.Lic5(1, points3));
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

    @Test
    void evaluateLics() {
    }
}