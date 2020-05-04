package com.pack.taller.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pack.taller.dao.GameDao;
import com.pack.taller.dao.StoryDao;
import com.pack.taller.model.TsscGame;
import com.pack.taller.model.TsscStory;

@Service
public class StoryServiceImp implements StoryService {
	
	@Autowired
	private StoryDao storyDao;
	
	@Autowired
	private GameDao gameDao;
	
	@Override
	public boolean saveStory(TsscStory story) throws Exception {
		if (story != null && story.getBusinessValue().compareTo(new BigDecimal(0)) == 1 && story.getInitialSprint().compareTo(new BigDecimal(0)) == 1 && story.getPriority().compareTo(new BigDecimal(0)) == 1) {
			TsscGame game = story.getTsscGame();
			if (game != null) {
				gameDao.findById(game.getId());
				storyDao.save(story);
				return true;
			} else {
				throw new Exception();
			}
		} else {
			throw new Exception();
		}
	}

	@Override
	public boolean editStory(TsscStory story, Long id) throws Exception {
		if (story != null && story.getBusinessValue().compareTo(new BigDecimal(0)) == 1 && story.getInitialSprint().compareTo(new BigDecimal(0)) == 1 && story.getPriority().compareTo(new BigDecimal(0)) == 1) {
			storyDao.delete(findById(id));
			storyDao.save(story);
			return true;
		} else {
			throw new Exception();
		}
	}

	@Override
	public TsscStory findById(Long id) {
		return storyDao.findById(id);
	}

	@Override
	public void deleteStory(Long id) {
		storyDao.delete(findById(id));
	}

	@Override
	public Iterable<TsscStory> findAll() {
		return storyDao.findAll();
	}
	
}
