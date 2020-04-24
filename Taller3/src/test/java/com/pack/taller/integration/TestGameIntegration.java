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

import com.pack.taller.model.TsscGame;
import com.pack.taller.model.TsscTopic;
import com.pack.taller.service.GameServiceImp;
import com.pack.taller.service.TopicServiceImp;

@SpringBootTest
public class TestGameIntegration {
	
	@Autowired
	private GameServiceImp gameService;
	
	@Autowired
	private TopicServiceImp topicRepository;
	
	@Test
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
	@DisplayName("all is ok with game")
	public void testOkTopic() {
		TsscGame game = new TsscGame();
		game.setNGroups(1);
		game.setNSprints(1);
		game.setId(1);
		TsscTopic topic = new TsscTopic();
		topic.setDefaultGroups(1);
		topic.setDefaultSprints(1);
		try {
			topicRepository.saveTopic(topic);
			game.setTsscTopic(topic);
			assertTrue(gameService.saveGame(game));
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	@DisplayName("game is null")
	public void testTopicNull() {
		TsscGame game = null;
		assertThrows(Exception.class, () -> gameService.saveGame(game));
	}
	
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
	
	@Test
	@DisplayName("edit test")
	public void testEdit() {
		TsscGame game = new TsscGame();
		game.setNGroups(1);
		game.setNSprints(1);
		game.setId(1);
		try {
			assertTrue(gameService.saveGame(game));
			game.setName("game2");
			gameService.editGame(game, Long.valueOf(1));
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		Optional<TsscGame> optional = Optional.of(game);
		assertEquals(optional.get().getName(), "game2");
	}
	
}
