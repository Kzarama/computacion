package co.edu.icesi.ci.injectionexample1.boot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.edu.icesi.ci.injectionexample1.repositories.CourseRepositoryImp;
import co.edu.icesi.ci.injectionexample1.repositories.RegistrationRepositoryImp;
import co.edu.icesi.ci.injectionexample1.repositories.StudentRepositoryImp;
import co.edu.icesi.ci.injectionexample1.service.RegistrationServiceImp;

@Configuration
public class AppConfig {
	
	CourseRepositoryImp courseRepository = new CourseRepositoryImp();
	RegistrationRepositoryImp registrationRepository = new RegistrationRepositoryImp();
	StudentRepositoryImp studentRepository = new StudentRepositoryImp();
	
	@Bean
	public CourseRepositoryImp courseRepository() {
		return courseRepository;
	}
	
	@Bean
	public RegistrationRepositoryImp registrationRepository() {
		return registrationRepository;
	}
	
	@Bean
	public StudentRepositoryImp studentRepository() {
		return studentRepository;
	}
	
	@Bean
	public RegistrationServiceImp registrationService() {
		return new RegistrationServiceImp(studentRepository, courseRepository, registrationRepository);
	}
	
}
