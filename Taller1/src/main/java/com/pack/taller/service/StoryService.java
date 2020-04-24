package com.pack.taller.service;

import org.springframework.stereotype.Service;

import com.pack.taller.model.TsscStory;

@Service
public interface StoryService {
	
	public boolean saveStory(TsscStory story) throws Exception;
	public boolean editStory(TsscStory story, Long id) throws Exception;
	
}
