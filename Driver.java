import java.io.File;
import java.io.IOException;

public class Driver { 
	public static void main(String [] args) throws IOException	{ 
		Polynomial p = new Polynomial(); 
		System.out.println(p.evaluate(3)); 
		double [] c1 = {2,1};
		int [] d1 = {0,1};
		Polynomial p1 = new Polynomial(c1, d1); 
		double [] c2 = {4,1}; 
		int [] d2 = {0, 1};
		Polynomial p2 = new Polynomial(c2, d2);
		Polynomial s = p1.add(p2); 
		System.out.println("s(0.1) = " + s.evaluate(0.1)); 
		if(s.hasRoot(1)) 
			System.out.println("1 is a root of s"); 
		else 
			System.out.println("1 is not a root of s");
		Polynomial multi = p1.multiply(p2);
		System.out.println("multi(2) = " + multi.evaluate(2));
		File f = new File("/Users/tamammakki/eclipse-workspace/b07 lab 1/src/polex");
		Polynomial test = new Polynomial(f);
		System.out.println(test.evaluate(1));
		p1.saveToFile("/Users/tamammakki/eclipse-workspace/b07 lab 1/src/test");
		}
	} 