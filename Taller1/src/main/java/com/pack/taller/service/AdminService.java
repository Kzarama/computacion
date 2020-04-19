package com.pack.taller.service;

import org.springframework.data.repository.CrudRepository;

import com.pack.taller.model.TsscAdmin;

public interface AdminService {
	
	public void saveAdmin(TsscAdmin admin);
	public TsscAdmin findByUser(String user);
	
}
