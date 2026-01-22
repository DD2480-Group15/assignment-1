package se.kth.dd2480.group15.model;

/**
 * Contains values for the different parameters used when evaluating the LICs.
 * 
 * @param LENGTH1 Length in LICs 0, 7, 12
 * @param RADIUS1 Radius in LICs 1, 8, 13
 * @param EPSILON Deviation from PI in LICs 2, 9
 * @param AREA1 Area in LICs 3, 10, 14
 * @param Q_PTS Number of consecutive points in LIC 4
 * @param QUADS Number of quadrants in LIC 4
 * @param DIST Distance in LIC 6
 * @param N_PTS Number of consecutive points in LIC 6
 * @param K_PTS Number of intervening points in LICs 7, 12
 * @param A_PTS First number of intervening points in LICs 8, 13
 * @param B_PTS Second number of intervening points in LICs 8, 13
 * @param C_PTS First number of intervening points in LIC 9
 * @param D_PTS Second number of intervening points in LIC 9
 * @param E_PTS First number of intervening points in LICs 10, 14
 * @param F_PTS Second number of intervening points in LICs 10, 14
 * @param G_PTS No. of intervening points in LIC 11
 * @param LENGTH2 Maximum length in LIC 12
 * @param RADIUS2 Maximum radius in LIC 13
 * @param AREA2 Maximum area in LIC 14
 */
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

