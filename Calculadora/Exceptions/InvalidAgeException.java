package Exceptions;

@SuppressWarnings("serial")
public class InvalidAgeException extends RuntimeException {
	private static final String INVALID_NUMBER = "A idade inserida n�o � v�lida.";
	
	public InvalidAgeException() {
		// TODO Auto-generated constructor stub
		super(INVALID_NUMBER);
	}

}
