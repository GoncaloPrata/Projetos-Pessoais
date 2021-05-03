package Exceptions;

@SuppressWarnings("serial")
public class ProfileAlreadyExistsException extends RuntimeException {

	private static final String PROFILE_ALREADY_EXISTS = "Perfil já existe por isso não foi criado.";
	
	public ProfileAlreadyExistsException() {
		// TODO Auto-generated constructor stub
		super (PROFILE_ALREADY_EXISTS);
	}

}
