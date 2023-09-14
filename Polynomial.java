import java.lang.Math;

public class Polynomial{
    double[] coeffs;

    public Polynomial(){
        coeffs = new double[1];
        coeffs[0] = 0;
    }

    public Polynomial(double[] input){
        int len = input.length;
        coeffs = new double[len];
        for (int i = 0; i < len; i++){
            coeffs[i] = input[i];
        }

    }

    public Polynomial add(Polynomial poly){
        //need to consider 2 cases: same length polynomials, diff size polynomials
        int longerLen;
        int shorterLen;

        if (this.coeffs.length > poly.coeffs.length){
            longerLen = this.coeffs.length;
            shorterLen = poly.coeffs.length;
        }
        else{
            longerLen = poly.coeffs.length;
            shorterLen = this.coeffs.length;
        }

        double[] sum = new double[longerLen];
        int j;
        for (j = 0; j < shorterLen; j++){
            sum[j] = coeffs[j] + poly.coeffs[j];
        }
        if (j == poly.coeffs.length){
            while(j < longerLen){
                sum[j] = coeffs[j];
                j++;
            }
        }
        while(j < longerLen){
            sum[j] = poly.coeffs[j];
            j++;
        }
        Polynomial soln = new Polynomial(sum);
        return soln;
    }

    public double evaluate(double num){
        double soln = coeffs[0];
        for(int j = 1; j < coeffs.length; j++){
            soln += coeffs[j]*Math.pow(num, j);
        }
        return soln;
    }

    public boolean hasRoot(double isRoot){
        return evaluate(isRoot) == 0;
    }

}