package com.pack.taller.dao;

import java.util.List;

import com.pack.taller.model.TsscAdmin;

public interface IAdminDao {
	
	public void save(TsscAdmin admin);
	public void update(TsscAdmin admin);
	public void delete(TsscAdmin admin);
	public TsscAdmin findById(Long id);
	public List<TsscAdmin> findAll();
	
}
