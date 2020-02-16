package co.edu.icesi.ci.injectionexample1.boot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import co.edu.icesi.ci.injectionexample1.repositories.CourseRepositoryImp;
import co.edu.icesi.ci.injectionexample1.repositories.RegistrationRepositoryImp;
import co.edu.icesi.ci.injectionexample1.repositories.StudentRepositoryImp;
import co.edu.icesi.ci.injectionexample1.service.RegistrationService;
import co.edu.icesi.ci.injectionexample1.service.RegistrationServiceImp;

@SpringBootApplication
public class NoInjectionApplication {
	
	private static RegistrationService registration;

	public static void main(String[] args) {
		SpringApplication.run(NoInjectionApplication.class, args);
		registration.enrolStudent("11","101",192);
	}
	
	@Bean
	public CommandLineRunner dummy(RegistrationRepositoryImp registrationRepository, StudentRepositoryImp studentRepository, CourseRepositoryImp courseRepository) {
		return (args) -> {
			 registration = new RegistrationServiceImp(studentRepository, courseRepository, registrationRepository);
		};
	}

}

