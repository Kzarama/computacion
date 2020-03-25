package co.edu.icesi.ci.mvctutorial.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import co.edu.icesi.ci.mvctutorial.model.User;
import co.edu.icesi.ci.mvctutorial.service.UserService;

@Controller
public class UserController {
	
	private UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/users/edit/")
	public String editUser(Model model, User user) {
		return "/users/edit";
	}
	
	@GetMapping("/users/")
	public String listUser(Model model) {
		model.addAttribute("users", userService.findAll());
		return "users/index";
	}
	
	@GetMapping("/users/add/")
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
	
	@GetMapping("/users/del/")
	public String delUSers() {
		return "users/add";
	}
	
}
