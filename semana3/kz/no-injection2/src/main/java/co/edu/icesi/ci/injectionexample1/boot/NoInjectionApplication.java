package co.edu.icesi.ci.injectionexample1.boot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import co.edu.icesi.ci.injectionexample1.service.RegistrationService;
import co.edu.icesi.ci.injectionexample1.service.RegistrationServiceImp;

@SpringBootApplication
@ComponentScan({"co.edu.icesi.ci.injectionexamples.repositories", "co.edu.icesi.ci.injectionexamples.services"})
public class NoInjectionApplication {
	
	private static RegistrationService registrationService;

	public static void main(String[] args) {
		SpringApplication.run(NoInjectionApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner dummy(RegistrationService registrationServiceImp) {
		return (args) -> {
			registrationService = registrationServiceImp;
			registrationService.enrolStudent("11", "101", 192);
		};
	}
	
}

