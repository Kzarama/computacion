package com.pack.taller.service;

import org.springframework.stereotype.Service;

import com.pack.taller.model.TsscTopic;

@Service
public interface TopicService {
	
	public boolean saveTopic(TsscTopic topic) throws Exception;
	public boolean editTopic(TsscTopic topic, Long id) throws Exception;
	
}