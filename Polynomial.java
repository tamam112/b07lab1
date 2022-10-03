import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Polynomial {
	double[] coeffs;
	int[] expos;
	
	public Polynomial() {
		coeffs = new double[1];
		coeffs[0] = 0;
		expos = new int[1];
		expos[0] = 0;
	}
	public Polynomial(double[] cos, int[] exp) {
		int len = cos.length;
		coeffs = new double[len];
		expos = new int[len];
		for(int i = 0; i < len; i++) {
			coeffs[i] = cos[i];
			expos[i] = exp[i];
		}
	}
	
	public Polynomial(File polFile) {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(polFile));
			String line = reader.readLine();
			int length = line.length();
			double [] cos = new double[length];
			int [] exp = new int[length];
			boolean isNeg = false, isExp = false;
			int i = 0, j = 0;
			for(char s: line.toCharArray()) {
				if(isExp) {//if last character was x
					exp[j] = Integer.parseInt(Character.toString(s));
					j++;
					isExp = false;
				}
				else if(s == 'x') {
					isExp = true;
				}
				else if(s == '-') {
					isNeg = true;
				}
				else if(s == '+')
				{
					continue;
				}
				else {
					double num = Double.parseDouble(Character.toString(s));
					if(isNeg) {
						num = -1*num;
						isNeg = false;
					}
					
					cos[i] = num;
					i++;
				}
			}
			coeffs = new double[i];
			expos  = new int[j + 1];
			for(int k = 0; k < i; k++) {
				coeffs[k] = cos[k];
			}
			expos[0] = 0;
			for(int k = 0; k < j; k++) {
				expos[k + 1] = exp[k];
			}
			reader.close();
		}
		
		catch(IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public Polynomial add(Polynomial poly) {
		int shorter_len;
		int longer_len;
		if(this.coeffs.length > poly.coeffs.length) {
			longer_len = coeffs.length;
			shorter_len = poly.coeffs.length;
		}
		else {
			longer_len = poly.coeffs.length;
			shorter_len = coeffs.length;
		}
		double[] coAns = new double[longer_len];
		int[] expAns = new int[longer_len];
		int i = 0;
		while(i < shorter_len && poly.expos[i] == expos[i]) {
			coAns[i] = poly.coeffs[i] + coeffs[i];
			expAns[i] = poly.expos[i];
			i++;
		}
		if(i == poly.coeffs.length) {
			while(i < longer_len) {
				coAns[i] = coeffs[i];
				expAns[i] = expos[i];
				i++;
			}
		}
		else {
			while(i < longer_len) {
				coAns[i] = poly.coeffs[i];
				expAns[i] = poly.expos[i];
				i++;
			}
		}
		Polynomial answer = new Polynomial(coAns, expAns);
		return answer;
	}
	
	public double evaluate(double x) {
		double xpow;
		double solution = 0.0;
		for(int i = 0; i < coeffs.length; i++) {
			xpow = Math.pow(x, expos[i]);
			solution += coeffs[i] * xpow;
		}
		return solution;
	}
	
	public boolean hasRoot(double val) {
		return evaluate(val) == 0;
	}
	
	public Polynomial multiply(Polynomial pol) {
		int shorter;
		int longer;
		if(this.coeffs.length > pol.coeffs.length) {
			longer = this.coeffs.length;
			shorter = pol.coeffs.length;
		}
		else {
			longer = pol.coeffs.length;
			shorter = this.coeffs.length;
		}
		double[] coAns = new double[longer * shorter];
		int[] expAns = new int[longer * shorter];
		int k = 0;
		for(int i = 0; i < longer; i++) {
			for(int j = 0; j < shorter; j++) {
				coAns[k] = pol.coeffs[i] * coeffs[j];
				expAns[k] = pol.expos[i] + expos[j];
				k++;
			}
		}
		Polynomial soln = new Polynomial(coAns, expAns);
		return soln;
	}
	
	public void saveToFile(String fileName) {
		FileWriter writer;
		try {
			writer = new FileWriter(fileName);
	
			for(int i = 0; i < coeffs.length; i++)
			{
				if(i > 0 && coeffs[i] > 0)
					writer.write("+");
				writer.write((int)coeffs[i] + "");
				if(i > 0) {
					writer.write("x");
					writer.write(expos[i] + "");
				}
			}
			writer.write("\n");
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
