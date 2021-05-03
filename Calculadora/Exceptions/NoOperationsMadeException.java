package Exceptions;

@SuppressWarnings("serial")
public class NoOperationsMadeException extends RuntimeException {
	private static final String NO_PROFILES_CREATED = "Ainda não foi feita nenhuma conta nesta calculadora.";
	
	public NoOperationsMadeException() {
		// TODO Auto-generated constructor stub
		super(NO_PROFILES_CREATED);
	}

}
