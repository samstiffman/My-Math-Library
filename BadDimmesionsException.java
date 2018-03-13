package myMath;

public class BadDimmesionsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Parameterless Constructor
    public BadDimmesionsException() {
    	super();
    }

    // Constructor that accepts a message
	public BadDimmesionsException(String message) {
		super(message);
	}

}
