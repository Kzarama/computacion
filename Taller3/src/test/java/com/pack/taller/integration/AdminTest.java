package com.pack.taller.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import javax.transaction.Transactional;

import org.hibernate.internal.ExceptionConverterImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.pack.taller.dao.AdminDao;
import com.pack.taller.model.TsscAdmin;
import com.pack.taller.service.AdminService;

@SpringBootTest
public class AdminTest {
	
	@Autowired
	private AdminService admin;
	
	@Autowired
	private AdminDao repo;
	
	@Test
	@Transactional
	public void test() {
		TsscAdmin ad = new TsscAdmin();
		ad.setUser("1");
		repo.save(ad);
		ad = admin.findByUser("1");
		assertEquals("1", ad.getUser());
	}
	
}
