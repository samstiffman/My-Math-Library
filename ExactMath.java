package myMath;

import myMath.Fraction.denominatorZeroException;

public class ExactMath {
	
	public static SquareRoot exactSquareRoot(double input) {
		long out = 1, in = (long)input;
		long upper = (in>4)? in/2: in;
		for(int i=1; i<upper; i++) 
			if(in%(i*i) == 0) {
				in /= (i*i);
				out *= i;
				if(in==1)
					break;
			}
		return new SquareRoot(in, out);
	}
	public static CubeRoot exactCubeRoot(double input) {
		long out = 1, in = (long)input;
		long upper = in/2;
		for(int i=1; i<upper; i++) 
			if(in%(i*i*i) == 0) {
				in /= (i*i*i);
				out *= i;
				if(in==1)
					break;
			}
		return new CubeRoot(in, out);
	}	
	
	
	
}
class SquareRoot extends MyNumber{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	double value;
	Fraction outsidePart;
	Fraction insidePart; 
	
	SquareRoot(long in, long out) {
		insidePart = new Fraction(in);
		outsidePart = new Fraction(out);
	}
	/**
	 * Constructor for use with fractional square roots
	 * @param in
	 * @param out
	 */
	public SquareRoot(Fraction in, Fraction out) throws denominatorZeroException {
		insidePart = in;
		outsidePart = out;
		//Reduces the Fraction so the square root is not in the denominator
		if(insidePart.getDenominator() > 1) {
			long temp = insidePart.getDenominator();
			insidePart.setDenominator(1);
			
			insidePart.setNumerator(temp*insidePart.getNumerator());;
			outsidePart.setDenominator(temp);
			insidePart.reduce();
			outsidePart.reduce();
		}
	}
	@Override
	public double getValue() {
		return value;
	}

	@Override
	public String toString() {
		if(insidePart.getValue() == 1)
			return outsidePart.toString();
		else if(outsidePart.getValue() == 1)
			return "root(" + insidePart + ")";
		else
			return "(" + outsidePart + ")*root(" + insidePart + ")";
	}

}
class CubeRoot extends MyNumber{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	double value;
	Fraction outsidePart, insidePart; 
	
	CubeRoot(long in, long out) {
		insidePart = new Fraction(in);
		outsidePart = new Fraction(out);
	}
	public CubeRoot(Fraction in, Fraction out) {
		insidePart = in;
		outsidePart = out;
	}
	@Override
	public double getValue() {
		return value;
	}

	@Override
	public String toString() {
		if(insidePart.getValue() == 1)
			return String.valueOf(outsidePart);
		else if(outsidePart.getValue() == 1)
			return "cubeRoot(" + insidePart + ")";
		else
			return "(" + outsidePart + ")*cubeRoot(" + insidePart + ")";
	}

}
