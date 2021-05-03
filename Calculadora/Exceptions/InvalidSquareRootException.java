package Exceptions;

@SuppressWarnings("serial")
public class InvalidSquareRootException extends RuntimeException {
	private static final String INVALID_NUMBER = "O valor dentro da raiz quadrada nao é válido.";

	public InvalidSquareRootException() {
		// TODO Auto-generated constructor stub
		super(INVALID_NUMBER);
	}

}
