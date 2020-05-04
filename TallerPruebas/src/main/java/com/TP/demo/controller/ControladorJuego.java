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
import com.TP.demo.model.TsscTopic;
import com.TP.demo.model.TsscGame.ValidatedGame;
import com.TP.demo.services.ServicioJuego;
import com.TP.demo.services.ServicioTema;

@Controller
public class ControladorJuego {

	@Autowired
	private ServicioJuego  servicioJuego;
	
	@Autowired
	private ServicioTema servicioTema;
	
	@GetMapping("/juego/")
	public String index(Model model) {
		model.addAttribute("juegos", servicioJuego.findAll());
		return "juego/index";
	}
	
	@GetMapping("/juego/adicionar")
	public String guardarJuego(Model model) {
		model.addAttribute("juego", new TsscGame());
		return "juego/guardar";
	}
	
	@PostMapping("/juego/adicionar")
	public String guardarJuego(@Validated(ValidatedGame.class) TsscGame tsscGame, BindingResult bindingResult, @RequestParam(value="action", required=true) String action, Model model) {
		if (action != null && !action.equals("Cancelar")) {
			model.addAttribute("name", tsscGame.getName());
			model.addAttribute("scheduledDate", tsscGame.getScheduledDate());
			model.addAttribute("scheduledTime", tsscGame.getScheduledTime());
			model.addAttribute("nGroups", tsscGame.getNGroups());
			model.addAttribute("nSprints", tsscGame.getNSprints());
			model.addAttribute("startTime", tsscGame.getStartTime());
			model.addAttribute("tsscTopic", tsscGame.getTsscTopic());
			if (bindingResult.hasErrors()) {
				model.addAttribute("juego", new TsscGame());
				return "juego/guardar";
			}
			try {
				servicioJuego.addGame(tsscGame);
				return "redirect:/juego/";
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "redirect:/juego/";
	}
	
	@GetMapping("/juego/editar/{id}")
	public String editGame(@PathVariable("id") long id, Model model) {
		TsscGame juego = servicioJuego.findById(id);
		model.addAttribute("juego", juego);
		model.addAttribute("name", juego.getName());
		model.addAttribute("scheduledDate", juego.getScheduledDate());
		model.addAttribute("scheduledTime", juego.getScheduledTime());
		model.addAttribute("nGroups", juego.getNGroups());
		model.addAttribute("nSprints", juego.getNSprints());
		model.addAttribute("startTime", juego.getStartTime());
		model.addAttribute("topics", servicioTema.findAll());
		return "juego/editar";
	}
	
	@PostMapping("/juego/editar/{id}")
	public String editGame(@PathVariable("id") long id, @RequestParam(value ="action", required = true) String action, @Validated(ValidatedGame.class) TsscGame tsscGame, BindingResult bindingResult, Model model) {
		if (action != null && !action.equals("Cancelar")) {
			if (bindingResult.hasErrors()) {
				model.addAttribute("topics", servicioTema.findAll());
				model.addAttribute("name", tsscGame.getName());
				model.addAttribute("scheduledDate", tsscGame.getScheduledDate());
				model.addAttribute("scheduledTime", tsscGame.getScheduledTime());
				model.addAttribute("nGroups", tsscGame.getNGroups());
				model.addAttribute("nSprints", tsscGame.getNSprints());
				model.addAttribute("startTime", tsscGame.getStartTime());
				return "juego/editar";
			} else {
				try {
					servicioJuego.editGame(id, tsscGame);
				} catch (Exception e) {
				}
			}
		}
		return "redirect:/juego/";
	}
	
	@GetMapping("/juego/borrar/{id}")
	public String deleteGame(@PathVariable("id") long id) {
		servicioJuego.deleteGame(id);
		return "redirect:/juego/";
	}
	
	@GetMapping("/juego/historia/{id}")
	public String historia(@PathVariable("id") long id, Model model) {
		TsscStory tsscStory = servicioJuego.getGame(id).getTsscStories().get(0);
		if (tsscStory != null ) {
			model.addAttribute("story", tsscStory);
			model.addAttribute("id", tsscStory.getId());
			model.addAttribute("description", tsscStory.getDescription());
			model.addAttribute("businessValue", tsscStory.getBusinessValue());
			model.addAttribute("initialSprint", tsscStory.getInitialSprint());
			model.addAttribute("priority", tsscStory.getPriority());
			model.addAttribute("games", servicioJuego.findAll());
			return "historia/editar";
		} else {
			return "juego/index";
		}
	}
	
	@GetMapping("/juego/tema/{id}")
	public String tema(@PathVariable("id") long id, Model model) {
		TsscTopic tema = servicioJuego.getGame(id).getTsscTopic();
		if(tema != null) {
			model.addAttribute("tema", tema);
			model.addAttribute("name", tema.getName());
			model.addAttribute("description", tema.getDescription());
			model.addAttribute("defalutGroups", tema.getDefaultGroups());
			model.addAttribute("defaultSprints", tema.getDefaultSprints());
			model.addAttribute("groupPrefix", tema.getGroupPrefix());
			return "tema/editar";
		} else {
			return "juego/index";
		}
	}
}
