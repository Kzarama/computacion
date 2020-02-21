package com.example.LifeCycle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@SuppressWarnings("unused")
public class LifeCycleApplication {
	
	@Autowired
	private MyBeanY beanY1;
	@Autowired
	private MyBeanY beanY2;
	
	public static void main(String[] args) {
		SpringApplication.run(LifeCycleApplication.class, args);
	}

}
