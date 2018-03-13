package myMath;

public class MyDouble extends MyNumber{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final double value;
	
	public MyDouble(double val) {
		value = val;
	}
	
	@Override
	public double getValue() {
		return value;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}

}
