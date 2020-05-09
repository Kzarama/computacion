package com.pack.taller.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pack.taller.dao.GameDao;
import com.pack.taller.dao.TopicDao;
import com.pack.taller.model.TsscGame;
import com.pack.taller.model.TsscTopic;

@Service
public class GameServiceImp implements GameService {
	
	
	private GameDao gameDao;
	
	@Autowired
	private TopicDao topicDao;

	@Autowired
	public GameServiceImp(GameDao gameDao) {
		this.gameDao = gameDao;
	}
	
	@Override
	public boolean saveGame(TsscGame game) throws Exception {
		if (game != null && game.getNSprints() > 0 && game.getNGroups() > 0) {
			TsscTopic topic = game.getTsscTopic();
			if (topic != null) {
				gameDao.save(game);
				return true;
			} else {
				gameDao.save(game);
				return true;
			}
		} else {
			throw new Exception();
		}
	}
	
	@Override
	public boolean saveGame2(TsscGame game) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean editGame(TsscGame game, Long id) throws Exception {
		if (game != null && game.getNGroups() > 0 && game.getNSprints() > 0) {
			TsscTopic topic = game.getTsscTopic();
			if (topic != null) {
				topicDao.findById(topic.getId());
				gameDao.delete(findById(id));
				gameDao.save(game);
				return true;
			} else {
				gameDao.delete(findById(id));
				gameDao.save(game);
				return true;
			}
		} else {
			throw new Exception();
		}
	}

	@Override
	public void deleteGame(Long id) {
		gameDao.delete(findById(id));
	}
	
	@Override
	public TsscGame findById(Long id) {
		return gameDao.findById(id);
	}
	
	@Override
	public Iterable<TsscGame> findAll() {
		return gameDao.findAll();
	}

	
	@Override
	public List<TsscGame> findByName(String name) {
		return gameDao.findByName(name);
	}

	@Override
	public List<TsscGame> findByDescription(String description) {
		return gameDao.findByDescriptionTopic(description);
	}

	@Override
	public List<TsscGame> findByIdTopic(Long id) {
		return gameDao.findByIdTopic(id);
	}

	@Override
	public List<TsscGame> rangeDate(LocalDate fechaInicio, LocalDate fechaFin) {
		return gameDao.rangeDate(fechaInicio, fechaFin);
	}

	@Override
	public List<TsscGame> rangeDateHour(LocalDate fechaInicio, LocalDate fechaFin, LocalTime horaInicio,
			LocalTime horaFinal) {
		return gameDao.rangeDateHour(fechaInicio, fechaFin, horaInicio, horaFinal);
	}

	@Override
	public List<TsscGame> rangeDatetTopicGame(LocalDate fecha) {
		return gameDao.rangeDatetTopicGame(fecha);
	}

	@Override
	public List<TsscGame> rangeDatetStoryNoTimeGame(LocalDate fecha) {
		return gameDao.rangeDatetStoryNoTimeGame(fecha);
	}
	
}
