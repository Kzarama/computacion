package com.example.demo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import lombok.extern.java.Log;

@Log
@Component
public class PostProcesadorBean implements BeanPostProcessor {
	
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		if (bean.getClass().getPackage().toString().equals("package com.example.demo")) {
			log.info("Mi Bean: " + beanName);
		}
		return bean;
	}
	
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if (!(bean.getClass().getPackage().toString().equals("package com.example.demo"))) {
			log.info("Bean de Spring: " + beanName);
		}
		return bean;
	}
	
}
