package Profile;

import java.util.Iterator;

import Calculadora.CalculadoraInt;

public interface ProfilesManagerInterface {

	/**
	 * 
	 * @param name -> nome do perfil
	 * @param age -> idade do perfil
	 * @param description -> descricao do perfil
	 */
	void addProfile(String name, int age);
	
	/**
	 * 
	 * @return devolve o perfil que esta ativo
	 */
	String getOnProfile();
	
	/**
	 * Desativa o perfil que esta ligado.
	 */
	void deactivateOnProfiles();
	
	/**
	 * Procura no mapa se existe um perfile com o nome 'name'
	 * @param name -> nome do perfil
	 * @return true se existir, falso se nao
	 */
	boolean existsProfileWithName(String name);
	
	/**
	 * <pre> todos os outros perfis estao desativados
	 * @param name -> nome do perfil a desativar
	 */
	void activateProfile(String name);
	
	/**
	 * 
	 * @return numero de perfis criados
	 */
	int numberOfProfilesCreated();
	
	/**
	 * 
	 * @return iterador de perfis
	 */
	Iterator<String> profileIterator();
	
	/**
	 * @param name -> nome do perfil
	 * @return um perfil
	 */
	ProfileInterface getProfile(String name);
	
	CalculadoraInt getCalculadoraOfProfile();
}
