package com.pack.taller.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.pack.taller.dao.GameDao;
import com.pack.taller.dao.TopicDao;
import com.pack.taller.model.TsscGame;
import com.pack.taller.model.TsscTopic;
import com.pack.taller.service.GameServiceImp;

public class TestGame {
	
	@Mock
	private GameDao gameDao;
	
	@Mock
	private TopicDao topicDao;
	
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
			TsscGame game = new TsscGame();
			game.setNGroups(1);
			game.setNSprints(1);
			try {
				assertTrue(gameService.saveGame(game));
			} catch (Exception e) {
				fail();
			}
		}
		/**
		 * topic null
		 */
		@Test
		@DisplayName("topic is null")
		public void testGameNull() {
			TsscGame game = null;
			assertThrows(Exception.class, () -> gameService.saveGame(game));
		}
		/**
		 * shows exception because there aren't sprints
		 */
		@Test
		@DisplayName("There aren't sprints")
		public void testSprintsEmpty() {
			TsscGame game = new TsscGame();
			game.setNGroups(1);
			game.setNSprints(0);
			assertThrows(Exception.class, () -> {
				gameService.saveGame(game);
				});
		}
		/**
		 * shows exception because there aren't groups
		 */
		@Test
		@DisplayName("There aren't groups")
		public void testGroupsEmpty() {
			TsscGame game = new TsscGame();
			game.setNGroups(0);
			game.setNSprints(1);
			assertThrows(Exception.class, () -> {
				gameService.saveGame(game);
				});
		}
		/**
		 * ok with topic
		 */
		@Test
		@DisplayName("all ok with topic")
		public void testOkWithTopic() {
			TsscGame game = new TsscGame();
			game.setNGroups(1);
			game.setNSprints(1);
			TsscTopic topic = new TsscTopic();
			topic.setDefaultGroups(1);
			topic.setDefaultSprints(1);
			topicDao.save(topic);
			game.setTsscTopic(topic);
			try {
				assertTrue(gameService.saveGame(game));
			} catch (Exception e) {
				fail();
			}
		}
		/**
		 * topic not exists
		 */
		@Test
		@DisplayName("topic not exists")
		public void testTopicNotExists() {
			TsscGame game = new TsscGame();
			game.setNGroups(1);
			game.setNSprints(1);
			TsscTopic topic = new TsscTopic();
			game.setTsscTopic(topic);
			try {
				assertTrue(gameService.saveGame(game));
			} catch (Exception e) {
				fail();
			}
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
			TsscGame game = new TsscGame();
			game.setNGroups(1);
			game.setNSprints(1);
			game.setId(1);
			game.setName("game1");
			try {
				gameService.saveGame(game);
				game.setName("game2");
				assertTrue(gameService.editGame(game, Long.valueOf(1)));
			} catch (Exception e) {
				fail();
			}
		}
		/**
		 * game null
		 */
		@Test
		@DisplayName("game null")
		public void testGameNull() {
			TsscGame game = new TsscGame();
			game.setNGroups(1);
			game.setNSprints(1);
			game.setId(1);
			game.setName("game1");
			try {
				gameService.saveGame(game);
			} catch (Exception e) {
				e.printStackTrace();
			}
			TsscGame game2 = null;
			assertThrows(Exception.class, () -> gameService.editGame(game2, Long.valueOf(1)));
		}
		/**
		 * sprints smaller than 0
		 */
		@Test
		@DisplayName("spring zero")
		public void testSpringSmall() {
			TsscGame game = new TsscGame();
			game.setNGroups(1);
			game.setNSprints(1);
			game.setId(1);
			game.setName("game1");
			try {
				gameService.saveGame(game);
			} catch (Exception e) {
				e.printStackTrace();
			}
			game.setNSprints(0);
			assertThrows(Exception.class, () -> gameService.editGame(game, Long.valueOf(1)));
		}
		/**
		 * groups smaller than 0
		 */
		@Test
		@DisplayName("groups zero")
		public void testGroupsSmall() {
			TsscGame game = new TsscGame();
			game.setNGroups(1);
			game.setNSprints(1);
			game.setId(1);
			game.setName("game1");
			try {
				gameService.saveGame(game);
			} catch (Exception e) {
				e.printStackTrace();
			}
			game.setNGroups(0);
			assertThrows(Exception.class, () -> gameService.editGame(game, Long.valueOf(1)));
		}
		/**
		 * edit with a nonexistent topic
		 */
		@Test
		@DisplayName("topic not exists")
		public void testTopicNonexistent() {
			TsscGame game = new TsscGame();
			game.setNGroups(1);
			game.setNSprints(1);
			game.setId(1);
			TsscTopic topic = new TsscTopic();
			topicDao.save(topic);
			game.setTsscTopic(topic);
			try {
				gameService.saveGame(game);
			} catch (Exception e) {
				e.printStackTrace();
			}
			game.setTsscTopic(null);
			try {
				assertTrue(gameService.editGame(game, Long.valueOf(1)));
			} catch (Exception e) {
				fail();
			}
		}
	}
	
}
