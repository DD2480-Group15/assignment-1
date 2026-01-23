package se.kth.dd2480.group15.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class LicEvaluatorTest {

    @Test
    void lic0_validInput() {
        // Distance between (1,1) and (4,5) is sqrt((4-1)^2 + (5-1)^2) = sqrt(9+16) = 5
        // length1 = 3 and 5 > 3, so should be true
        LicEvaluator evaluator = new LicEvaluator();

        Point[] points = {new Point(1,1), new Point(4,5)};
        double length1 = 3.0;
        int numpoints = 2;

        assertTrue(evaluator.Lic0(numpoints, points, length1));
    }

    @Test
    void lic0_invalidInput() {
        // Distance between (1,1) and (4,5) is sqrt((4-1)^2 + (5-1)^2) = sqrt(9+16) = 5
        // length1 = 6 and 5 < 6, so should be false
        LicEvaluator evaluator = new LicEvaluator();

        Point[] points = {new Point(1,1), new Point(4,5)};
        double length1 = 6.0;
        int numpoints = 2;

        assertFalse(evaluator.Lic0(numpoints, points, length1));
    }

    @Test
    void lic0_singlePoint() {
        // if there is only a single point to evaluate, function should return false and not some error.
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

    @Test
    void lic11() {
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

    @Test
    void evaluateLics() {
    }
}