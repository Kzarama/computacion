package com.repaso.Parcial.repositories;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.repaso.Parcial.model.Person;

@Repository
public class PersonRepositoryImp implements PersonRepository {
	
	HashMap<String, Person> personHash;
	
	public PersonRepositoryImp() {
		personHash = new HashMap<String, Person>();
	}
	
	public boolean savePerson(String firstName, String lastName) {
		Person newPerson = new Person(firstName, lastName);
		personHash.put(lastName, newPerson);
		return personHash.containsKey(lastName);
	}
	
	public Person queryPerson(String lastName) {
		return personHash.get(lastName);
	}
	
	public boolean deletePerson(String lastName) {
		return personHash.remove(lastName, personHash.get(lastName));
	}
	
	public boolean updatePerson(String firstName, String lastName) {
		personHash.remove(lastName);
		personHash.put(lastName, new Person(firstName, lastName));
		return true;
	}
	
}
