package com.pack.taller.dao;

import java.util.List;

import com.pack.taller.model.TsscStory;

public interface IStoryDao {
	
	public void save(TsscStory story);
	public void update(TsscStory story);
	public void delete(TsscStory story);
	public TsscStory findById(Long id);
	public List<TsscStory> findAll();
	
}
