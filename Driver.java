import java.util.Arrays;
public class Driver {
    public static void main(String [] args) {
        Polynomial p = new Polynomial();

        //test evaluate
        System.out.println(p.evaluate(3)); //should evaulate to 0.0

        //create Polynomial 1
        double [] c1 = {0,0,0};//{6,0,0,5};
        int [] e1 = {0,0,0};//{0,1,2,3};
        Polynomial p1 = new Polynomial(c1, e1);

        //create Polynomial 2
        double [] c2 = {0,0,0};//{1,-2,0,0,-9};
        int [] e2 = {0,0,0};//{0,1,2,3,4};
        Polynomial p2 = new Polynomial(c2, e2);

        //test add
        Polynomial s = p1.add(p2);
        System.out.println("s(0.1) = " + s.evaluate(0.1));

        //test hasRoot
        if(s.hasRoot(1))
            System.out.println("1 is a root of s");
        else
            System.out.println("1 is not a root of s");

        //test multiply
        Polynomial product = p1.multiply(p2);
        System.out.println("The Coefficients are: " + Arrays.toString(product.coeffs));
        System.out.println("The Exponents are: " + Arrays.toString(product.expons));
        System.out.println(product.evaluate(2));
    }
}