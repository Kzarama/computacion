package com.pack.taller.service;

import com.pack.taller.model.TsscAdmin;

public interface AdminService {
	
	public void saveAdmin(TsscAdmin admin);
	public TsscAdmin findByUser(String user);
	
}
