package se.kth.dd2480.group15;

import se.kth.dd2480.group15.model.Connectors;
import se.kth.dd2480.group15.model.Evaluator;
import se.kth.dd2480.group15.model.LicEvaluator;
import se.kth.dd2480.group15.model.Parameters;
import se.kth.dd2480.group15.model.Point;
import se.kth.dd2480.group15.utils.Utils;


public class Decide {

    public static void main(String[] args) {
        // Example of how to use the builder pattern
        Parameters params = Parameters.builder() // Retrieve builder
                .length1(3) // Set parameter values
                .radius1(1)
                .cPts(2)
                .build(); // Build the Parameter object using declared values (and default values)

        // Coordinates of data points
        Point[] coords = new Point[]{
                new Point(0, 0),
                new Point(1, 0),
                new Point(0, 1),
                new Point(1, -1),
                new Point(-1, -1)
        };

        // Number of data points
        int numPoints = coords.length;

        // get the LCM value by console input
        Connectors[][] LCM = new Connectors[15][15];

        // Preliminary Unlocking Vector
        boolean[] PUV = new boolean[15];

        decide(LCM, PUV, coords, params, numPoints);
    }

    public static void decide(Connectors[][] LCM, boolean[] PUV, Point[] coords, Parameters params, int numPoints) {
        // Conditions Met Vector
        boolean[] CMV = new boolean[15];
        LicEvaluator evaluator = new LicEvaluator();
        CMV = evaluator.evaluateLics(numPoints, coords, params);

         // Preliminary Unlocking Matrix
        boolean[][] PUM = new boolean[15][15];
        Evaluator evaluator2 = new Evaluator();
        PUM = evaluator2.evaluatePUM(LCM, CMV);

        // Final Unlocking Vector
        boolean[] FUV = new boolean[15];
        FUV = evaluator2.evaluateFUV(PUM, PUV);

        // Decision: Launch or No Launch
        boolean launch = evaluator2.evaluateLAUNCH(FUV);
        if(launch) 
            System.out.println("YES");
        else 
            System.out.println("NO");
    }

}