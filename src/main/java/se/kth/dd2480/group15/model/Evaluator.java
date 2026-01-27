package se.kth.dd2480.group15.model;

import se.kth.dd2480.group15.utils.Utils;

public class Evaluator {

    public static final double PI = 3.1415926535;

    /**
     * Compute the Preliminary Unlocking Matrix (PUM) array value based on the Logical Connector Matrix (LCM) and the Conditions Met Vector (CMV).
     * PUM[i,j] is set according to the result of this operation. 
     * If LCM[i,j] is NOTUSED, then PUM[i,j] should be set to true. 
     * If LCM[i,j] is ANDD, PUM[i,j] should be set to true only if (CMV[i] AND CMV[j]) is true. 
     * If LCM[i,j] is ORR, PUM[i,j] should be set to true if (CMV[i] OR CMV[j]) is true.
     * 
     * @param numPoints The number of data points in the array.
     * @param LCM       A 2D connector array defining the logical connectors between LICs. LCM[i][j] defines the connector between LIC i and LIC j.
     * @param CMV       A boolean array indicating which LICs are true.
     * @return A 2D boolean array representing the Preliminary Unlocking Matrix (PUM), where PUM[i][j] is determined based on LCM[i][j] and CMV.
     */
    public boolean[][] evaluatePUM(Connectors[][] LCM, boolean[] CMV) {

        boolean[][] PUM = new boolean[15][15];  
        for(int i=0; i<15; i++){
            for(int j=0; j<15; j++){
                if(LCM[i][j] == Connectors.ANDD)
                    PUM[i][j] = CMV[i] && CMV[j];
                else if(LCM[i][j] == Connectors.ORR)
                    PUM[i][j] = CMV[i] || CMV[j];

                else if(LCM[i][j] == Connectors.NOTUSED){
                    PUM[i][j] = true;
                }
            }
        }

        return PUM;
    }   

    /**
     * Compute the Final Unlocking Vector (FUV) based on the Preliminary Unlocking Matrix (PUM) and the Preliminary Unlocking Vector (PUV).
     * FUV[i] is set to true if PUV[i] is false or if all values in row i of PUM are true. Otherwise, FUV[i] is set to false.
     * 
     * @param PUM       A 2D boolean array representing the Preliminary Unlocking Matrix (PUM).
     * @param PUV       A boolean array representing the Preliminary Unlocking Vector (PUV).
     * @return A 1D boolean array representing the Final Unlocking Vector (FUV).
     */
    public boolean[] evaluateFUV(boolean[][] PUM, boolean[] PUV) {
        boolean[] FUV = new boolean[15];
        for(int i=0; i<15; i++){
            if(PUV[i] == false || Utils.allTrue(PUM[i]))
                FUV[i] = true;
            else
                FUV[i] = false;
        }
        return FUV;
    }   
}