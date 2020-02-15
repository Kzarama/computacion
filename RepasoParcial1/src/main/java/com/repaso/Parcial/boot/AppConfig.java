package com.repaso.Parcial.boot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import com.repaso.Parcial.repositories.PersonRepositoryImp;
import com.repaso.Parcial.services.PersonServiceImp;

@Configuration
public class AppConfig {
	
	@Bean
	@Lazy
	public PersonRepositoryImp personRepository() {
		return new PersonRepositoryImp();
	}
	
	@Bean
	public PersonServiceImp personService() {
		return new PersonServiceImp();
	}
	
}
