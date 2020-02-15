package com.repaso.Parcial.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.repaso.Parcial.model.Person;
import com.repaso.Parcial.repositories.PersonRepository;

@Lazy
@Service
public class PersonServiceImp implements PersonService {
	
	@Autowired
	private PersonRepository personRepository;
	
	@Override
	public boolean savePerson(String firstName, String lastName) {
		return personRepository.savePerson(firstName, lastName);
	}

	@Override
	public Person queryPerson(String lastName) {
		return personRepository.queryPerson(lastName);
	}

	@Override
	public boolean deletePerson(String lastName) {
		return personRepository.deletePerson(lastName);
	}

	@Override
	public boolean updatePerson(String firstName, String lastName) {
		return personRepository.updatePerson(firstName, lastName);
	}
	
}
