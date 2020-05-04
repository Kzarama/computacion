package com.TP.demo.services;

import com.TP.demo.exceptions.ElemNotExiPreviousException;
import com.TP.demo.model.TsscGame;
import com.TP.demo.model.TsscSprint;
import com.TP.demo.model.TsscStory;

public interface IServicioHistoria {

	public boolean addStory(TsscStory game);
	
	public void deleteStory(Long key);
	
	public void editStory(Long key, TsscStory game) throws ElemNotExiPreviousException;
	
	public TsscStory getStory(Long key);
	
	public boolean exists(Long key);
	
	public Iterable<TsscStory> findAll();
	
	public TsscStory findById(Long id);
	
}
