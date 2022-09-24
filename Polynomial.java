
public class Polynomial {
	double[] co;
	
	public Polynomial() {
		co = new double[1];
		co[0] = 0;
	}
	public Polynomial(double[] cos) {
		int len = cos.length;
		co = new double[len];
		for(int i = 0; i < len; i++) {
			co[i] = cos[i];
		}
	}
	public Polynomial add(Polynomial poly) {
		int shorter_len;
		int longer_len;
		if(this.co.length > poly.co.length) {
			longer_len = co.length;
			shorter_len = poly.co.length;
		}
		else {
			longer_len = poly.co.length;
			shorter_len = co.length;
		}
		double[] ans  =new double[longer_len];
		int i = 0;
		while(i < shorter_len) {
			ans[i] = poly.co[i] + co[i];
			i++;
		}
		if(i == poly.co.length) {
			while(i < longer_len) {
				ans[i] = co[i];
				i++;
			}
		}
		else {
			while(i < longer_len) {
				ans[i] = poly.co[i];
				i++;
			}
		}
		Polynomial answer = new Polynomial(ans);
		return answer;
	}
	public double evaluate(double x) {
		double xpow;
		double solution = 0.0;
		solution += co[0];
		for(int i = 1; i < co.length; i++) {
			xpow = Math.pow(x, i);
			solution += co[i] * xpow;
		}
		return solution;
	}
	public boolean hasRoot(double val) {
		boolean check = true;
		if(evaluate(val) == 0) {
			return check;
		}
		check = false;
		return check;
	}

}
