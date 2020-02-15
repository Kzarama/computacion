package com.example.LifeCycle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class LifeCycleApplication {
	
	private MyBeanY beanY1;
	private MyBeanY beanY2;
	
	@Autowired
	public LifeCycleApplication(MyBeanY beanY1, MyBeanY beanY2) {
		this.beanY1 = beanY1;
		this.beanY2 = beanY2;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(LifeCycleApplication.class, args);
	}

}
