package com.pack.taller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pack.taller.model.TsscGame;
import com.pack.taller.model.TsscTopic;
import com.pack.taller.model.TsscGame.ValidatedGame;
import com.pack.taller.model.TsscTopic.ValidatedTopic;
import com.pack.taller.service.GameServiceImp;
import com.pack.taller.service.TopicServiceImp;

@Controller
public class GameController {
	
	@Autowired
	private GameServiceImp gameService;
	
	@Autowired
	private TopicServiceImp topicService;
	
	@GetMapping("/game/")
	public String index(Model model) {
		model.addAttribute("games", gameService.findAll());
		return "game/index";
	}
	
	@GetMapping("/game/add")
	public String saveGameGet(Model model) {
		model.addAttribute("tsscGame", new TsscGame());
		model.addAttribute("topics", topicService.findAll());
		return "game/save_game";
	}
	
	@PostMapping("/game/add")
	public String saveGamePost(@Validated(ValidatedGame.class) TsscGame game, BindingResult bindingResult, @RequestParam(value = "action", required = true) String action, Model model) {
		if (action != null && !action.equals("Cancelar")) {
			model.addAttribute("name", game.getName());
			model.addAttribute("scheduledDate", game.getScheduledDate());
			model.addAttribute("scheduledTime", game.getScheduledTime());
			model.addAttribute("nGroups", game.getNGroups());
			model.addAttribute("nSprints", game.getNSprints());
			model.addAttribute("startTime", game.getStartTime());
			if (bindingResult.hasErrors()) {
				return "game/save_game";
			}
			try {
				gameService.saveGame(game);
				return "redirect:/game/";
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "redirect:/game/";
	}
	
	@GetMapping("/game/edit/{id}")
	public String editGame(@PathVariable("id") long id, Model model) {
		TsscGame game = gameService.findById(id);
		model.addAttribute("game", game);
		model.addAttribute("name", game.getName());
		model.addAttribute("scheduledDate", game.getScheduledDate());
		model.addAttribute("scheduledTime", game.getScheduledTime());
		model.addAttribute("nGroups", game.getNGroups());
		model.addAttribute("nSprints", game.getNSprints());
		model.addAttribute("startTime", game.getStartTime());
		model.addAttribute("topics", topicService.findAll());
		return "game/edit_game";
	}
	
	@PostMapping("/game/edit/{id}")
	public String editGame(@PathVariable("id") long id, @RequestParam(value ="action", required = true) String action, @Validated(ValidatedGame.class) TsscGame game, BindingResult bindingResult, Model model) {
		if (action != null && !action.equals("Cancelar")) {
			if (bindingResult.hasErrors()) {
				model.addAttribute("topics", topicService.findAll());
				model.addAttribute("name", game.getName());
				model.addAttribute("scheduledDate", game.getScheduledDate());
				model.addAttribute("scheduledTime", game.getScheduledTime());
				model.addAttribute("nGroups", game.getNGroups());
				model.addAttribute("nSprints", game.getNSprints());
				model.addAttribute("startTime", game.getStartTime());
				return "game/edit_game";
			} else {
				try {
					gameService.editGame(game, id);
				} catch (Exception e) {
				}
			}
		}
		return "redirect:/game/";
	}
	
	@GetMapping("/game/delete/{id}")
	public String deleteGame(@PathVariable("id") long id) {
		gameService.deleteGame(id);
		return "redirect:/game/";
	}
	
	@GetMapping("/game/stories/{id}")
	public String stories(@PathVariable("id") long id, Model model) {
		TsscGame game = gameService.findById(id);
		model.addAttribute("tsscGame", game);
		model.addAttribute("stories", game.getTsscStories());
		return "game/stories";
	}
	
	@GetMapping("/game/topic/{id}")
	public String topic(@PathVariable("id") long id, Model model) {
		TsscGame game = gameService.findById(id);
		model.addAttribute("topics", game.getTsscTopic());
		return "game/topic";
	}
	
}
