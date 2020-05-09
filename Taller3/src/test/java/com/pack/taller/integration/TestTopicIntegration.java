package com.pack.taller.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Optional;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.pack.taller.model.TsscTopic;
import com.pack.taller.service.TopicServiceImp;

@Rollback
@SpringBootTest
public class TestTopicIntegration {
	
	@Autowired(required = true)
	private TopicServiceImp topicService;
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@DisplayName("all is ok")
	public void testOk() {
		TsscTopic topic = new TsscTopic();
		topic.setDefaultGroups(1);
		topic.setDefaultSprints(1);
		try {
			assertTrue(topicService.saveTopic(topic));
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@DisplayName("topic is null")
	public void testTopicNull() {
		TsscTopic topic = null;
		assertThrows(Exception.class, () -> topicService.saveTopic(topic));
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@DisplayName("There aren't sprints")
	public void testSprintsEmpty() {
		TsscTopic topic = new TsscTopic();
		topic.setDefaultGroups(1);
		topic.setDefaultSprints(0);
		assertThrows(Exception.class, () -> {
			topicService.saveTopic(topic);
		});
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@DisplayName("There aren't groups")
	public void testGroupsEmpty() {
		TsscTopic topic = new TsscTopic();
		topic.setDefaultGroups(0);
		topic.setDefaultSprints(1);
		assertThrows(Exception.class, () -> {
			topicService.saveTopic(topic);
		});
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@DisplayName("edit test")
	public void testEdit() {
		TsscTopic topic = new TsscTopic();
		topic.setDefaultGroups(1);
		topic.setDefaultSprints(1);
		try {
			assertTrue(topicService.saveTopic(topic));
			topic.setName("topic2");
			topicService.editTopic(topic, topic.getId());
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		Optional<TsscTopic> optional = Optional.of(topic);
		assertEquals(optional.get().getName(), "topic2");
	}
	
}
