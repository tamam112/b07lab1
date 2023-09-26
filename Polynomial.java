import java.lang.Math;

public class Polynomial {

    double[] coeffs;
    int[] expons;

    public Polynomial(){
        coeffs = new double[1];
        expons = new int[1];
        coeffs[0] = 0;
        expons[0] = 0;
    }

    public Polynomial(double[] cos, int[] exps){
        coeffs = new double[cos.length];
        expons = new int[exps.length];
        for (int i = 0; i < cos.length; i++){
            coeffs[i] = cos[i];
            expons[i] = exps[i];
        }
    }
    //resume here, step b) changing exisitng methods according to extra array

    public Polynomial add(Polynomial poly){
        int longer = Math.max(coeffs.length, poly.coeffs.length);
        int shorter = Math.min(coeffs.length, poly.coeffs.length);
        double[] sumCoeffs = new double[longer];
        int[] sumExpons = new int[longer];
        int i;

        for (i = 0; i < shorter && expons[i] == poly.expons[i]; i++){
            sumCoeffs[i] = coeffs[i] + poly.coeffs[i];
            sumExpons[i] = expons[i];
        }
        for(int j = i; j < sumCoeffs.length; j++){//is this the same as the commented out code below?
            if (coeffs.length > poly.coeffs.length){
                sumCoeffs[j] = coeffs[j];
                sumExpons[j] = expons[j];
            }
            else{
                sumCoeffs[j] = poly.coeffs[j];
                sumExpons[j] = poly.expons[j];
            }
        }
        /**
        if (coeffs.length > poly.coeffs.length) {
            for (int j = i; j < sum.length; j++) {
                sum[j] = coeffs[j];
            }
        }
        else{
            for(int j = i; j < sum.length; j++){
                sum[j] = poly.coeffs[j];
            }
        }
         */
        return new Polynomial(sumCoeffs, sumExpons);
    }

    public double evaluate(double x){
        //need to multiply each element in the array based on its exponent
        double eval = 0.0;
        for(int i = 0; i < coeffs.length; i++){
            eval += coeffs[i]*Math.pow(x, expons[i]);
        }
        return eval;
    }

    public boolean hasRoot(double x){
        return evaluate(x) == 0;
    }
}