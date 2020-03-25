package com.packt.springboot.blogmania.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
	
	@GetMapping("/")
	public String renderWelcome() {
		return "hello";
	}
	
}
