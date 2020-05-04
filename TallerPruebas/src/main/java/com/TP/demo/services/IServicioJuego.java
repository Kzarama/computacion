package com.TP.demo.services;

import java.util.Optional;

import com.TP.demo.exceptions.ElemIdAlrExistException;
import com.TP.demo.exceptions.ElemNotExiPreviousException;
import com.TP.demo.model.TsscGame;

public interface IServicioJuego {

	public boolean addGame(TsscGame game);
	
	public void deleteGame(Long key);
	
	public void editGame(Long key, TsscGame game) throws ElemNotExiPreviousException;
	
	public TsscGame getGame(Long key);
	
	public boolean exists(Long key);
	
	public Iterable<TsscGame> findAll();
	
	public TsscGame findById(Long id);
	
}
