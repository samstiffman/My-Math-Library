package myMath;

class MyInteger extends MyNumber{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long value;
	
	public MyInteger(long val) {
		value = val;
	}
	
	public double getValue() {
		return value;
	}

	public String toString() {
		return String.valueOf(value);
	}
}