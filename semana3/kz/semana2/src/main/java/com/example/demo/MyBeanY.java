package com.example.demo;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import lombok.extern.java.Log;

@Log
@Lazy
@Component
public class MyBeanY {
	
	private void printMessage() {
		log.info("work");
	}
	
}
