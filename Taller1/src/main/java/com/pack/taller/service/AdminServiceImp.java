package com.pack.taller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pack.taller.model.TsscAdmin;
import com.pack.taller.repository.AdminRepository;

@Service
public class AdminServiceImp implements AdminService {
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Override
	public void saveAdmin(TsscAdmin admin) {
		adminRepository.save(admin);
	}

	@Override
	public TsscAdmin findByUser(String user) {
		return adminRepository.findByUser(user).get(0);
	}

}
