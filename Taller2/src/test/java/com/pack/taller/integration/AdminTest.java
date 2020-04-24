package com.pack.taller.integration;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.pack.taller.model.TsscAdmin;
import com.pack.taller.repository.AdminRepository;
import com.pack.taller.service.AdminService;

@SpringBootTest
public class AdminTest {
	
	@Autowired
	private AdminService admin;
	
	@Autowired
	private AdminRepository repo;
	
	@Test
	public void test() {
		TsscAdmin ad = new TsscAdmin();
		ad.setUser("1");
		repo.save(ad);
		assertEquals("1", admin.findByUser("1").getUser());
	}
	
}
