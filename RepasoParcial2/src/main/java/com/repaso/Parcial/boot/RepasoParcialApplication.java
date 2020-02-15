package com.repaso.Parcial.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.repaso.Parcial.services.PersonService;
import com.repaso.Parcial.services.PersonServiceImp;

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
			personService.savePerson("Ronaldo", "Cristiano");
			personService.savePerson("Messi", "Lionel");
			personService.deletePerson("Ronaldo");
			personService.deletePerson("Messi");
		};
	}
	
}
