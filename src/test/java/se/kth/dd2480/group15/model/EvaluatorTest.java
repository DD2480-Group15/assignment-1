package se.kth.dd2480.group15.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static se.kth.dd2480.group15.utils.Utils.allTrue;

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

    /**
     * Verifies that {@code evaluateFUV} returns {@code true} when the
     * Preliminary Unlocking Vector (PUV) has the corresponding LIC set to {@code false},
     * regardless of the Preliminary Unlocking Matrix (PUM) values.
     * <p>
     * Test setup: PUM array is set to all {@code false}. PUV array is set such that
     * all LICs are {@code false}.
     * Expected outcome: FUV[0] should be {@code true} since PUV[0] is {@code false}.
     * </p>
     */
    @Test
    void evaluateFUV_PUVfalse_ReturnsTrue() {
        Evaluator evaluator = new Evaluator();
        boolean[][] PUM = new boolean[15][15];
        boolean[] PUV = new boolean[15];
        for(int i=0; i<15; i++){
            PUV[i] = false;
            for(int j=0; j<15; j++){
                PUM[i][j] = false;
            }
        }
        boolean[] FUV = evaluator.evaluateFUV(PUM, PUV);
        assertTrue(FUV[0]);
    }

    /**
     * Verifies that {@code evaluateFUV} returns {@code true} when the
     * Preliminary Unlocking Vector (PUV) has the corresponding LIC set to {@code true},
     * and all values in the corresponding row of the Preliminary Unlocking Matrix (PUM) are {@code true}.
     * <p>
     * Test setup: PUM array is set to all {@code true}. PUV array is set such that
     * the first row is {@code true} and the rest are {@code false}.
     * Expected outcome: FUV[0] should be {@code true} since PUV[0] is {@code true} and all PUM[0][j] are {@code true}.
     * </p>
     */
    @Test
    void evaluateFUV_PUVtrueAllPUMTrue_ReturnsTrue() {
        Evaluator evaluator = new Evaluator();
        boolean[][] PUM = new boolean[15][15];
        boolean[] PUV = new boolean[15];
        for(int i=0; i<15; i++)
            PUM[0][i] = true;    

        PUV[0] = true;  
        for(int i=1; i<15; i++){
            PUV[i] = true;
            for(int j=0; j<15; j++){
                PUM[i][j] = false;
            }
        }
        boolean[] FUV = evaluator.evaluateFUV(PUM, PUV);
        assertTrue(FUV[0]);
    }

    /**
     * Verifies that {@code evaluateFUV} returns {@code false} when the
     * Preliminary Unlocking Vector (PUV) has the corresponding LIC set to {@code true},
     * and one of the values in the corresponding row of the Preliminary Unlocking Matrix (PUM) are {@code false}.
     * <p>
     * Test setup: PUM array is set to all {@code true}. PUV array is set such that
     * the first row is {@code true} and the rest are {@code false}.
     * Expected outcome: FUV[1] should be {@code false} since PUV[1] is {@code true} and all PUM[1][j] are {@code false}.
     * </p>
     */
    @Test
    void evaluateFUV_PUVtrueAllPUMFalse_ReturnsFalse() {
        Evaluator evaluator = new Evaluator();
        boolean[][] PUM = new boolean[15][15];
        boolean[] PUV = new boolean[15];
        for(int i=0; i<15; i++)
            PUM[0][i] = true;    
        
        PUV[0] = true;  
        for(int i=1; i<15; i++){
            PUV[i] = true;
            for(int j=0; j<15; j++)
                PUM[i][j] = false;
        }
        boolean[] FUV = evaluator.evaluateFUV(PUM, PUV);
        assertFalse(FUV[1]);
    }

    /**
     * Verifies that {@code evaluateLAUNCH} returns {@code true} when the
     * Final Unlocking Vector (FUV) has all elements set to {@code true}.
     * <p>
     * Test setup: FUV array is set to all {@code true}.
     * Expected outcome: Launch should be {@code true}.
     * </p>
     */
    @Test
    void evaluateLAUNCH_ReturnsTrue() {
        Evaluator evaluator = new Evaluator();
        boolean[] FUV = new boolean[15];
        for(int i=0; i<15; i++)
            FUV[i] = true;    
        
        boolean launch = evaluator.evaluateLAUNCH(FUV);
        assertTrue(launch);
    }

    /**
     * Verifies that {@code evaluateLAUNCH} returns {@code false} when the
     * Final Unlocking Vector (FUV) has one of the elements set to {@code false}.
     * <p>
     * Test setup: FUV array is set to {@code true} for odd indices and {@code false} for even indices.
     * Expected outcome: Launch should be {@code false}.
     * </p>
     */
    @Test
    void evaluateLAUNCH_ReturnsFalse() {
        Evaluator evaluator = new Evaluator();
        boolean[] FUV = new boolean[15];
        for(int i=0; i<15; i++){
            if(i%2 == 0)
                FUV[i] = false;    
            else
                FUV[i] = true; 
        }
        
        boolean launch = evaluator.evaluateLAUNCH(FUV);
        assertFalse(launch);
    }
}