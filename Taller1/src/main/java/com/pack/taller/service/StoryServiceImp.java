package com.pack.taller.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pack.taller.model.TsscGame;
import com.pack.taller.model.TsscStory;
import com.pack.taller.repository.GameRepository;
import com.pack.taller.repository.StoryRepository;

@Service
public class StoryServiceImp implements StoryService {
	
	@Autowired
	private StoryRepository repo;
	
	@Autowired
	private GameRepository gameRepo;
	
	@Override
	public boolean saveStory(TsscStory story) throws Exception {
		if (story != null && story.getBusinessValue().compareTo(new BigDecimal(0)) == 1 && story.getInitialSprint().compareTo(new BigDecimal(0)) == 1 && story.getPriority().compareTo(new BigDecimal(0)) == 1) {
			TsscGame game = story.getTsscGame();
			if (game != null) {
				gameRepo.findById(game.getId());
				repo.save(story);
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
			repo.deleteById(id);
			repo.save(story);
			return true;
		} else {
			throw new Exception();
		}
	}

	@Override
	public TsscStory findById(Long id) {
		return repo.findById(id).get();
	}

	@Override
	public void deleteStory(Long id) {
		repo.deleteById(id);
	}

	@Override
	public Iterable<TsscStory> findAll() {
		return repo.findAll();
	}
	
}
