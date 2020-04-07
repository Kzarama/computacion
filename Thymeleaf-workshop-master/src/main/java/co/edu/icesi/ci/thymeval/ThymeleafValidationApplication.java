package co.edu.icesi.ci.thymeval;

import java.time.LocalDate;

import org.apache.tomcat.jni.Time;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import co.edu.icesi.ci.thymeval.model.UserApp;
import co.edu.icesi.ci.thymeval.model.UserGender;
import co.edu.icesi.ci.thymeval.model.UserType;
import co.edu.icesi.ci.thymeval.service.UserService;

@SpringBootApplication
public class ThymeleafValidationApplication {

	@Bean
	public Java8TimeDialect java8TimeDialect() {
		return new Java8TimeDialect();
	}

	public static void main(String[] args) {
		
		ConfigurableApplicationContext c = SpringApplication.run(ThymeleafValidationApplication.class, args);
		UserService u = c.getBean(UserService.class);
		UserApp user1 = new UserApp();
		user1.setName("Juan");
		user1.setEmail("jc@gmail.com");
		user1.setType(UserType.doctor);
		user1.setGender(UserGender.masculine);
		user1.setUsername("1");
		user1.setPassword("{noop}1");
		user1.setBirthDate(LocalDate.of(1999, 10, 12));
		u.save(user1);
		UserApp user2 = new UserApp();
		user2.setName("Ana");
		user2.setUsername("2");
		user2.setPassword("{noop}2");
		user2.setEmail("ana@gmail.com");
		user2.setType(UserType.patient);
		user2.setGender(UserGender.femenine);
		user2.setBirthDate(LocalDate.of(1989, 10, 12));
		u.save(user2);
		UserApp user3 = new UserApp();
		user3.setName("cafka");
		user3.setUsername("3");
		user3.setPassword("{noop}3");
		user3.setEmail("c@gmail.com");
		user3.setType(UserType.ADMIN);
		user3.setGender(UserGender.femenine);
		user3.setBirthDate(LocalDate.of(1989, 10, 12));
		u.save(user3);
	}

	
	
}
