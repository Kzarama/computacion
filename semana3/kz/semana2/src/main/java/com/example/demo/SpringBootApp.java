package com.example.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.Lifecycle;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class SpringBootApp {
	
	private MyBeanY beanY;
	
	public static void main(String[] args) {
		ConfigurableApplicationContext app = new AnnotationConfigApplicationContext(SpringBootApp.class);
		app.close();
	}
	
	public SpringBootApp(MyBeanY beanY) {
		this.beanY = beanY;
	}
	
}
