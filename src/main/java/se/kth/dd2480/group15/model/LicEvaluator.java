package se.kth.dd2480.group15.model;

public class LicEvaluator {

    public static final double PI = 3.1415926535;

    public boolean Lic0() {
        // TODO Implement functionality
        return false;
    }

    public boolean Lic1() {
        // TODO Implement functionality
        return false;
    }

    public boolean Lic2() {
        // TODO Implement functionality
        return false;
    }

    public boolean Lic3() {
        // TODO Implement functionality
        return false;
    }

    public boolean Lic4() {
        // TODO Implement functionality
        return false;
    }

    public boolean Lic5(int numpoints, Point[] pt) {
        /* 
        Input: n (integer, number of data points), 
               pt (arrays of Point containing the x and y coordinates of the data points)

        Output: boolean (true if the condition is met, false otherwise)       
        
        Functionality: There exists at least one set of two consecutive data points, (X[i],Y[i]) and (X[j],Y[j]), such
        that X[j] - X[i] < 0. (where i = j-1)
        */

        for(int i=1; i<numpoints; i++)
            if(pt[i].x() - pt[i-1].x() < 0)
                return true;
        return false;
    }

    public boolean Lic6() {
        // TODO Implement functionality
        return false;
    }

    public boolean Lic7() {
        // TODO Implement functionality
        return false;
    }

    public boolean Lic8() {
        // TODO Implement functionality
        return false;
    }

    public boolean Lic9() {
        // TODO Implement functionality
        return false;
    }

    public boolean Lic10() {
        // TODO Implement functionality
        return false;
    }

    public boolean Lic11(int numpoints, Point[] pt, int g) {
        /* 
        Input: n (integer, number of data points), 
               pt (arrays of Point containing the x and y coordinates of the data points)
               g (integer, G_PTS)

        Output: boolean (true if the condition is met, false otherwise)       
        
        Functionality: There exists at least one set of two data points, (X[i],Y[i]) and (X[j],Y[j]), separated by
        exactly G_PTS consecutive intervening points, such that X[j] - X[i] < 0. (where i < j ) The
        condition is not met when NUMPOINTS < 3.
        1 ≤G_PTS ≤NUMPOINTS−2
        */

        if(g < 1 || g > numpoints - 2 || numpoints < 3)
            return false;

        for(int i=0; i<numpoints-g; i++)
            if(pt[i+g].x() - pt[i].x() < 0)
                return true;
        return false;
    }

    public boolean Lic12() {
        // TODO Implement functionality
        return false;
    }

    public boolean Lic13() {
        // TODO Implement functionality
        return false;
    }

    public boolean Lic14() {
        // TODO Implement functionality
        return false;
    }

    public boolean[] evaluateLics() {
        // TODO Implement functionality
        return new  boolean[0];
    }
}
