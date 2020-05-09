package com.pack.taller.Dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.pack.taller.dao.GameDao;
import com.pack.taller.dao.TopicDao;
import com.pack.taller.model.TsscAdmin;
import com.pack.taller.model.TsscGame;
import com.pack.taller.model.TsscTimecontrol;
import com.pack.taller.model.TsscTopic;

@SpringBootTest
@DisplayName("GameDao")
public class TestGameDao {
	
	@Autowired
	private GameDao gameDao;
	
	private TsscGame game;
	
	@Autowired
	private TopicDao topicDao;
	
	private TsscTopic topic;
	
	@BeforeEach
	public void initiallize() {
		game = new TsscGame();
		game.setAdminPassword("123");
		game.setGuestPassword("123");
		game.setName("kz");
		game.setNGroups(1);
		game.setNSprints(1);
	}
	
	@Test
	@Transactional
	@DisplayName("Save")
	public void save_game() {
		assertTrue(gameDao.findAll().isEmpty());
		gameDao.save(game);
		assertFalse(gameDao.findAll().isEmpty());
	}
	
	@Test
	@Transactional
	@DisplayName("Update")
	public void update_game() {
		assertTrue(gameDao.findAll().isEmpty());
		gameDao.save(game);
		assertEquals("kz", gameDao.findById(game.getId()).getName());
		game.setName("abc");
		gameDao.update(game);
		assertEquals("abc", gameDao.findById(game.getId()).getName());
	}
	
	@Test
	@Transactional
	@DisplayName("Delete")
	public void delete_game() {
		assertTrue(gameDao.findAll().isEmpty());
		gameDao.save(game);
//		assertFalse(gameDao.findAll().isEmpty());
		gameDao.delete(game);
		assertTrue(gameDao.findAll().isEmpty());
	}
	
	@Test
	@Transactional
	@DisplayName("Find by id")
	public void findById_game() {
		gameDao.save(game);
		assertEquals("kz", gameDao.findById(game.getId()).getName());
	}
	
	@Test
	@Transactional
	@DisplayName("Find all")
	public void find_all() {
		assertTrue(gameDao.findAll().isEmpty());
		gameDao.save(game);
		TsscGame game2 = new TsscGame();
		game2.setAdminPassword("123");
		game2.setGuestPassword("123");
		game2.setName("k");
		game2.setNGroups(1);
		game2.setNSprints(1);
		gameDao.save(game2);
		ArrayList<TsscGame> arGame = new ArrayList<TsscGame>();
		arGame.add(game);
		arGame.add(game2);
		assertEquals("kz", gameDao.findAll().get(0).getName());
		assertEquals("k", gameDao.findAll().get(1).getName());
	}
	
	@Test
	@Transactional
	@DisplayName("Find by name")
	public void findByName_game() {
		gameDao.save(game);
		assertEquals("kz", gameDao.findByName(game.getName()).get(0).getName());
	}
	
	@Test
	@Transactional
	@DisplayName("Find game by description topic")
	public void findByDescriptionTopci_game() {
		topic = new TsscTopic();
		topic.setDefaultGroups(1);
		topic.setDefaultSprints(1);
		topic.setDescription("desc");
		topic.setGroupPrefix("group");
		topic.setName("topic");
		topicDao.save(topic);
		game.setTsscTopic(topic);
		gameDao.save(game);
		assertEquals(game, gameDao.findByDescriptionTopic("desc").get(0));
	}
	
	@Test
	@Transactional
	@DisplayName("Find game by id topic")
	public void findByIdTopic_game() {
		topic = new TsscTopic();
		topic.setDefaultGroups(1);
		topic.setDefaultSprints(1);
		topic.setDescription("desc");
		topic.setGroupPrefix("group");
		topic.setName("topic");
		topicDao.save(topic);
		game.setTsscTopic(topic);
		gameDao.save(game);	
		assertEquals(game, gameDao.findByIdTopic(topic.getId()).get(0));
	}
	
	@Test
	@Transactional
	@DisplayName("")
	public void rangeDate_game() {
		game.setScheduledDate(LocalDate.of(2001, 01, 02));
		gameDao.save(game);
		assertEquals(game, gameDao.rangeDate(LocalDate.of(2001, 01, 01), LocalDate.of(2001, 01, 03)).get(0));
	}
	
	@Test
	@Transactional
	@DisplayName("")
	public void rangeDateHour_game() {
		game.setScheduledDate(LocalDate.of(2001, 01, 02));
		game.setScheduledTime(LocalTime.of(5, 30));
		gameDao.save(game);
		assertEquals(game, gameDao.rangeDateHour(LocalDate.of(2001, 01, 01), LocalDate.of(2001, 01, 03), LocalTime.of(5, 00), LocalTime.of(6, 00)).get(0));
	}
	
	@Test
	@Transactional
	@DisplayName("")
	public void rangeDatetTopicGame_game() {
		topic = new TsscTopic();
		topic.setDefaultGroups(1);
		topic.setDefaultSprints(1);
		topic.setDescription("desc");
		topic.setGroupPrefix("group");
		topic.setName("topic");
		topicDao.save(topic);
		game.setTsscTopic(topic);
		game.setScheduledDate(LocalDate.of(2001, 01, 01));
		gameDao.save(game);
		assertEquals(game.getName(), gameDao.rangeDatetTopicGame(LocalDate.of(2001, 01, 01)).get(0).getName());
	}
	
	@Test
	@Transactional
	@DisplayName("")
	public void rangeDatetStoryNoTimeGame_game() {
		game.setScheduledDate(LocalDate.of(2001, 01, 01));
		gameDao.save(game);
		assertEquals(game.getName(), gameDao.rangeDatetTopicGame(LocalDate.of(2001, 01, 01)).get(0).getName());
	}
	
}
