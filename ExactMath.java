package myMath;

import myMath.Fraction.denominatorZeroException;

public class ExactMath {
	
	public static SquareRoot exactSquareRoot(double input) throws denominatorZeroException {
		Fraction a = Fraction.rationalize(input);
		return a.exactSquareRoot();
	}
	
	public static CubeRoot exactCubeRoot(double input) throws denominatorZeroException {
		Fraction a = Fraction.rationalize(input);
		return a.exactCubeRoot();
	}	
	/**
	 * Used when input already is a long
	 * @param input
	 * @return
	 */
	public static SquareRoot exactSquareRoot(long input) {
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
	/**
	 * Used when input already is a long
	 * @param input
	 * @return
	 */
	public static CubeRoot exactCubeRoot(long input) {
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
		
		long newInsideDenominator, newInsideNumerator, newOutsideDenominator;
		//Reduces the Fraction so the square never has a denominator
		if(insidePart.getDenominator() > 1) {
			long temp = insidePart.getDenominator();
			newInsideDenominator = 1;
			newInsideNumerator = insidePart.getNumerator() * temp;
			newOutsideDenominator = outsidePart.getDenominator() * temp;
			insidePart = new Fraction(newInsideNumerator, newInsideDenominator);
			outsidePart = new Fraction(outsidePart.getNumerator(), newOutsideDenominator);
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
