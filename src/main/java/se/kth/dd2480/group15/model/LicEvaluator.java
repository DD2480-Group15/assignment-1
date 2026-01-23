package se.kth.dd2480.group15.model;

public class LicEvaluator {

    public static final double PI = 3.1415926535;

    public boolean Lic0(int numpoints, Point[] pt, double length1) {
        /*
        Input: 
            n (integer, the number of data points)
            pt (array, contains (x,y) coordinates for each data point)
            length1 (double, a given length)
        
        Output: 
            boolean (true if condition is met, false otherwise)

        Functionality: 
            Checks if there exists at least one set of two consecutive data points that are a distance greater than the length, LENGTH1, apart.
        */

        for (int i = 0; i < numpoints-1; i++) {
            double dist_x = pt[i+1].x() - pt[i].x();
            double dist_y = pt[i+1].y() - pt[i].y();

            double euclidian_dist = Math.sqrt(Math.pow(dist_x, 2) + Math.pow(dist_y, 2));

            if(euclidian_dist > length1){
                return true;
            }
        } 
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

    public boolean Lic11() {
        // TODO Implement functionality
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
