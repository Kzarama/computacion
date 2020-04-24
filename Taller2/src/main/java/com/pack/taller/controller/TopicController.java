package com.pack.taller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pack.taller.model.TsscTopic;
import com.pack.taller.model.TsscTopic.ValidatedTopic;
import com.pack.taller.service.TopicServiceImp;

@Controller
public class TopicController {
	
	@Autowired
	private TopicServiceImp topicService;
	
	@GetMapping("/topic/")
	public String index(Model model) {
		model.addAttribute("topics", topicService.findAll());
		return "topic/index";
	}
	
	@GetMapping("/topic/add")
	public String saveTopicGet(Model model) {
		model.addAttribute("tsscTopic", new TsscTopic());
		return "topic/save_topic";
	}
	
	@PostMapping("/topic/add")
	public String saveTopicPost(@Validated(ValidatedTopic.class) TsscTopic topic, BindingResult bindingResult, @RequestParam(value = "action", required = true) String action, Model model) {
		if (action != null && !action.equals("Cancelar")) {
			model.addAttribute("name", topic.getName());
			model.addAttribute("description", topic.getDescription());
			model.addAttribute("defaultGroups", topic.getDefaultGroups());
			model.addAttribute("defaultSprints", topic.getDefaultSprints());
			model.addAttribute("groupPrefix", topic.getGroupPrefix());
			if (bindingResult.hasErrors()) {
				return "topic/save_topic";
			}
			try {
				topicService.saveTopic(topic);
				return "redirect:/topic/";
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "redirect:/topic/";
	}
	
	@GetMapping("/topic/edit/{id}")
	public String editTopic(@PathVariable("id") long id, Model model) {
		TsscTopic topic = topicService.findById(id);
		model.addAttribute("topic", topic);
		model.addAttribute("name", topic.getName());
		model.addAttribute("description", topic.getDescription());
		model.addAttribute("defalutGroups", topic.getDefaultGroups());
		model.addAttribute("defaultSprints", topic.getDefaultSprints());
		model.addAttribute("groupPrefix", topic.getGroupPrefix());
		return "topic/edit_topic";
	}
	
	@PostMapping("/topic/edit/{id}")
	public String editTopic(@PathVariable("id") long id, @RequestParam(value ="action", required = true) String action, @Validated(ValidatedTopic.class) TsscTopic topic, BindingResult bindingResult, Model model) {
		if (action != null && !action.equals("Cancelar")) {
			if (bindingResult.hasErrors()) {
				model.addAttribute("name", topic.getName());
				model.addAttribute("description", topic.getDescription());
				model.addAttribute("defaultGroups", topic.getDefaultGroups());
				model.addAttribute("defaultSprints", topic.getDefaultSprints());
				model.addAttribute("groupPrefix", topic.getGroupPrefix());
				return "topic/edit_topic";
			} else {
				try {
					topicService.editTopic(topic, id);
				} catch (Exception e) {
				}
			}
		}
		return "redirect:/topic/";
	}
	
	@GetMapping("/topic/delete/{id}")
	public String deleteTopic(@PathVariable("id") long id) {
		topicService.deleteTopic(id);
		return "redirect:/topic/";
	}
	
}
