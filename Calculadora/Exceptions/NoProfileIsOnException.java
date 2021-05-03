package Exceptions;

@SuppressWarnings("serial")
public class NoProfileIsOnException extends RuntimeException {
	private static final String NO_PROFILES_CREATED = "Nenhum perfil está ativo.";
	
	public NoProfileIsOnException() {
		// TODO Auto-generated constructor stub
		super(NO_PROFILES_CREATED);
	}

}
