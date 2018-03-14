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
	public Fraction(double input) {
		if(input != 0) {
			input*=1000;
			numerator = (int) Math.floor(input);
			denominator = 1000;
			reduce();
		}else {
			numerator = 0;
			denominator = 1;
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
	//Base operations for longs
	public Fraction multiplyNumber(long input) throws denominatorZeroException {
		return new Fraction(numerator*input, denominator);
	}
	public Fraction divideNumber(long input) throws denominatorZeroException {
		return new Fraction(numerator, denominator*input);
	}
	public Fraction addNumber(long input) throws denominatorZeroException {
		Fraction temp = new Fraction(input,1);
		return addF(temp);
	}
	public Fraction subtractNumber(long input) throws denominatorZeroException {
		Fraction temp = new Fraction(input,1);
		return subtractF(temp);
	}
	//Base operations for doubles
	public Fraction multiplyNumber(double input) throws denominatorZeroException {
		Fraction newNumber = rationalize(input);
		return multiplyF(newNumber);
	}
	public Fraction divideNumber(double input) throws denominatorZeroException {
		Fraction newNumber = rationalize(input);
		return divideF(newNumber);
	}
	public Fraction addNumber(double input) throws denominatorZeroException {
		Fraction newNumber = rationalize(input);
		return addF(newNumber);
	}
	public Fraction subtractNumber(double input) throws denominatorZeroException {
		Fraction newNumber = rationalize(input);
		return subtractF(newNumber);
	}
	/**
	 * Reduces the fraction used when a new fraction is made
	 */
	public void reduce() {
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
	 * Simple method for multiplying 2 fractions
	 * @return Product of fraction*input fraction
	 */
	public Fraction multiplyF(Fraction input) {
		try {
			Fraction newFraction = new Fraction(numerator * input.numerator, denominator * input.denominator);
			return newFraction;
		} catch (denominatorZeroException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * Simple method for dividing 2 fractions input fraction cannot be 0 or there will be a denominatorZeroException
	 * @return Product of fraction*1/input fraction
	 */
	public Fraction divideF(Fraction input){
		try {
			Fraction newFraction = new Fraction(numerator * input.denominator, denominator * input.numerator);
			return newFraction;
		} catch (denominatorZeroException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * Method for adding 2 fractions
	 * @return Sum of fraction +input fraction
	 */
	public Fraction addF(Fraction input){
		try {
			long newNum = (denominator * input.numerator) + (numerator * input.denominator);
			long newDenom = denominator * input.denominator;
			
			Fraction newFraction = new Fraction(newNum, newDenom);
			
			//The key here is that because we rely on the reduce method when making a new fraction
			//We don't need to worry about having smallest denominator
			return newFraction;
		} catch (denominatorZeroException e) {
			e.printStackTrace();
			return null;
		}

	}
	/**
	 * Method for subtracting 2 fractions
	 * @return Sum of fraction -input fraction
	 */
	public Fraction subtractF(Fraction input){
		try {
			long newNum =  (numerator * input.denominator) - (denominator * input.numerator);
			long newDenom = denominator * input.denominator;
			Fraction newFraction = new Fraction(newNum, newDenom);
			
			//The key here is that because we rely on the reduce method when making a new fraction
			//We don't need to worry about having smallest denominator
			return newFraction;
		} catch (denominatorZeroException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * Takes the fraction and exponentiates it to the correct power
	 * @param power
	 * @return this fraction^power
	 */
	public Fraction exponentiate(int power){
		if(power == 1) 
			return this;
		try {
			if(power<0) {
				long newNum = 1;
				long newDenom = 1;
				for(int i=0; i<power; i++) {
					newNum *= denominator;
					newDenom *= numerator;
				}
				return new Fraction(newNum, newDenom);
			}
			else if(power==0) 
				return new Fraction(1,1);
			long newNum = 1;
			long newDenom = 1;
			for(int i=0; i<power; i++) {
				newNum *= numerator;
				newDenom *= denominator;
			}
			return new Fraction(newNum,newDenom);
		} catch (denominatorZeroException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String toString() {
		if(denominator == 1 || numerator == 0)
			return String.valueOf(numerator);
		else
			return  numerator + "/" + denominator;
	}
	/**
	 * Square Root function for fractions
	 * @param input
	 * @return Square root of the input fraction
	 */
	public SquareRoot exactSquareRoot(){
		try {
			SquareRoot num = ExactMath.exactSquareRoot(numerator);
			SquareRoot denom = ExactMath.exactSquareRoot(denominator);
			Fraction out = num.outsidePart.divideF(denom.outsidePart);
			Fraction in = num.insidePart.divideF(denom.insidePart);
			
			return new SquareRoot(in, out);
		} catch (denominatorZeroException e) {
			e.printStackTrace();
		}
		return null;		
	}
	/**
	 * Cube Root function for fractions
	 * @param input
	 * @return Cube root of the input fraction
	 */
	public CubeRoot exactCubeRoot(){
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
	public double getValue() {
		return numerator/denominator;
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
