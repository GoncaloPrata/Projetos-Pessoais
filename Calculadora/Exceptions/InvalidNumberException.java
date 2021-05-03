package Exceptions;

@SuppressWarnings("serial")
public class InvalidNumberException extends RuntimeException {
	private static final String INVALID_NUMBER = "O numero introduzido não é válido.";
	
	public InvalidNumberException() {
		// TODO Auto-generated constructor stub
		super(INVALID_NUMBER);
	}

}
