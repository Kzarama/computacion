package com.TP.demo.services;

import com.TP.demo.model.TsscGame;
import com.TP.demo.model.TsscGroup;

public interface IServicioGrupo {

	public void addGroup(TsscGroup game);
	
	public void deleteGroup(Integer key);
	
	public void editGroup(Integer key, TsscGroup game);
	
	public TsscGroup getGroup(Integer key);

}
