import java.lang.Math;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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

    public Polynomial(File polyFile){
        BufferedReader reader;

        try{
            reader = new BufferedReader(new FileReader(polyFile));
            String line = reader.readLine();
            int lineLen = line.length();
            double[] coefficients = new double[lineLen];
            int[] exponents = new int[lineLen];
            boolean neg = false, isExp = false;
            int i = 0, j= 0;

            for(char a: line.toCharArray()){
                if(isExp == true){
                    exponents[j] = Integer.parseInt(Character.toString(a));
                    j++;
                    isExp = false;
                }
                else if(a == 'x'){
                    isExp = true;
                }
                else if(a == '-'){
                    neg = true;
                }
                else if(a == '+'){
                    continue;
                }
                else{
                    double n = Double.parseDouble(Character.toString(a));
                    if(neg == true){
                        n = -1*n;
                        neg = false;
                    }
                    coefficients[i] = n;
                    i++;
                }
            }
            coeffs = new double[i];
            expons = new int[j + 1];
            for(int k = 0; k < i; k++){
                coeffs[k] = coefficients[k];
            }
            expons[0] = 0;
            for(int l = 0; l < j; l++){
                expons[l + 1] = exponents[l];
            }
            reader.close();
        }

        catch(IOException e){
            e.printStackTrace();
        }
    }


    public Polynomial add(Polynomial poly){//method updated for lab2
        int longer = Math.max(coeffs.length, poly.coeffs.length);
        int shorter = Math.min(coeffs.length, poly.coeffs.length);
        double[] sumCoeffs = new double[longer];
        int[] sumExpons = new int[longer];
        int i;

        if(coeffs.length == 0 || expons.length == 0 || poly.coeffs.length == 0 || poly.expons.length == 0){
            return new Polynomial();//return empty polynomial if any of the fields are empty
        }

        for (i = 0; i < shorter && expons[i] == poly.expons[i]; i++){
            sumCoeffs[i] = coeffs[i] + poly.coeffs[i];
            sumExpons[i] = expons[i];
        }
        for(int j = i; j < sumCoeffs.length; j++){
            if(coeffs.length > poly.coeffs.length){
                sumCoeffs[j] = coeffs[j];
                sumExpons[j] = expons[j];
            }
            else{
                sumCoeffs[j] = poly.coeffs[j];
                sumExpons[j] = poly.expons[j];
            }
        }
        /**
        for(int j = i; j < sumCoeffs.length; j++){
            if (i < coeffs.length){
                sumCoeffs[j] = coeffs[j];
                sumExpons[j] = expons[j];
            }
            else{
                sumCoeffs[j] = poly.coeffs[j];
                sumExpons[j] = poly.expons[j];
            }
        }
         */
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

        if(coeffs.length == 0 || expons.length == 0 || poly.coeffs.length == 0 || poly.expons.length == 0){
            return new Polynomial();//return empty polynomial if any of the fields are empty
        }

        for(int i = 0; i < coeffs.length; i++){//initial multiplication in expanded form
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

        //count number of nonzero coeffs to find needed length of prodCoeffs[] and prodExpons[]
        int counter = 0;
        for(int i = 0; i < finalCoeffs.length; i++){
            if(finalCoeffs[i] != 0){
                counter++;
            }
        }
        //System.out.println("The value of counter:" + counter);

        //remove redundant exponents
        double[] prodCoeffs = new double[counter];
        int[] prodExpons = new int[counter];
        for(int i = 0; finalCoeffs[i] != 0 && i < counter; i++){
            prodCoeffs[i] = finalCoeffs[i];
            prodExpons[i] = finalExpons[i];
        }

        return new Polynomial(prodCoeffs, prodExpons);
    }

    public void saveToFile(String file){
        FileWriter fwriter;

        try{
            fwriter = new FileWriter(file);

            for(int i = 0; i < coeffs.length; i++){
                if(i > 0 && coeffs[i] > 0){
                    fwriter.write("+");
                }
                fwriter.write((int)coeffs[i] + "");
                if(i > 0){
                    fwriter.write("x");
                    fwriter.write(expons[i] + "");
                }
            }
            fwriter.write("\n");
            fwriter.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}