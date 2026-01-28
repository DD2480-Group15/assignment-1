package se.kth.dd2480.group15;

import org.junit.jupiter.api.Test;
import se.kth.dd2480.group15.model.Connectors;
import se.kth.dd2480.group15.model.Evaluator;
import se.kth.dd2480.group15.model.LicEvaluator;
import se.kth.dd2480.group15.model.Parameters;
import se.kth.dd2480.group15.model.Point;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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
        for (int i = 0; i < 15; i++) 
            for (int j = 0; j < 15; j++) 
            {
                if(i == 6)
                    LCM[i][j] = Connectors.NOTUSED;
                else
                    LCM[i][j] = Connectors.ORR;
            }

        boolean[] PUV = new boolean[15];
        for (int i = 0; i < 15; i++)
            PUV[i] = true;

        //test
        PrintStream originalOut = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));

        Decide.decide(LCM, PUV, coords, params, numPoints); 
        System.setOut(originalOut);  // always restore

        String out = baos.toString().trim();
        assertEquals("YES", out);
    }
}
