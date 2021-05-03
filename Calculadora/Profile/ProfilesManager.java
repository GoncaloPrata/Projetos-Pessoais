package Profile;

import java.util.SortedMap;
import java.util.TreeMap;

import Calculadora.CalculadoraInt;

import java.util.Iterator;
import Exceptions.*;

public class ProfilesManager implements ProfilesManagerInterface {

	private SortedMap<String, ProfileInterface> profilesMap;

	public ProfilesManager() {
		// TODO Auto-generated constructor stub
		this.profilesMap = new TreeMap<String, ProfileInterface>();
	}

	@Override
	public void addProfile(String name, int age) {
		// TODO Auto-generated method stub
		if (profilesMap.containsKey(name) == true)
			throw new ProfileAlreadyExistsException();
		if (age <= 0)
			throw new InvalidAgeException();
		ProfileClass profile = new ProfileClass(name, age);
		profilesMap.put(name, profile);
	}

	@Override
	public String getOnProfile() throws NoProfilesCreatedException {
		// TODO Auto-generated method stub
		if (profilesMap.isEmpty() == true)
			throw new NoProfilesCreatedException();
		Iterator<String> iterator = profilesMap.keySet().iterator();
		while (iterator.hasNext()) {
			String profileName = iterator.next();
			if (profilesMap.get(profileName).isProfileOn() == true)
				return profileName;
		}
		return null;
	}

	@Override
	public void deactivateOnProfiles() throws NoProfilesCreatedException {
		// TODO Auto-generated method stub
		if (profilesMap.isEmpty() == true)
			throw new NoProfilesCreatedException();
		Iterator<String> iterator = profilesMap.keySet().iterator();
		while (iterator.hasNext()) {
			String profileName = iterator.next();
			if (profilesMap.get(profileName).isProfileOn() == true)
				profilesMap.get(profileName).setProfileOff();
		}
	}

	@Override
	public boolean existsProfileWithName(String name) {
		// TODO Auto-generated method stub
		return profilesMap.containsKey(name);
	}

	@Override
	public void activateProfile(String name) throws NoProfilesCreatedException, ProfileDoesNotExistsException {
		// TODO Auto-generated method stub
		if (profilesMap.isEmpty() == true)
			throw new NoProfilesCreatedException();
		if (existsProfileWithName(name) == false)
			throw new ProfileDoesNotExistsException();
		deactivateOnProfiles();
		profilesMap.get(name).setProfileOn();
	}

	@Override
	public int numberOfProfilesCreated() {
		// TODO Auto-generated method stub
		return profilesMap.size();
	}

	@Override
	public Iterator<String> profileIterator() throws NoProfilesCreatedException {
		// TODO Auto-generated method stub
		if (profilesMap.isEmpty() == true)
			throw new NoProfilesCreatedException();
		Iterator<String> iterator = profilesMap.keySet().iterator();
		return iterator;
	}

	@Override
	public ProfileInterface getProfile(String name) {
		// TODO Auto-generated method stub
		if (existsProfileWithName(name) == false)
			throw new ProfileDoesNotExistsException();
		return profilesMap.get(name);
	}

	@Override
	public CalculadoraInt getCalculadoraOfProfile() {
		// TODO Auto-generated method stub
		if (profilesMap.isEmpty() == true)
			throw new NoProfilesCreatedException();
		if (getOnProfile() == null)
			throw new NoProfileIsOnException();
		String name = getOnProfile();
		return profilesMap.get(name).getCalculadora();
	}

}
