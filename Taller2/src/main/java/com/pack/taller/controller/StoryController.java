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

import com.pack.taller.model.TsscStory;
import com.pack.taller.model.TsscStory.ValidatedStory;
import com.pack.taller.service.GameServiceImp;
import com.pack.taller.service.StoryServiceImp;

@Controller
public class StoryController {
	
	@Autowired
	private StoryServiceImp storyService;
	
	@Autowired
	private GameServiceImp gameService;
	
	@GetMapping("/story/")
	public String index(Model model) {
		model.addAttribute("stories", storyService.findAll());
		return "story/index";
	}
	
	@GetMapping("/story/add")
	public String saveStoryGet(Model model) {
		model.addAttribute("tsscStory", new TsscStory());
		model.addAttribute("games", gameService.findAll());
		return "story/save_story";
	}
	
	@PostMapping("/story/add")
	public String saveStoryPost(@Validated(ValidatedStory.class) TsscStory story, BindingResult bindingResult, @RequestParam(value = "action", required = true) String action, Model model) {
		if (action != null && !action.equals("Cancelar")) {
			model.addAttribute("games", gameService.findAll());
			model.addAttribute("id", story.getId());
			model.addAttribute("description", story.getDescription());
			model.addAttribute("businessValue", story.getBusinessValue());
			model.addAttribute("initialSprint", story.getInitialSprint());
			model.addAttribute("priority", story.getPriority());
			if (bindingResult.hasErrors()) {
				return "story/save_story";
			}
			try {
				storyService.saveStory(story);
				return "redirect:/story/";
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "redirect:/story/";
	}
	
	@GetMapping("/story/edit/{id}")
	public String editStory(@PathVariable("id") long id, Model model) {
		TsscStory story = storyService.findById(id);
		model.addAttribute("story", story);
		model.addAttribute("id", story.getId());
		model.addAttribute("description", story.getDescription());
		model.addAttribute("businessValue", story.getBusinessValue());
		model.addAttribute("initialSprint", story.getInitialSprint());
		model.addAttribute("priority", story.getPriority());
		model.addAttribute("games", gameService.findAll());
		return "story/edit_story";
	}
	
	@PostMapping("/story/edit/{id}")
	public String editStory(@PathVariable("id")long id, @RequestParam(value = "action", required = true) String action, @Validated(ValidatedStory.class) TsscStory story, BindingResult bindingResult, Model model) {
		if (action != null && !action.equals("Cancelar")) {
			if (bindingResult.hasErrors()) {
				model.addAttribute("games", gameService.findAll());
				model.addAttribute("id", story.getId());
				model.addAttribute("description", story.getDescription());
				model.addAttribute("businessValue", story.getBusinessValue());
				model.addAttribute("initialSprint", story.getInitialSprint());
				model.addAttribute("priority", story.getPriority());
				return "story/edit_story";
			} else {
				try {
					storyService.editStory(story, id);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return "redirect:/story/";
	}
	
	@GetMapping("/story/delete/{id}")
	public String deleteStory(@PathVariable("id") long id) {
		storyService.deleteStory(id);
		return "redirect:/story/";
	}
	
	@GetMapping("/story/game/{id}")
	public String game(@PathVariable("id") long id, Model model) {
		TsscStory story = storyService.findById(id);
		model.addAttribute("games", story.getTsscGame());
		return "story/game";
	}
	
}
