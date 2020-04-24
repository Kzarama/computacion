package com.pack.taller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pack.taller.model.TsscTopic;
import com.pack.taller.repository.TopicRepository;

@Service
public class TopicServiceImp implements TopicService {
	
	@Autowired
	private TopicRepository repo;
	
	@Override
	public boolean saveTopic(TsscTopic topic) throws Exception {
		if (topic != null && topic.getDefaultGroups() > 0 && topic.getDefaultSprints() > 0) {
			repo.save(topic);
			return true;
		} else {
			throw new Exception();
		}
	}

	@Override
	public boolean editTopic(TsscTopic topic, Long id) throws Exception {
		if (topic != null && topic.getDefaultGroups() > 0 && topic.getDefaultSprints() > 0) {
			repo.deleteById(id);
			repo.save(topic);
			return true;
		} else {
			 throw new Exception();
		}
	}
	
}
