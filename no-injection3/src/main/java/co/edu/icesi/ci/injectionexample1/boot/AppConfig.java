package co.edu.icesi.ci.injectionexample1.boot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.edu.icesi.ci.injectionexample1.repositories.CourseRepositoryImp;
import co.edu.icesi.ci.injectionexample1.repositories.RegistrationRepositoryImp;
import co.edu.icesi.ci.injectionexample1.repositories.StudentRepositoryImp;
import co.edu.icesi.ci.injectionexample1.service.RegistrationServiceImp;

@Configuration
public class AppConfig {
	
	@Bean
	public CourseRepositoryImp courseRepository() {
		return new CourseRepositoryImp();
	}
	
	@Bean
	public RegistrationRepositoryImp registrationRepository() {
		return new RegistrationRepositoryImp();
	}
	
	@Bean
	public StudentRepositoryImp studentRepository() {
		return new StudentRepositoryImp();
	}
	
	@Bean
	public RegistrationServiceImp registrationService(StudentRepositoryImp studentRepository, CourseRepositoryImp courseRepository, RegistrationRepositoryImp registrationRepository) {
		return new RegistrationServiceImp(studentRepository, courseRepository, registrationRepository);
	}
	
}
