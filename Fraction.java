package myMath;

class Fraction extends MyNumber{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int numerator;
	private int denominator;
	private boolean negative;

	public Fraction(int num, int denom) {
		numerator = Math.abs(num);
		denominator = Math.abs(denom);
		if(num < 0 ^ denom < 0)
			negative = true;
		reduce();
	}
	
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
		newFraction.negative = ((a.negative ^ negative)? true: false);
		return newFraction;
	}
	public Fraction divideF(Fraction a) {
		Fraction newFraction = new Fraction(numerator * a.denominator, denominator * a.numerator);
		newFraction.negative = ((a.negative ^ negative)? true: false);
		return newFraction;
	}
	public Fraction addF(Fraction a) {
		int newNum;
		int newDenom;
		if(negative  ^ a.negative) {
			newNum = (denominator * a.numerator) - (numerator * a.denominator);
			newDenom = denominator * a.denominator;
		}
		else {
			newNum = (denominator * a.numerator) + (numerator * a.denominator);
			newDenom = denominator * a.denominator;
		}
		Fraction newFraction = new Fraction(newNum, newDenom);
		
		//The key here is that because we rely on the reduce method when making a new fraction
		//We don't need to worry about having smallest denominator
		return newFraction;
	}
	public Fraction subtractF(Fraction a) {
		int newNum;
		int newDenom;
		a.negative = !a.negative;
		if(negative  ^ a.negative) {
			newNum = (denominator * a.numerator) - (numerator * a.denominator);
			newDenom = denominator * a.denominator;
		}
		else {
			newNum = (denominator * a.numerator) + (numerator * a.denominator);
			newDenom = denominator * a.denominator;
		}
		Fraction newFraction = new Fraction(newNum, newDenom);
		
		//The key here is that because we rely on the reduce method when making a new fraction
		//We don't need to worry about having smallest denominator
		return newFraction;
	}


	public double getValue() {
		return ((negative)?-1:1) * (numerator/denominator);
	}
	public String toString() {
		return  ((negative)? "-": "")+ numerator + "/" + denominator;
	}
}