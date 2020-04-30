package com.pack.taller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pack.taller.dao.AdminDao;
import com.pack.taller.model.TsscAdmin;

@Service
public class AdminServiceImp implements AdminService {
	
	@Autowired
	private AdminDao adminDao;
	
	@Override
	public void saveAdmin(TsscAdmin admin) {
		adminDao.save(admin);
	}

	@Override
	public TsscAdmin findByUser(String user) {
		return adminDao.findByUser(user).get(0);
	}

}
