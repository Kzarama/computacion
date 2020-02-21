package com.example.LifeCycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import lombok.extern.java.Log;

@Log
@Component
public class PostProcesorBean implements BeanPostProcessor {
	
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		if (bean.getClass().getCanonicalName().startsWith("com.example.LifeCycle")) {
//		if (bean.getClass().getPackage().toString().equals("package com.example.LifeCycle")) {
			log.info("My bean: " + beanName);
		}
		return bean;
	}
	
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if (!(bean.getClass().getCanonicalName().startsWith("com.example.LifeCycle"))) {
//		if (!(bean.getClass().getPackage().toString().equals("package com.example.LifeCycle"))) {
			log.info("Spring bean: " + beanName);
		}
		return bean;
	}
	
}
