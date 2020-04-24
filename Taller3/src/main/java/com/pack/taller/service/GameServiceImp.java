package com.pack.taller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pack.taller.model.TsscGame;
import com.pack.taller.model.TsscTopic;
import com.pack.taller.repository.GameRepository;
import com.pack.taller.repository.TopicRepository;

@Service
public class GameServiceImp implements GameService {
	
	@Autowired
	private GameRepository repo;
	
	@Autowired
	private TopicRepository topicRepo;
	
	@Override
	public boolean saveGame(TsscGame game) throws Exception {
		if (game != null && game.getNSprints() > 0 && game.getNGroups() > 0) {
			TsscTopic topic = game.getTsscTopic();
			if (topic != null) {
				topicRepo.findById(topic.getId());
				repo.save(game);
				return true;
			} else {
				repo.save(game);
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
				topicRepo.findById(topic.getId());
				repo.deleteById(id);
				repo.save(game);
				return true;
			} else {
				repo.deleteById(id);
				repo.save(game);
				return true;
			}
		} else {
			throw new Exception();
		}
	}

	@Override
	public Iterable<TsscGame> findAll() {
		return repo.findAll();
	}

	@Override
	public TsscGame findById(Long id) {
		return repo.findById(id).get();
	}

	@Override
	public void deleteGame(Long id) {
		repo.deleteById(id);
	}
	
}
