package com.testNg.spring.boot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.testNg.spring.repositories.OrderRepositoryImp;
import com.testNg.spring.services.OrderServiceImp;

@Configuration
@ComponentScan("com.testNg.spring.services")
public class AppConfig {
	
	@Bean
	public OrderRepositoryImp orderRepository() {
		return new OrderRepositoryImp();
	}
	
	@Bean
	public OrderServiceImp orderService() {
		return new OrderServiceImp();
	}
	
}
