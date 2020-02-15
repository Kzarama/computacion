package com.repaso.Parcial.services;

import com.repaso.Parcial.model.Person;

public interface PersonService {
	
	public boolean savePerson(String firstName, String lastName);
	public Person queryPerson(String lastName);
	public boolean deletePerson(String lastName);
	public boolean updatePerson(String firstName, String lastName);
	
}
