import java.lang.Math;

public class Polynomial {

    double[] coeffs;

    public Polynomial(){
        coeffs = new double[1];
        coeffs[0] = 0;
    }

    public Polynomial(double[] cos){
        coeffs = new double[cos.length];
        for (int i = 0; i < cos.length; i++){
            coeffs[i] = cos[i];
        }
    }

    public Polynomial add(Polynomial poly){
        int longer = Math.max(coeffs.length, poly.coeffs.length);
        int shorter = Math.min(coeffs.length, poly.coeffs.length);
        double[] sum = new double[longer];
        int i;

        for (i = 0; i < shorter; i++){
            sum[i] = coeffs[i] + poly.coeffs[i];
        }
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
        return new Polynomial(sum);
    }

    public double evaluate(double x){
        //need to multiply each element in the array based on its exponent
        double eval = coeffs[0];
        for(int i = 1; i < coeffs.length; i++){
            eval += coeffs[i]*Math.pow(x, i);
        }
        return eval;
    }

    public boolean hasRoot(double x){
        return evaluate(x) == 0;
    }
}