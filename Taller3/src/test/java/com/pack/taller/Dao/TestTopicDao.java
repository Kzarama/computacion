package com.pack.taller.Dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.aspectj.internal.lang.annotation.ajcDeclareAnnotation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.pack.taller.dao.GameDao;
import com.pack.taller.dao.TopicDao;
import com.pack.taller.model.TsscGame;
import com.pack.taller.model.TsscTopic;

@SpringBootTest
@DisplayName("TopicDao")
public class TestTopicDao {
	
	@Autowired
	private TopicDao topicDao;
	
	private TsscTopic topic;
	
	@BeforeEach
	public void initiallize() {
		topic = new TsscTopic();
		topic.setDefaultGroups(1);
		topic.setDefaultSprints(1);
		topic.setDescription("des");
		topic.setGroupPrefix("group");
		topic.setName("kz");
	}
	
	@Test
	@Transactional
	@DisplayName("Save")
	public void save() {
		assertTrue(topicDao.findAll().isEmpty());
		topicDao.save(topic);
		assertFalse(topicDao.findAll().isEmpty());
	}

	@Test
	@Transactional
	@DisplayName("Update")
	public void update() {
		assertTrue(topicDao.findAll().isEmpty());
		topicDao.save(topic);
		assertEquals("kz", topicDao.findById(topic.getId()).getName());
		topic.setName("k");
		topicDao.save(topic);
		assertEquals("k", topicDao.findById(topic.getId()).getName());
	}

	@Test
	@Transactional
	@DisplayName("Delete")
	public void delete() {
		assertTrue(topicDao.findAll().isEmpty());
		topicDao.save(topic);
		assertFalse(topicDao.findAll().isEmpty());
		topicDao.delete(topic);
		assertTrue(topicDao.findAll().isEmpty());
	}

	@Test
	@Transactional
	@DisplayName("Find by id")
	public void findById() {
		topicDao.save(topic);
		assertEquals("kz", topicDao.findById(topic.getId()).getName());
	}

	@Test
	@Transactional
	@DisplayName("Find all")
	public void findAll() {
		topicDao.save(topic);
		assertEquals("kz", topicDao.findAll().get(0).getName());
	}

	@Test
	@Transactional
	@DisplayName("Find by name")
	public void findByName() {
		topicDao.save(topic);
		assertEquals("kz", topicDao.findByName(topic.getName()).get(0).getName());
	}

	@Test
	@Transactional
	@DisplayName("Find by description")
	public void findByDescription() {
		topicDao.save(topic);
		assertEquals("kz", topicDao.findByDescription(topic.getDescription()).get(0).getName());
	}
	
}
