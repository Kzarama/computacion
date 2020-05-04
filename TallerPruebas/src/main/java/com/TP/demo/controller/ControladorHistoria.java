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
import com.TP.demo.model.TsscStory;
import com.TP.demo.model.TsscStory.ValidatedStory;
import com.TP.demo.model.TsscTopic.ValidatedTopic;
import com.TP.demo.services.ServicioHistoria;
import com.TP.demo.services.ServicioJuego;
import com.TP.demo.services.ServicioTema;

@Controller
public class ControladorHistoria {
	
	@Autowired
	private ServicioHistoria servicioHistoria;
	
	@Autowired
	private ServicioJuego servicioJuego;
	
	@Autowired
	private ServicioTema servicioTema;
	
	@GetMapping("/historia/")
	public String index(Model model) {
		model.addAttribute("historias", servicioHistoria.findAll());
		return "historia/index";
	}
	
	@GetMapping("/historia/adicionar")
	public String guardarJuego(Model model) {
		model.addAttribute("historia", new TsscStory());
		model.addAttribute("juegos", servicioJuego.findAll());
		return "historia/guardar";
	}
	
	
	@PostMapping("/historia/adicionar")
	public String guardarJuego(@Validated(ValidatedTopic.class) TsscStory tsscStory, BindingResult bindingResult, @RequestParam(value="action", required=true) String action, Model model) {
		if (action != null && !action.equals("Cancelar")) {
			model.addAttribute("jeugos", servicioJuego.findAll());
			model.addAttribute("id", tsscStory.getId());
			model.addAttribute("description", tsscStory.getDescription());
			model.addAttribute("businessValue", tsscStory.getBusinessValue());
			model.addAttribute("initialSprint", tsscStory.getInitialSprint());
			model.addAttribute("priority", tsscStory.getPriority());
			if (bindingResult.hasErrors()) {
				return "historia/guardar";
			}
			try {
				servicioHistoria.addStory(tsscStory);
				return "redirect:/historia/";
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "redirect:/historia/";
	}
	
	@GetMapping("/historia/editar/{id}")
	public String editarHistoria(@PathVariable("id") long id, Model model) {
		TsscStory tsscStory = servicioHistoria.findById(id);
		model.addAttribute("historia", tsscStory);
		model.addAttribute("id", tsscStory.getId());
		model.addAttribute("description", tsscStory.getDescription());
		model.addAttribute("businessValue", tsscStory.getBusinessValue());
		model.addAttribute("initialSprint", tsscStory.getInitialSprint());
		model.addAttribute("priority", tsscStory.getPriority());
		model.addAttribute("juegos", servicioJuego.findAll());
		return "historia/editar";
	}
	
	@PostMapping("/historia/editar/{id}")
	public String editarHistoria(@PathVariable("id") long id, @RequestParam(value="action", required=true) String action, @Validated(ValidatedStory.class) TsscStory tsscStory, BindingResult bindingResult, Model model) {
		if (action != null && !action.equals("Cancelar")) {
			if (bindingResult.hasErrors()) {
				model.addAttribute("juegos", servicioJuego.findAll());
				model.addAttribute("id", tsscStory.getId());
				model.addAttribute("description", tsscStory.getDescription());
				model.addAttribute("businessValue", tsscStory.getBusinessValue());
				model.addAttribute("initialSprint", tsscStory.getInitialSprint());
				model.addAttribute("priority", tsscStory.getPriority());
				return "historia/editar";
			} else {
				try {
					servicioHistoria.editStory(id, tsscStory);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return "redirect:/historia/";
	}
	
	@GetMapping("/historia/borrar/{id}")
	public String deleteStory(@PathVariable("id") long id) {
		servicioHistoria.deleteStory(id);
		return "redirect:/historia/";
	}
	
	@GetMapping("/historia/juego/{id}")
	public String juego(@PathVariable("id") long id, Model model) {
		TsscGame juego = servicioHistoria.getStory(id).getTsscGame();
		if (juego != null) {
			model.addAttribute("juego", juego);
			model.addAttribute("name", juego.getName());
			model.addAttribute("scheduledDate", juego.getScheduledDate());
			model.addAttribute("scheduledTime", juego.getScheduledTime());
			model.addAttribute("nGroups", juego.getNGroups());
			model.addAttribute("nSprints", juego.getNSprints());
			model.addAttribute("startTime", juego.getStartTime());
			model.addAttribute("topics", servicioTema.findAll());
			return "juego/editar";
		} else {
			return "historia/index";
		}
	}
	
}
