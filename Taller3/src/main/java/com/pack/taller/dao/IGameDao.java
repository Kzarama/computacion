package com.pack.taller.dao;

import java.util.List;

import com.pack.taller.model.TsscGame;

public interface IGameDao {
	
	public void save(TsscGame game);
	public void update(TsscGame game);
	public void delete(TsscGame game);
	public TsscGame findById(Long id);
	public List<TsscGame> findAll();
	public TsscGame findByName(String name);
	public TsscGame findByDescription(String description);
	
}
