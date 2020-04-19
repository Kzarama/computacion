package com.pack.taller.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.pack.taller.model.TsscGame;

@Service
public interface GameService {
	
	public boolean saveGame(TsscGame game) throws Exception;
	public boolean saveGame2(TsscGame game) throws Exception;
	public boolean editGame(TsscGame game, Long id) throws Exception;
	public Iterable<TsscGame> findAll();
	public TsscGame findById(Long id);
	public void deleteGame(Long id);
	
}
