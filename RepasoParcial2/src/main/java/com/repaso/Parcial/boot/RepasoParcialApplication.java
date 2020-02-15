package com.repaso.Parcial.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.repaso.Parcial.services.PersonServiceImp;

import lombok.extern.java.Log;

@Log
@SpringBootApplication
@ComponentScan("com.repaso.Parcial")
public class RepasoParcialApplication {
	
	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(RepasoParcialApplication.class, args);
		PersonServiceImp personService = ctx.getBean(PersonServiceImp.class);
		personService.savePerson("Cristiano", "Ronaldo");
		personService.savePerson("Lionel", "Messi");
		log.info(personService.queryPerson("Ronaldo") + "");
		personService.deletePerson("Ronaldo");
		personService.deletePerson("Messi");
		log.info(personService.queryPerson("Ronaldo") + "");
	}
		
}
