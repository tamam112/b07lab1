import java.util.Arrays;
import java.io.File;
import java.io.IOException;
public class Driver {
    public static void main(String [] args) {
        Polynomial p = new Polynomial();

        //test evaluate
        System.out.println(p.evaluate(3)); //should evaulate to 0.0

        //create Polynomial 1
        double [] c1 = {1,2,3};
        int [] e1 = {2,1,0};

        //double[] c3 = {3,0,2,1,0};//{3,2,1};//{1,-2,-9};//{6,0,0,5};
        //int[] e3 = {4,2,2,1,0};//{2,1,0};//{0,1,4};//{0,1,2,3};

        Polynomial p1 = new Polynomial(c1, e1);
        //Polynomial p3 = new Polynomial(c3, e3);

        //create Polynomial 2
        double [] c2 = {0};//{0,4,5};
        int [] e2 = {0};//{2,1,0};

        //double[] c4 = {4,0,2,0};//{4,2};//{6,4,6,5,3};//{0,-2,0,0,-9};
        //int[] e4 = {3,2,2,1};//{1,0};//{0,2,2,3,2};//{0,1,2,3,5};

        Polynomial p2 = new Polynomial(c2, e2);
        //Polynomial p4 = new Polynomial(c4, e4);

        //test add
        Polynomial s = p1.add(p2);
        System.out.println("s(0.1) = " + s.evaluate(0.1));
        System.out.println("s Coeffs: " + Arrays.toString(s.coeffs) + ", s Expons: " + Arrays.toString(s.expons));

        //Polynomial s2 = p3.add(p4);
        //System.out.println("s2(0.1) = " + s2.evaluate(0.1));
        //System.out.println("s2 Coeffs: " + Arrays.toString(s2.coeffs) + "s2 Expons: " + Arrays.toString(s2.expons));

        //test hasRoot
        if(s.hasRoot(1))
            System.out.println("1 is a root of s");
        else
            System.out.println("1 is not a root of s");

        //test multiply
        Polynomial prodP1P2 = p1.multiply(p2);
        //Polynomial prodP3P4 = p3.multiply(p4);

        System.out.println("The Coefficients for p1*p2 are: " + Arrays.toString(prodP1P2.coeffs));
        System.out.println("The Exponents for p1*p2 are: " + Arrays.toString(prodP1P2.expons));
        System.out.println("p1*p2 evaluated: " + prodP1P2.evaluate(0.1));

        /**
        System.out.println("The Coefficients for p3*p4 are: " + Arrays.toString(prodP3P4.coeffs));
        System.out.println("The Exponents for p3*p4 are: " + Arrays.toString(prodP3P4.expons));
        System.out.println("p3*p4 evaluated: " + prodP3P4.evaluate(2));
        */

        File polyTest = new File("/Users/tamammakki/Desktop/b07lab1/polytest.txt");
        Polynomial test = new Polynomial(polyTest);
        System.out.println(test.evaluate(1));

        p1.saveToFile("/Users/tamammakki/Desktop/b07lab1/test1.txt");

    }
}