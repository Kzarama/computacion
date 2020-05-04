package com.TP.demo.services;

import com.TP.demo.model.TsscGame;
import com.TP.demo.model.TsscGroup;
import com.TP.demo.model.TsscSprint;

public interface IServicioSpring {

	public void addSprint(TsscSprint game);
	
	public void deleteSprint(Integer key);
	
	public void editSprint(Integer key, TsscSprint game);
	
	public TsscSprint getSprint(Integer key);
	
}
