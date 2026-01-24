package se.kth.dd2480.group15.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class EvaluatorTest {

    /**
     * Verifies that {@code evaluatePUM} returns {@code false} when the
     * Logical Connector Matrix (LCM) contains an ANDD connector between two
     * LICs, and the Conditions Met Vector (CMV) has at least one of the corresponding
     * LICs set to {@code false}.
     * <p>
     * Test setup: LCM array is set to all ANDD connectors. CMV array is set such that
     * even-indexed LICs are {@code false} and odd-indexed LICs are {@code true}.
     * Expected outcome: PUM[0][2] should be {@code false} since both CMV[0] and CMV[2] are {@code false}.
     * </p>
     */
    @Test
    void evaluatePUM_ANDD2False_ReturnsFalse() {
        Evaluator evaluator = new Evaluator();
        Connectors[][] LCM = new Connectors[15][15];
        for(int i=0; i<15; i++)
            for(int j=0; j<15; j++)
                LCM[i][j] = Connectors.ANDD;

        boolean[] CMV = new boolean[15];
        for(int i=0; i<15; i++){
            if(i%2 == 0)
                CMV[i] = false;
            else
                CMV[i] = true;
        }

        boolean[][] PUM = evaluator.evaluatePUM(LCM, CMV);

        assertFalse(PUM[0][2]);
    }

    /**
     * Verifies that {@code evaluatePUM} returns {@code true} when the
     * Logical Connector Matrix (LCM) contains an ANDD connector between two
     * LICs, and the Conditions Met Vector (CMV) has both of the corresponding
     * LICs set to {@code true}.
     * <p>
     * Test setup: LCM array is set to all ANDD connectors. CMV array is set such that
     * even-indexed LICs are {@code false} and odd-indexed LICs are {@code true}.
     * Expected outcome: PUM[1][3] should be {@code true} since both CMV[1] and CMV[3] are {@code true}.
     * </p>
     */
    @Test
    void evaluatePUM_ANDD2True_ReturnsTrue() {
        Evaluator evaluator = new Evaluator();
        Connectors[][] LCM = new Connectors[15][15];
        for(int i=0; i<15; i++)
            for(int j=0; j<15; j++)
                LCM[i][j] = Connectors.ANDD;

        boolean[] CMV = new boolean[15];
        for(int i=0; i<15; i++){
            if(i%2 == 0)
                CMV[i] = false;
            else
                CMV[i] = true;
        }

        boolean[][] PUM = evaluator.evaluatePUM(LCM, CMV);

        assertTrue(PUM[1][3]);
    }

    /**
     * Verifies that {@code evaluatePUM} returns {@code false} when the
     * Logical Connector Matrix (LCM) contains an ANDD connector between two
     * LICs, and the Conditions Met Vector (CMV) has one of the corresponding
     * LICs set to {@code true} and the other set to {@code false}.
     * <p>
     * Test setup: LCM array is set to all ANDD connectors. CMV array is set such that
     * even-indexed LICs are {@code false} and odd-indexed LICs are {@code true}.
     * Expected outcome: PUM[0][1] should be {@code false} since CMV[0] is {@code false} and CMV[1] is {@code true}.
     * </p>
     */
    @Test
    void evaluatePUM_ANDD1True1False_ReturnsFalse() {
        Evaluator evaluator = new Evaluator();
        Connectors[][] LCM = new Connectors[15][15];
        for(int i=0; i<15; i++)
            for(int j=0; j<15; j++)
                LCM[i][j] = Connectors.ANDD;

        boolean[] CMV = new boolean[15];
        for(int i=0; i<15; i++){
            if(i%2 == 0)
                CMV[i] = false;
            else
                CMV[i] = true;
        }

        boolean[][] PUM = evaluator.evaluatePUM(LCM, CMV);

        assertFalse(PUM[0][1]);
    }

    /**
     * Verifies that {@code evaluatePUM} returns {@code false} when the
     * Logical Connector Matrix (LCM) contains an ORR connector between two
     * LICs, and the Conditions Met Vector (CMV) has none of the corresponding
     * LICs set to {@code false}.
     * <p>
     * Test setup: LCM array is set to all ORR connectors. CMV array is set such that
     * even-indexed LICs are {@code false} and odd-indexed LICs are {@code true}.
     * Expected outcome: PUM[0][2] should be {@code false} since both CMV[0] and CMV[2] are {@code false}.
     * </p>
     */
    @Test
    void evaluatePUM_ORR2False_ReturnsFalse() {
        Evaluator evaluator = new Evaluator();
        Connectors[][] LCM = new Connectors[15][15];
        for(int i=0; i<15; i++)
            for(int j=0; j<15; j++)
                LCM[i][j] = Connectors.ORR;

        boolean[] CMV = new boolean[15];
        for(int i=0; i<15; i++){
            if(i%2 == 0)
                CMV[i] = false;
            else
                CMV[i] = true;
        }

        boolean[][] PUM = evaluator.evaluatePUM(LCM, CMV);

        assertFalse(PUM[0][2]);
    }

    /**
     * Verifies that {@code evaluatePUM} returns {@code true} when the
     * Logical Connector Matrix (LCM) contains an ORR connector between two
     * LICs, and the Conditions Met Vector (CMV) has at least one of the corresponding
     * LICs set to {@code true}.
     * <p>
     * Test setup: LCM array is set to all ORR connectors. CMV array is set such that
     * even-indexed LICs are {@code false} and odd-indexed LICs are {@code true}.
     * Expected outcome: PUM[1][3] should be {@code true} since both CMV[1] and CMV[3] are {@code true}.
     * </p>
     */
    @Test
    void evaluatePUM_ORR2True_ReturnsTrue() {
        Evaluator evaluator = new Evaluator();
        Connectors[][] LCM = new Connectors[15][15];
        for(int i=0; i<15; i++)
            for(int j=0; j<15; j++)
                LCM[i][j] = Connectors.ORR;

        boolean[] CMV = new boolean[15];
        for(int i=0; i<15; i++){
            if(i%2 == 0)
                CMV[i] = false;
            else
                CMV[i] = true;
        }

        boolean[][] PUM = evaluator.evaluatePUM(LCM, CMV);

        assertTrue(PUM[1][3]);
    }

    /**
     * Verifies that {@code evaluatePUM} returns {@code true} when the
     * Logical Connector Matrix (LCM) contains an ORR connector between two
     * LICs, and the Conditions Met Vector (CMV) has at least one of the corresponding
     * LICs set to {@code true}.
     * <p>
     * Test setup: LCM array is set to all ORR connectors. CMV array is set such that
     * even-indexed LICs are {@code false} and odd-indexed LICs are {@code true}.
     * Expected outcome: PUM[0][1] should be {@code true} since CMV[0] is {@code false} and CMV[1] is {@code true}.
     * </p>
     */
    @Test
    void evaluatePUM_ORR1True1False_ReturnsTrue() {
        Evaluator evaluator = new Evaluator();
        Connectors[][] LCM = new Connectors[15][15];
        for(int i=0; i<15; i++)
            for(int j=0; j<15; j++)
                LCM[i][j] = Connectors.ORR;

        boolean[] CMV = new boolean[15];
        for(int i=0; i<15; i++){
            if(i%2 == 0)
                CMV[i] = false;
            else
                CMV[i] = true;
        }

        boolean[][] PUM = evaluator.evaluatePUM(LCM, CMV);

        assertTrue(PUM[0][1]);
    }

    /**
     * Verifies that {@code evaluatePUM} returns {@code true} when the
     * Logical Connector Matrix (LCM) contains an NOTUSED connector between two
     * LICs regardless of the Conditions Met Vector (CMV) values.
     * <p>
     * Test setup: LCM array is set to all NOTUSED connectors. CMV array is set such that
     * even-indexed LICs are {@code false} and odd-indexed LICs are {@code true}.
     * Expected outcome: PUM[0][2] should be {@code true} since the LCM contains a NOTUSED connector.
     * </p>
     */
    @Test
    void evaluatePUM_NOTUSED_ReturnsTrue() {
        Evaluator evaluator = new Evaluator();
        Connectors[][] LCM = new Connectors[15][15];
        for(int i=0; i<15; i++)
            for(int j=0; j<15; j++)
                LCM[i][j] = Connectors.NOTUSED;

        boolean[] CMV = new boolean[15];
        for(int i=0; i<15; i++){
            if(i%2 == 0)
                CMV[i] = false;
            else
                CMV[i] = true;
        }

        boolean[][] PUM = evaluator.evaluatePUM(LCM, CMV);

        assertTrue(PUM[0][2]);
    }

}