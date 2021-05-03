package Exceptions;

@SuppressWarnings("serial")
public class NoProfilesCreatedException extends RuntimeException {
	private static final String NO_PROFILES_CREATED = "Nenhum perfil foi criado.";
	
	public NoProfilesCreatedException() {
		// TODO Auto-generated constructor stub
		super(NO_PROFILES_CREATED);
	}

}
