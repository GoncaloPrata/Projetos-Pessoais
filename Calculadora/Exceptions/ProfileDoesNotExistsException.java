package Exceptions;

@SuppressWarnings("serial")
public class ProfileDoesNotExistsException extends RuntimeException {

	private static final String PROFILE_DOES_NOT_EXIST = "Perfil não existe.";
	
	public ProfileDoesNotExistsException() {
		// TODO Auto-generated constructor stub
		super (PROFILE_DOES_NOT_EXIST);
	}

}
