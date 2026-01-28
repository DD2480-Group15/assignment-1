package se.kth.dd2480.group15;

import org.junit.jupiter.api.Test;
import se.kth.dd2480.group15.model.Connectors;
import se.kth.dd2480.group15.model.LicEvaluator;
import se.kth.dd2480.group15.model.Parameters;
import se.kth.dd2480.group15.model.Point;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class DecideTest {

    /**
     * Verify that the decide function outputs "YES" for a specific configuration.
     * </p>
     * Configuration:
     * Points: (0,0), (5,0), (2,2), (0,5), (-1,1), (-1,-1), (0,-5), (5,-5), (10,0), (0,0)
     * Parameters: length1=1, radius1=0.1, epsilon=0.1, area1=0.1, quads=1, dist=0.1, length2=100, radius2=100, area2=100, qPts=2, nPts=3,
     * other parameters keep as default value 1.
     * LCM: All ORR except row 6 which is NOTUSED
     * PUV: All true
     * </p>
     * Expected Output: "YES"
     */
    @Test
    void decide_outputYES() {
        //configuration
        Point[] coords = new Point[]{
                new Point(0, 0),
                new Point(5, 0),
                new Point(2, 2),
                new Point(0, 5),
                new Point(-1, 1),
                new Point(-1, -1),
                new Point(0, -5),
                new Point(5, -5),
                new Point(10, 0),
                new Point(0, 0)
        };
        int numPoints = coords.length; //10

        Parameters params = Parameters.builder()
                .length1(1)
                .radius1(0.1)
                .epsilon(0.1)
                .area1(0.1)
                .quads(1)
                .dist(0.1)
                .length2(100)
                .radius2(100)
                .area2(100)
                .qPts(2)
                .nPts(3)
                .build();

        Connectors[][] LCM = new Connectors[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if(i == 6)
                    LCM[i][j] = Connectors.NOTUSED;
                else
                    LCM[i][j] = Connectors.ORR;
            }
        }

        boolean[] PUV = new boolean[15];
        for (int i = 0; i < 15; i++) PUV[i] = true;

        //test
        String decision = Decide.decide(LCM, PUV, coords, params, numPoints);

        assertEquals("YES", decision);
    }

    /**
     * Verifies that decide returns "YES" for the provided test input.
     * <p>
     * Test setup:
     * <bl>
     *     <li>Set of 5 data points: {@code (0,0)}, {@code (2,0)}, {@code (0,2)}, {@code (-1,-1)}, {@code (2,0)}.</li>
     *     <li>LIC evaluation parameters such that each LIC evaluates to true for the given points.</li>
     *     <li>LCM with all connectors set to {@code ANDD} to enforce all LICs to be true for evaluation.</li>
     *     <li>PUV with all values set to {@code true}, to ensure every LIC is used.</li>
     * </bl>
     */
    @Test
    void decide_useEveryLicWithAnd_returnYes() {
        Point[] points = new Point[]{
                new Point(0, 0),
                new Point(2, 0), // LIC 0 with p0
                new Point(0, 2), // LIC 1, 2, 3, 6 with p0, p1, LIC 5 with p1, LIC 7, 12 with p0
                new Point(-1, -1), // LIC 4 with the previous point, LIC11 with p1
                new Point(2, 0), // LIC 8, 9, 10, 13, 14 with p0, p2
        };

        int numPoints = points.length;

        Parameters params = Parameters.builder()
                .length1(1)
                .radius1(0.1)
                .epsilon(Math.PI / 6)
                .area1(1)
                .qPts(2)
                .quads(1)
                .nPts(3)
                .dist(1)
                .kPts(1)
                .aPts(1)
                .bPts(1)
                .cPts(1)
                .dPts(1)
                .ePts(1)
                .fPts(1)
                .gPts(1)
                .length2(3)
                .radius2(5)
                .area2(4)
                .build();

        Connectors[][] LCM = new Connectors[15][15];
        for (int i = 0; i < 15; i++) {
            Arrays.fill(LCM[i], Connectors.ANDD);
        }

        boolean[] PUV = new boolean[15];
        Arrays.fill(PUV, true);

        String decision = Decide.decide(LCM, PUV, points, params, numPoints);

        assertEquals("YES", decision);
    }
}
