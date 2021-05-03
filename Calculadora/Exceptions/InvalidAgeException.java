package Exceptions;

@SuppressWarnings("serial")
public class InvalidAgeException extends RuntimeException {
	private static final String INVALID_NUMBER = "A idade inserida não é válida.";
	
	public InvalidAgeException() {
		// TODO Auto-generated constructor stub
		super(INVALID_NUMBER);
	}

}
