package com.TP.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControladorAdministrador {
	
	@GetMapping("/login")
	public String loginPrincipal() {
		return "/MyLogin";
	}
	
}
