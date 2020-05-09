package com.pack.taller.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


import org.hibernate.internal.ExceptionConverterImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.pack.taller.dao.AdminDao;
import com.pack.taller.model.TsscAdmin;
import com.pack.taller.service.AdminService;

@Rollback
@SpringBootTest
public class TestAdminIntegration {
	
	@Autowired
	private AdminService admin;
	
	@Autowired
	private AdminDao repo;
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void test() {
		TsscAdmin ad = new TsscAdmin();
		ad.setUser("1");
		admin.saveAdmin(ad);
		ad = admin.findByUser("1");
		assertEquals("1", ad.getUser());
	}
	
}
