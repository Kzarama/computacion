package com.pack.taller.integration;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.pack.taller.model.TsscGame;
import com.pack.taller.model.TsscStory;
import com.pack.taller.repository.GameRepository;
import com.pack.taller.service.StoryServiceImp;

@SpringBootTest
public class TestStoryIntegration {
	
	@Autowired
	private StoryServiceImp storyService;
	
	@Autowired
	private GameRepository gameRepository;
	
	@Test
	@DisplayName("all is ok")
	public void testOk() {
		TsscStory story = new TsscStory();
		story.setBusinessValue(new BigDecimal(1));
		story.setInitialSprint(new BigDecimal(1));
		story.setPriority(new BigDecimal(1));
		TsscGame game = new TsscGame();
		try {
			gameRepository.save(game);
			story.setTsscGame(game);
			assertTrue(storyService.saveStory(story));
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	@DisplayName("story is null")
	public void testStoryNull() {
		TsscStory story = null;
		assertThrows(Exception.class, () -> storyService.saveStory(story));
	}
	
	@Test
	@DisplayName("business value zero")
	public void testBusinessValueZero() {
		TsscStory story = new TsscStory();
		story.setBusinessValue(new BigDecimal(0));
		story.setInitialSprint(new BigDecimal(1));
		story.setPriority(new BigDecimal(1));
		TsscGame game = new TsscGame();
		try {
			gameRepository.save(game);
			story.setTsscGame(game);
		} catch (Exception e) {
			fail();
		}
		assertThrows(Exception.class, () -> {
			storyService.saveStory(story);
		});
	}
	
	@Test
	@DisplayName("initial sprint zero")
	public void testIntialSprintsZero() {
		TsscStory story = new TsscStory();
		story.setBusinessValue(new BigDecimal(1));
		story.setInitialSprint(new BigDecimal(0));
		story.setPriority(new BigDecimal(1));
		TsscGame game = new TsscGame();
		try {
			gameRepository.save(game);
			story.setTsscGame(game);
		} catch (Exception e) {
			fail();
		}
		assertThrows(Exception.class, () -> {
			storyService.saveStory(story);
		});
	}
	
	@Test
	@DisplayName("priority zero")
	public void testPriorityZero() {
		TsscStory story = new TsscStory();
		story.setBusinessValue(new BigDecimal(1));
		story.setInitialSprint(new BigDecimal(1));
		story.setPriority(new BigDecimal(0));
		TsscGame game = new TsscGame();
		try {
			gameRepository.save(game);
			story.setTsscGame(game);
		} catch (Exception e) {
			fail();
		}
		assertThrows(Exception.class, () -> {
			storyService.saveStory(story);
		});
	}
	
	@Test
	@DisplayName("edit test")
	public void testEdit() {
		TsscStory story = new TsscStory();
		story.setBusinessValue(new BigDecimal(1));
		story.setInitialSprint(new BigDecimal(1));
		story.setPriority(new BigDecimal(1));
		story.setDescription("desc2");
		story.setId(1);
		TsscGame game = new TsscGame();
		try {
			gameRepository.save(game);
			story.setTsscGame(game);
			storyService.saveStory(story);
			story.setDescription("desc2");
			assertTrue(storyService.editStory(story, story.getId()));
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

}
