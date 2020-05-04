package com.TP.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.TP.demo.model.TsscGame;
import com.TP.demo.model.TsscGame.ValidatedGame;
import com.TP.demo.model.TsscTopic;
import com.TP.demo.model.TsscTopic.ValidatedTopic;
import com.TP.demo.services.ServicioTema;

@Controller
public class ControladorTema {
	
	@Autowired
	private ServicioTema servicioTema;
	
	@GetMapping("/tema/")
	public String index(Model model) {
		model.addAttribute("temas", servicioTema.findAll());
		return "tema/index";
	}
	
	@GetMapping("/tema/adicionar")
	public String saveTopicGet(Model model) {
		model.addAttribute("tema", new TsscTopic());
		return "tema/guardar";
	}
	
	@PostMapping("/tema/adicionar")
	public String saveTopicPost(@Validated(ValidatedTopic.class) TsscTopic topic, BindingResult bindingResult, @RequestParam(value = "action", required = true) String action, Model model) {
		if (action != null && !action.equals("Cancelar")) {
			model.addAttribute("name", topic.getName());
			model.addAttribute("description", topic.getDescription());
			model.addAttribute("defaultGroups", topic.getDefaultGroups());
			model.addAttribute("defaultSprints", topic.getDefaultSprints());
			model.addAttribute("groupPrefix", topic.getGroupPrefix());
			if (bindingResult.hasErrors()) {
				return "tema/guardar";
			}
			try {
				servicioTema.addTopic(topic);
				return "redirect:/tema/";
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "redirect:/tema/";
	}
	
	@GetMapping("/tema/editar/{id}")
	public String editTopic(@PathVariable("id") long id, Model model) {
		TsscTopic tema = servicioTema.findById(id);
		model.addAttribute("tema", tema);
		model.addAttribute("name", tema.getName());
		model.addAttribute("description", tema.getDescription());
		model.addAttribute("defalutGroups", tema.getDefaultGroups());
		model.addAttribute("defaultSprints", tema.getDefaultSprints());
		model.addAttribute("groupPrefix", tema.getGroupPrefix());
		return "tema/editar";
	}
	
	@PostMapping("/tema/editar/{id}")
	public String editTopic(@PathVariable("id") long id, @RequestParam(value ="action", required = true) String action, @Validated(ValidatedTopic.class) TsscTopic tsscTopic, BindingResult bindingResult, Model model) {
		if (action != null && !action.equals("Cancelar")) {
			if (bindingResult.hasErrors()) {
				model.addAttribute("tema", tsscTopic);
				model.addAttribute("name", tsscTopic.getName());
				model.addAttribute("description", tsscTopic.getDescription());
				model.addAttribute("defaultGroups", tsscTopic.getDefaultGroups());
				model.addAttribute("defaultSprints", tsscTopic.getDefaultSprints());
				model.addAttribute("groupPrefix", tsscTopic.getGroupPrefix());
				return "tema/editar";
			} else {
				try {
					servicioTema.editTopic(id, tsscTopic);
				} catch (Exception e) {
				}
			}
		}
		return "redirect:/tema/";
	}
	
	@GetMapping("/tema/borrar/{id}")
	public String deleteTopic(@PathVariable("id") long id) {
		servicioTema.deleteTopic(id);
		return "redirect:/tema/";
	}
	
}
