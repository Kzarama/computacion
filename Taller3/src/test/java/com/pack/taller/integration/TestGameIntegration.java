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

import com.pack.taller.model.TsscGame;
import com.pack.taller.model.TsscTopic;
import com.pack.taller.service.GameServiceImp;
import com.pack.taller.service.TopicServiceImp;

@Rollback
@SpringBootTest
public class TestGameIntegration {
	
	@Autowired
	private GameServiceImp gameService;
	
	@Autowired
	private TopicServiceImp topicService;
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@DisplayName("all is ok")
	public void testOk() {
		TsscGame game = new TsscGame();
		game.setNGroups(1);
		game.setNSprints(1);
		try {
			assertTrue(gameService.saveGame(game));
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@DisplayName("all is ok with game")
	public void testGetName() {
		TsscGame game = new TsscGame();
		game.setNGroups(1);
		game.setNSprints(1);
		game.setName("1");
		try {
			gameService.saveGame(game);
			assertEquals("1", gameService.findById(game.getId()).getName());
			System.out.println(gameService.findById(game.getId()).getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@DisplayName("all is ok with game")
	public void testOkTopic() {
		TsscGame game = new TsscGame();
		game.setNGroups(1);
		game.setNSprints(1);
		TsscTopic topic = new TsscTopic();
		topic.setDefaultGroups(1);
		topic.setDefaultSprints(1);
		try {
			topicService.saveTopic(topic);
			game.setTsscTopic(topic);
			assertTrue(gameService.saveGame(game));
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@DisplayName("game is null")
	public void testTopicNull() {
		TsscGame game = null;
		assertThrows(Exception.class, () -> gameService.saveGame(game));
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@DisplayName("There aren't sprints")
	public void testSprintsEmpty() {
		TsscGame game = new TsscGame();
		game.setNGroups(1);
		game.setNSprints(0);
		assertThrows(Exception.class, () -> {
			gameService.saveGame(game);
		});
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@DisplayName("There aren't groups")
	public void testGroupsEmpty() {
		TsscGame game = new TsscGame();
		game.setNGroups(0);
		game.setNSprints(1);
		assertThrows(Exception.class, () -> {
			gameService.saveGame(game);
		});
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@DisplayName("edit test")
	public void testEdit() {
		TsscGame game = new TsscGame();
		game.setNGroups(1);
		game.setNSprints(1);
		try {
			assertTrue(gameService.saveGame(game));
			game.setName("game2");
			gameService.editGame(game, game.getId());
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		Optional<TsscGame> optional = Optional.of(game);
		assertEquals(optional.get().getName(), "game2");
	}
	
}
