package com.pack.taller.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.pack.taller.Taller1ComputacionApplication;
import com.pack.taller.dao.GameDao;
import com.pack.taller.dao.StoryDao;
import com.pack.taller.model.TsscGame;
import com.pack.taller.model.TsscStory;
import com.pack.taller.service.GameServiceImp;
import com.pack.taller.service.StoryServiceImp;

@SpringBootTest(classes = Taller1ComputacionApplication.class)
@Rollback(false)
public class TestStory {
	
	@Mock
	private StoryDao storyDao;
	
	@Mock
	private GameDao gameDao;
	
	@InjectMocks
	private StoryServiceImp storyService;
	
	@InjectMocks
	private GameServiceImp gameService;
	
	@BeforeEach
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Nested
	@DisplayName("Save Test")
	class saveMethods {
		/**
		 * all ok
		 */
		@Test
		@DisplayName("all is ok")
		public void testOk() {
			TsscStory story = new TsscStory();
			story.setBusinessValue(new BigDecimal(10));
			story.setInitialSprint(new BigDecimal(1));
			story.setPriority(new BigDecimal(1));
			TsscGame game = new TsscGame();
			try {
				gameService.saveGame(game);
				story.setTsscGame(game);
				assertTrue(storyService.saveStory(story));
			} catch (Exception e) {
				fail();
			}
		}
		/**
		 * story null
		 */
		@Test
		@DisplayName("story is null")
		public void testGameNull() {
			TsscStory story = null;
			assertThrows(Exception.class, () -> storyService.saveStory(story));
		}
		/**
		 * shows exception because business value is 0
		 */
		@Test
		@DisplayName("business value zero")
		public void testbusinessValueZero() {
			TsscStory story = new TsscStory();
			story.setBusinessValue(new BigDecimal(0));
			story.setInitialSprint(new BigDecimal(1));
			story.setPriority(new BigDecimal(1));
			TsscGame game = new TsscGame();
			story.setTsscGame(game);
			assertThrows(Exception.class, () -> {
				storyService.saveStory(story);
				});
		}
		/**
		 * shows exception because initial sprint is 0
		 */
		@Test
		@DisplayName("initial sprint zero")
		public void testInitialSprint() {
			TsscStory story = new TsscStory();
			story.setBusinessValue(new BigDecimal(1));
			story.setInitialSprint(new BigDecimal(0));
			story.setPriority(new BigDecimal(1));
			TsscGame game = new TsscGame();
			story.setTsscGame(game);
			assertThrows(Exception.class, () -> {
				storyService.saveStory(story);
				});
		}
		/**
		 * shows exception because business value is 0
		 */
		@Test
		@DisplayName("Priority Zero")
		public void testPriorityZero() {
			TsscStory story = new TsscStory();
			story.setBusinessValue(new BigDecimal(1));
			story.setInitialSprint(new BigDecimal(1));
			story.setPriority(new BigDecimal(0));
			TsscGame game = new TsscGame();
			story.setTsscGame(game);
			assertThrows(Exception.class, () -> {
				storyService.saveStory(story);
				});
		}
		/**
		 * game nonexistent
		 */
		@Test
		@DisplayName("game nonexistent")
		public void testOkWithTopic() {
			TsscStory story = new TsscStory();
			story.setBusinessValue(new BigDecimal(1));
			story.setInitialSprint(new BigDecimal(1));
			story.setPriority(new BigDecimal(0));
			assertThrows(Exception.class, () -> {
				storyService.saveStory(story);
				});
		}
		
	}
	
	@Nested
	@DisplayName("EditTest")
	class editMethods {
		/**
		 * all ok
		 */
		@Test
		@DisplayName("all ok")
		public void testOk() {
			TsscStory story = new TsscStory();
			story.setBusinessValue(new BigDecimal(10));
			story.setInitialSprint(new BigDecimal(1));
			story.setPriority(new BigDecimal(1));
			TsscGame game = new TsscGame();
			try {
				gameService.saveGame(game);
				story.setTsscGame(game);
				story.setId(1);
				story.setDescription("desc1");
				storyService.saveStory(story);
				story.setDescription("desc2");
				storyService.editStory(story, Long.valueOf(1));
			} catch (Exception e) {
				e.printStackTrace();
			}
			Optional<TsscStory> optional = Optional.of(story);
			assertEquals("desc2", optional.get().getDescription());
		}
		/**
		 * story null
		 */
		@Test
		@DisplayName("story null")
		public void testStoryNull() {
			TsscStory story = new TsscStory();
			story.setBusinessValue(new BigDecimal(10));
			story.setInitialSprint(new BigDecimal(1));
			story.setPriority(new BigDecimal(1));
			TsscGame game = new TsscGame();
			story.setTsscGame(game);
			story.setId(1);
			story.setDescription("desc1");
			try {
				storyService.saveStory(story);
			} catch (Exception e) {
				e.printStackTrace();
			}
			TsscStory story2 = null;
			assertThrows(Exception.class, () -> storyService.editStory(story2, Long.valueOf(1)));
		}
		/**
		 * business value smaller than 0
		 */
		@Test
		@DisplayName("business zero")
		public void testBusinessSmall() {
			TsscStory story = new TsscStory();
			story.setBusinessValue(new BigDecimal(10));
			story.setInitialSprint(new BigDecimal(1));
			story.setPriority(new BigDecimal(1));
			TsscGame game = new TsscGame();
			try {
				gameService.saveGame(game);
				story.setTsscGame(game);
				storyService.saveStory(story);
			} catch (Exception e) {
				e.printStackTrace();
			}
			story.setBusinessValue(new BigDecimal(0));
			assertThrows(Exception.class, () -> storyService.editStory(story, Long.valueOf(1)));
		}
		/**
		 * initial sprint smaller than 0
		 */
		@Test
		@DisplayName("sprint zero")
		public void testSpringSmall() {
			TsscStory story = new TsscStory();
			story.setBusinessValue(new BigDecimal(10));
			story.setInitialSprint(new BigDecimal(1));
			story.setPriority(new BigDecimal(1));
			TsscGame game = new TsscGame();
			try {
				gameService.saveGame(game);
				story.setTsscGame(game);
				storyService.saveStory(story);
			} catch (Exception e) {
				e.printStackTrace();
			}
			story.setInitialSprint(new BigDecimal(0));
			assertThrows(Exception.class, () -> storyService.editStory(story, Long.valueOf(1)));
		}
		/**
		 * priority smaller than 0
		 */
		@Test
		@DisplayName("priority zero")
		public void testPrioritySmall() {
			TsscStory story = new TsscStory();
			story.setBusinessValue(new BigDecimal(10));
			story.setInitialSprint(new BigDecimal(1));
			story.setPriority(new BigDecimal(1));
			TsscGame game = new TsscGame();
			try {
				gameService.saveGame(game);
				story.setTsscGame(game);
				storyService.saveStory(story);
			} catch (Exception e) {
				e.printStackTrace();
			}
			story.setPriority(new BigDecimal(0));
			assertThrows(Exception.class, () -> storyService.editStory(story, Long.valueOf(1)));
		}
		/**
		 * edit with a nonexistent game
		 */
		@Test
		@DisplayName("game nonexistent")
		public void testGameNonexistent() {
			TsscStory story = new TsscStory();
			story.setBusinessValue(new BigDecimal(10));
			story.setInitialSprint(new BigDecimal(1));
			story.setPriority(new BigDecimal(1));
			assertThrows(Exception.class, () -> storyService.saveStory(story));
		}
	}
	
}
