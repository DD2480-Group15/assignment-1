package se.kth.dd2480.group15.model;

public record Parameters(
        // Length in LICs 0, 7, 12
        double LENGTH1,

        // Radius in LICs 1, 8, 13
        double RADIUS1,

        // Deviation from PI in LICs 2, 9
        double EPSILON,

        // Area in LICs 3, 10, 14
        double AREA1,

        // No. of consecutive points in LIC 4
        int Q_PTS,

        // No. of quadrants in LIC 4
        int QUADS,

        // Distance in LIC 6
        double DIST,

        // No. of consecutive pts. in LIC 6
        int N_PTS,

        // No. of int. pts. in LICs 7, 12
        int K_PTS,

        // No. of int. pts. in LICs 8, 13
        int A_PTS,

        // No. of int. pts. in LICs 8, 13
        int B_PTS,

        // No. of int. pts. in LIC 9
        int C_PTS,

        // No. of int. pts. in LIC 9
        int D_PTS,

        // No. of int. pts. in LICs 10, 14
        int E_PTS,

        // No. of int. pts. in LICs 10, 14
        int F_PTS,

        // No. of int. pts. in LIC 11
        int G_PTS,

        // Maximum length in LIC 12
        double LENGTH2,

        // Maximum radius in LIC 13
        double RADIUS2,

        // Maximum area in LIC 14
        double AREA2
) {}

