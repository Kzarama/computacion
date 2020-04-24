package com.pack.taller.dao;

import java.util.List;

import com.pack.taller.model.TsscTimecontrol;

public interface ITimecontrolDao {
	
	public void save(TsscTimecontrol timecontrol);
	public void update(TsscTimecontrol timecontrol);
	public void delete(TsscTimecontrol timecontrol);
	public TsscTimecontrol findById(Long id);
	public List<TsscTimecontrol> findAll();
	
}
