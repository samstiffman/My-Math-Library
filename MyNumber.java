package myMath;

abstract class MyNumber extends java.lang.Number implements Comparable<MyNumber>{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	abstract double getValue();
	double add(MyNumber a) {
		return getValue() + a.getValue();
	}
	double subtract(MyNumber a) {
		return getValue() - a.getValue();
	}
	double multiply(MyNumber a) {
		return getValue() * a.getValue();
	}
	double divide(MyNumber a) {
		return getValue()/a.getValue();
	}
	boolean equals(MyNumber a) {
		return getValue() == a.getValue();
	}
	public abstract String toString();
	public int compareTo(MyNumber a) {
		if (getValue() < a.getValue()) {
		    return -1;
		}
		else if (getValue() > a.getValue()) {
			return 1;
		}
		return 0;
	}
	@Override
	public int intValue() {
		return (int)getValue();
	}

	@Override
	public long longValue() {
		return (long)getValue();
	}

	@Override
	public float floatValue() {
		return (float)getValue();
	}

	@Override
	public double doubleValue() {
		return getValue();
	}
} 
