package co.edu.icesi.ci.thymeval.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.icesi.ci.thymeval.model.UserApp;
import co.edu.icesi.ci.thymeval.model.UserApp.firstValidator;
import co.edu.icesi.ci.thymeval.model.UserApp.secondValidator;
import co.edu.icesi.ci.thymeval.model.UserApp.updateValidator;
import co.edu.icesi.ci.thymeval.service.UserService;

@Controller
public class UserController {

	UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/users/")
	public String indexUser(Model model) {
		model.addAttribute("users", userService.findAll());
		return "users/index";
	}
	
	@GetMapping("/index")
	public String index() {
		return "/index";
	}
	
	@GetMapping("/users/add")
	public String addUser2(Model model) {
		model.addAttribute("userApp", new UserApp());
		return "users/add-user1";
	}
	
	@GetMapping("/login")
	public String loginPrincipal() {
		return "/loginThymeleaf";
	}
	
	@PostMapping("/users/add")
	public String saveUser(@Validated({firstValidator.class}) UserApp user, BindingResult bindingResult, @RequestParam(value = "action", required = true) String action, Model model) {			
		model.addAttribute("genders", userService.getGenders());
		model.addAttribute("types", userService.getTypes());
		if(bindingResult.hasErrors()) {
			return "users/add-user1";
		}
		
		if (!action.equals("Cancel"))
			userService.save(user);
			return "users/add-user2";
	}
	
	@PostMapping("/users/add1")
	public String saveUser1(@Validated({secondValidator.class}) UserApp user, BindingResult bindingResult, @RequestParam(value = "action", required = true) String action, Model model) {
		model.addAttribute("genders", userService.getGenders());
		model.addAttribute("types", userService.getTypes());
		Optional<UserApp> uOptional = userService.findById(user.getId());
		UserApp user2 = uOptional.get();
		user.setPassword(user2.getPassword());
		user.setUsername(user2.getUsername());
		user.setBirthDate(user2.getBirthDate());
		
		if(bindingResult.hasErrors()) {
			return "users/add-user1";
		}
		
		if (!action.equals("Cancel"))
			userService.save(user);
		return "redirect:/users/";
	}

	@GetMapping("/users/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Optional<UserApp> user = userService.findById(id);
		if (user == null)
			throw new IllegalArgumentException("Invalid user Id:" + id);
		model.addAttribute("userApp", user.get());
		model.addAttribute("genders", userService.getGenders());
		model.addAttribute("types", userService.getTypes());
		return "users/update-user";
	}

	@PostMapping("/users/edit/{id}")
	public String updateUser(@Validated(updateValidator.class) UserApp user, BindingResult bindingResult, @PathVariable("id") long id,
			@RequestParam(value = "action", required = true) String action, Model m) {
		if (action != null && !action.equals("Cancel")) {
		if(bindingResult.hasErrors()) {
			m.addAttribute("types", userService.getTypes());
			m.addAttribute("genders", userService.getGenders());
			return "users/update-user";
		}
		UserApp user2 = userService.findById(user.getId()).get();
			user.setPassword(user2.getPassword());
			userService.save(user);
		}
		return "redirect:/users/";
	}

	@GetMapping("/users/del/{id}")
	public String deleteUser(@PathVariable("id") long id) {
		UserApp user = userService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		userService.delete(user);
		return "redirect:/users/";
	}
}
