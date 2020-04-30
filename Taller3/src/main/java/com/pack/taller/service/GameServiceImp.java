package com.pack.taller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pack.taller.dao.GameDao;
import com.pack.taller.dao.TopicDao;
import com.pack.taller.model.TsscGame;
import com.pack.taller.model.TsscTopic;

@Service
public class GameServiceImp implements GameService {
	
	@Autowired
	private GameDao gameDao;
	
	@Autowired
	private TopicDao topicDao;
	
	@Override
	public boolean saveGame(TsscGame game) throws Exception {
		if (game != null && game.getNSprints() > 0 && game.getNGroups() > 0) {
			TsscTopic topic = game.getTsscTopic();
			if (topic != null) {
				topicDao.findById(topic.getId());
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
				gameDao.delete(gameDao.findById(id));
				gameDao.save(game);
				return true;
			} else {
				gameDao.delete(gameDao.findById(id));
				gameDao.save(game);
				return true;
			}
		} else {
			throw new Exception();
		}
	}

	@Override
	public Iterable<TsscGame> findAll() {
		return gameDao.findAll();
	}

	@Override
	public TsscGame findById(Long id) {
		return gameDao.findById(id);
	}

	@Override
	public void deleteGame(Long id) {
		gameDao.delete(gameDao.findById(id));
	}
	
}
