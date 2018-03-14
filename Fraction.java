package myMath;

class Fraction extends MyNumber{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long numerator;
	private long denominator;
	
	public Fraction(long num, long denom) throws denominatorZeroException {
		if(denom == 0) {
			String message = "Your fraction has a denominator of 0";
			throw new denominatorZeroException(message);
		}
		else {
			long sign;
			if(num < 0 ^ denom < 0)
				sign = -1;
			else
				sign = 1;
			numerator = sign*Math.abs(num);
			denominator = Math.abs(denom);
			reduce();
		}
	}
	
	/**
	 * 1 Number long cunstructor for a fractional representation of an Integer
	 * @param num
	 */
	public Fraction(long num) {
		numerator = num;
		denominator = 1;
	}
	/**
	 * Fraction constructor for converting double -> fraction
	 */
	public Fraction(double a) {
		if(a != 0) {
			a*=1000;
			numerator = (int) Math.floor(a);
			denominator = 1000;
			reduce();
		}else {
			numerator = 0;
			denominator = 1;
		}
	}
	/**
	 * Reduces the fraction used when a new fraction is made
	 */
	private void reduce() {
		if(numerator%denominator == 0) {
			numerator = numerator / denominator;
			denominator = 1;
			return;
		}
		long greater = (numerator>denominator)? numerator : denominator;
		for(int i=2; i<greater; i++) {
			while(numerator%i == 0 && denominator%i == 0) {
				numerator  = numerator / i;
				denominator /= i;
			}
		}
	}
	/**
	 * Turns a double into a Rational Number
	 * @param a
	 * @return
	 * @throws denominatorZeroException
	 */
	public static Fraction rationalize(double a) throws denominatorZeroException {
		long temp = 100;
		a*=100;
		final int[] temporary = {2,3,4,5,7,9,11};
		for(int i: temporary) {
			a*=i;
			temp*=i;
		}
		return new Fraction((long)a, temp);
	}
	public Fraction multiplyF(Fraction a) throws denominatorZeroException {
		Fraction newFraction = new Fraction(numerator * a.numerator, denominator * a.denominator);
		return newFraction;
	}
	public Fraction divideF(Fraction a) throws denominatorZeroException {
		Fraction newFraction = new Fraction(numerator * a.denominator, denominator * a.numerator);
		return newFraction;
	}
	public Fraction addF(Fraction a) throws denominatorZeroException {
		long newNum;
		long newDenom;
		
		newNum = (denominator * a.numerator) + (numerator * a.denominator);
		newDenom = denominator * a.denominator;
		
		Fraction newFraction = new Fraction(newNum, newDenom);
		
		//The key here is that because we rely on the reduce method when making a new fraction
		//We don't need to worry about having smallest denominator
		return newFraction;
	}
	public Fraction subtractF(Fraction a) throws denominatorZeroException {
		long newNum;
		long newDenom;
		newNum =  (numerator * a.denominator) - (denominator * a.numerator);
		newDenom = denominator * a.denominator;
		Fraction newFraction = new Fraction(newNum, newDenom);
		
		//The key here is that because we rely on the reduce method when making a new fraction
		//We don't need to worry about having smallest denominator
		return newFraction;
	}
	public Fraction exponentiate(int power) throws denominatorZeroException {
		long newNum = 1;
		long newDenom = 1;
		for(int i=0; i<power; i++) {
			newNum *= numerator;
			newDenom *= denominator;
		}
		return new Fraction(newNum,newDenom);
	}
	public double getValue() {
		return numerator/denominator;
	}
	public String toString() {
		if(denominator == 1 || numerator == 0)
			return String.valueOf(numerator);
		else
			return  numerator + "/" + denominator;
	}
		//Base operations for longs
	public Fraction multiplyNumber(long a) throws denominatorZeroException {
		return new Fraction(numerator*a, denominator);
	}
	public Fraction divideNumber(long a) throws denominatorZeroException {
		return new Fraction(numerator, denominator*a);
	}
	public Fraction addNumber(long a) throws denominatorZeroException {
		Fraction temp = new Fraction(a,1);
		return addF(temp);
	}
	public Fraction subtractNumber(long a) throws denominatorZeroException {
		Fraction temp = new Fraction(a,1);
		return subtractF(temp);
	}
	//Base operations for doubles
	public Fraction multiplyNumber(double d) throws denominatorZeroException {
		Fraction newNumber = rationalize(d);
		return multiplyF(newNumber);
	}
	public Fraction divideNumber(double d) throws denominatorZeroException {
		Fraction newNumber = rationalize(d);
		return divideF(newNumber);
	}
	public Fraction addNumber(double d) throws denominatorZeroException {
		Fraction newNumber = rationalize(d);
		return addF(newNumber);
	}
	public Fraction subtractNumber(double d) throws denominatorZeroException {
		Fraction newNumber = rationalize(d);
		return subtractF(newNumber);
	}
	/**
	 * Square Root function for fractions
	 * @param input
	 * @return
	 * @throws denominatorZeroException 
	 */
	public SquareRoot exactSquareRoot() throws denominatorZeroException {
		
		SquareRoot num = ExactMath.exactSquareRoot(numerator);
		SquareRoot denom = ExactMath.exactSquareRoot(denominator);
		Fraction out = num.outsidePart.divideF(denom.outsidePart);
		Fraction in = num.insidePart.divideF(denom.insidePart);
		
		return new SquareRoot(in, out);
	}
	public CubeRoot exactCubeRoot() throws denominatorZeroException {
		CubeRoot num = ExactMath.exactCubeRoot(numerator);
		CubeRoot denom = ExactMath.exactCubeRoot(denominator);
		
		Fraction out = num.outsidePart.divideF(denom.outsidePart);
		Fraction in = num.insidePart.divideF(denom.insidePart);
		
		return new CubeRoot(in, out);
	}
	public long getNumerator() {
		return numerator;
	}
	public long getDenominator() {
		return denominator;
	}
	class denominatorZeroException extends Exception {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		// Parameterless Constructor
	    public denominatorZeroException() {
	    	super();
	    }

	    // Constructor that accepts a message
		public denominatorZeroException(String message) {
			super(message);
		}

	}
}
