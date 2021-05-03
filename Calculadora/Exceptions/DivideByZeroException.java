package Exceptions;

@SuppressWarnings("serial")
public class DivideByZeroException extends RuntimeException {
	private static final String INVALID_NUMBER = "Ocorreu uma divis�o por 0.";
	
	public DivideByZeroException() {
		// TODO Auto-generated constructor stub
		super(INVALID_NUMBER);
	}

}
