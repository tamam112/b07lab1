import java.lang.Math;
import java.util.Arrays;

public class Polynomial {

    double[] coeffs;
    int[] expons;

    public Polynomial(){//constrctr updated for lab2
        coeffs = new double[1];
        expons = new int[1];
        coeffs[0] = 0;
        expons[0] = 0;
    }

    public Polynomial(double[] cos, int[] exps){//constrctr updated for lab2
        coeffs = new double[cos.length];
        expons = new int[exps.length];
        for (int i = 0; i < cos.length; i++){
            coeffs[i] = cos[i];
            expons[i] = exps[i];
        }
    }

    public Polynomial(File file){

    }

    public Polynomial add(Polynomial poly){//method updated for lab2
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
        return new Polynomial(sumCoeffs, sumExpons);
    }

    public double evaluate(double x){//method updated for lab2
        //need to multiply each element in the array based on its exponent
        double eval = 0.0;
        for(int i = 0; i < coeffs.length; i++){
            eval += coeffs[i]*Math.pow(x, expons[i]);
        }
        return eval;
    }

    public boolean hasRoot(double x){//method updated for lab2
        return evaluate(x) == 0;
    }

    public Polynomial multiply(Polynomial poly){
        double[] multiCoeffs = new double[poly.coeffs.length*coeffs.length];
        int[] multiExpons = new int[poly.coeffs.length*coeffs.length];
        int k = 0;//counts num of terms (expanded form), remaining values in arrays are filled by default double value

        for(int i = 0; i < coeffs.length; i++){
            for(int j = 0; j < poly.coeffs.length; j++){
                if(coeffs[i]*poly.coeffs[j] != 0){
                    multiCoeffs[k] = coeffs[i]*poly.coeffs[j];
                    multiExpons[k] = expons[i] + poly.expons[j];
                    k++;
                }
            }
        }

        //collect like terms
        double[] finalCoeffs = new double[multiCoeffs.length];
        int[] finalExpons = new int[multiExpons.length];
        int curr = 0;

        for(int i = 0; i < multiCoeffs.length; i++){
            boolean srch = false;

            for(int j = 0; j < curr; j++){
                if(finalExpons[j] == multiExpons[i]){//if matching expon is found, add the coeffs
                    finalCoeffs[j] += multiCoeffs[i];
                    srch = true;
                    break;
                }
            }
            if(srch == false){//if no matches, add expon and coeff to respective arrays
                finalCoeffs[curr] = multiCoeffs[i];
                finalExpons[curr] = multiExpons[i];
                curr++;
            }
        }

        //count number of redundant exponents
        int counter = 0;
        for(int i = 0; i < finalCoeffs.length; i++){
            if(finalExpons[i] == 0){
                counter++;
            }
        }

        //remove redundant exponents
        double[] prodCoeffs = new double[finalCoeffs.length - counter]; //finalCoeffs-counter
        int[] prodExpons = new int[finalCoeffs.length - counter];
        for(int i = 0; finalCoeffs[i] != 0 && i < (finalCoeffs.length - counter); i++){
            prodCoeffs[i] = finalCoeffs[i];
            prodExpons[i] = finalExpons[i];
         }

        return new Polynomial(prodCoeffs, prodExpons);
    }
}