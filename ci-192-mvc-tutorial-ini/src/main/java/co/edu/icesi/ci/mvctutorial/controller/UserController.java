package co.edu.icesi.ci.mvctutorial.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.icesi.ci.mvctutorial.model.User;
import co.edu.icesi.ci.mvctutorial.service.UserService;

@Controller
public class UserController {
	
	private UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/users/")
	public String listUser(Model model) {
		model.addAttribute("users", userService.findAll());
		return "users/index";
	}
	
	@GetMapping("/add/")
	public String addUser(Model model) {
		model.addAttribute("gender", userService.getGenders());
		model.addAttribute("type", userService.getTypes());
		model.addAttribute("user", new User());
		return "users/add";
	}
	
	@PostMapping("/users/add")
	public String addusers() {
		return "users";
	}
	
}











/*
 * planeacion
 * transporte
 * almacenamiento
 * compras
 * conversion
 * */
