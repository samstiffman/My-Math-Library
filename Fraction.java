package myMath;

class Fraction extends MyNumber{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int numerator;
	private int denominator;

	public Fraction(int num, int denom) {
		int sign;
		if(num < 0 ^ denom < 0)
			sign = -1;
		else
			sign = 1;
		numerator = sign*Math.abs(num);
		denominator = Math.abs(denom);
		reduce();
	}
	/**
	 * Reduces the fraction used when a new fraction is made
	 */
	private void reduce() {
		if(numerator%denominator == 0) {
			numerator /= denominator;
			denominator = 1;
			return;
		}
		int greater = (numerator>denominator)? numerator : denominator;
		for(int i=2; i<greater; i++) {
			if(numerator%i == 0 && denominator%i == 0) {
				numerator /= i;
				denominator /= i;
			}
		}
	}
	public Fraction multiplyF(Fraction a) {
		Fraction newFraction = new Fraction(numerator * a.numerator, denominator * a.denominator);
		return newFraction;
	}
	public Fraction divideF(Fraction a) {
		Fraction newFraction = new Fraction(numerator * a.denominator, denominator * a.numerator);
		return newFraction;
	}
	public Fraction addF(Fraction a) {
		int newNum;
		int newDenom;
		
		newNum = (denominator * a.numerator) + (numerator * a.denominator);
		newDenom = denominator * a.denominator;
		
		Fraction newFraction = new Fraction(newNum, newDenom);
		
		//The key here is that because we rely on the reduce method when making a new fraction
		//We don't need to worry about having smallest denominator
		return newFraction;
	}
	public Fraction subtractF(Fraction a) {
		int newNum;
		int newDenom;
		newNum =  (numerator * a.denominator) - (denominator * a.numerator);
		newDenom = denominator * a.denominator;
		Fraction newFraction = new Fraction(newNum, newDenom);
		
		//The key here is that because we rely on the reduce method when making a new fraction
		//We don't need to worry about having smallest denominator
		return newFraction;
	}

	public double getValue() {
		return numerator/denominator;
	}
	public String toString() {
		return  numerator + "/" + denominator;
	}
}
