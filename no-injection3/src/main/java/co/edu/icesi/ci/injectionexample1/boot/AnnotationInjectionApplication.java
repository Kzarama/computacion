package co.edu.icesi.ci.injectionexample1.boot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import co.edu.icesi.ci.injectionexample1.service.RegistrationService;

@SpringBootApplication
@ComponentScan({"co.edu.icesi.ci.injectionexample1.repositories", "co.edu.icesi.ci.injectionexample1.service"})
public class AnnotationInjectionApplication {
	
	private static RegistrationService registrationService;
	
	public static void main(String[] args) {
		SpringApplication.run(AnnotationInjectionApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(RegistrationService registrationServiceImp) {
		return (args) -> {
			registrationService = registrationServiceImp;
			registrationService.enrolStudent("11", "101", 192);
		};
	}
	
}
