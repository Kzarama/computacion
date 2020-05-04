package com.pack.taller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pack.taller.dao.TopicDao;
import com.pack.taller.model.TsscTopic;

@Service
public class TopicServiceImp implements TopicService {
	
	@Autowired
	private TopicDao topicDao;
	
	@Override
	public boolean saveTopic(TsscTopic topic) throws Exception {
		if (topic != null && topic.getDefaultGroups() > 0 && topic.getDefaultSprints() > 0) {
			topicDao.save(topic);
			return true;
		} else {
			throw new Exception();
		}
	}

	@Override
	public boolean editTopic(TsscTopic topic, Long id) throws Exception {
		if (topic != null && topic.getDefaultGroups() > 0 && topic.getDefaultSprints() > 0) {
			topicDao.delete(findById(id));
			topicDao.save(topic);
			return true;
		} else {
			 throw new Exception();
		}
	}

	@Override
	public Iterable<TsscTopic> findAll() {
		return topicDao.findAll();
	}

	@Override
	public TsscTopic findById(Long id) {
		return topicDao.findById(id);
	}

	@Override
	public void deleteTopic(Long id) {
		topicDao.delete(findById(id));;
	}
	
}
