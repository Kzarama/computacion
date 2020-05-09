package com.pack.taller.Dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.pack.taller.dao.StoryDao;
import com.pack.taller.model.TsscStory;

@SpringBootTest
@DisplayName("StoryDao")
public class TestStoryDao {
	
	@Autowired
	private StoryDao storyDao;
	
	private TsscStory story;
	
	@BeforeEach
	public void initiallize(){
		story = new TsscStory();
		story.setAltDescripton("alt des");
		story.setBusinessValue(new BigDecimal(1));
		story.setDescription("des");
		story.setInitialSprint(new BigDecimal(1));
		story.setNumber(new BigDecimal(1));
		story.setPriority(new BigDecimal(1));
	}
	
	@Test
	@Transactional
	@DisplayName("Save")
	public void save() {
		assertTrue(storyDao.findAll().isEmpty());
		storyDao.save(story);
		assertFalse(storyDao.findAll().isEmpty());
	}
	
	@Test
	@Transactional
	@DisplayName("Update")
	public void update() {
		assertTrue(storyDao.findAll().isEmpty());
		storyDao.save(story);
		assertEquals("des", storyDao.findById(story.getId()).getDescription());
		story.setDescription("desc");
		storyDao.update(story);
		assertEquals("desc", storyDao.findById(story.getId()).getDescription());
	}

	@Test
	@Transactional
	@DisplayName("Delete")
	public void delete() {
		assertTrue(storyDao.findAll().isEmpty());
		storyDao.save(story);
		assertFalse(storyDao.findAll().isEmpty());
		storyDao.delete(story);
		assertTrue(storyDao.findAll().isEmpty());
	}

	@Test
	@Transactional
	@DisplayName("Find by id")
	public void findById() {
		storyDao.save(story);
		assertEquals("des", storyDao.findById(story.getId()).getDescription());
	}

	@Test
	@Transactional
	@DisplayName("Find all")
	public void findAll() {
		storyDao.save(story);
		TsscStory story2 = new TsscStory();
		story2 = new TsscStory();
		story2.setAltDescripton("alt des");
		story2.setBusinessValue(new BigDecimal(1));
		story2.setDescription("desc");
		story2.setInitialSprint(new BigDecimal(1));
		story2.setNumber(new BigDecimal(1));
		story2.setPriority(new BigDecimal(1));
		storyDao.save(story2);
		assertEquals(story, storyDao.findAll().get(0));
		assertEquals(story2, storyDao.findAll().get(1));
	}
	
}
