package com.repaso.Parcial.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.repaso.Parcial.services.PersonService;

import lombok.extern.java.Log;

@Log
@SpringBootApplication
public class RepasoParcialApplication {
	
	@Autowired
	PersonService personService;
	
	public static void main(String[] args) {
		SpringApplication.run(RepasoParcialApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner dummy(PersonService personService) {
		return (args) -> {
			personService.savePerson("Cristiano", "Ronaldo");
			personService.savePerson("Lionel", "Messi");
			log.info(personService.queryPerson("Ronaldo") + "");
			personService.deletePerson("Ronaldo");
			personService.deletePerson("Messi");
			log.info(personService.queryPerson("Ronaldo") + "");
		};
	}
	
}
