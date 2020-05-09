package com.pack.taller.Dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.transaction.annotation.Transactional;
import com.pack.taller.dao.AdminDao;
import com.pack.taller.model.TsscAdmin;

@SpringBootTest
@DisplayName("AdminDao")
public class TestAdminDao {
	
	@Autowired
	private AdminDao adminDao;
	
	private TsscAdmin admin;
	
	@BeforeEach
	public void initiallize() {
		admin = new TsscAdmin();
		admin.setPassword("123");
		admin.setSuperAdmin("superadmin");
		admin.setUser("kz");
	}
	
	@Test
	@Transactional
	@DisplayName("Save")
	public void save_admin() {
		assertTrue(adminDao.findAll().isEmpty());
		adminDao.save(admin);
		assertFalse(adminDao.findAll().isEmpty());
	}
	
	@Test
	@Transactional
	@DisplayName("Update")
	public void update_admin() {
		assertTrue(adminDao.findAll().isEmpty());
		adminDao.save(admin);
		assertEquals("123", adminDao.findById(admin.getId()).getPassword());
		admin.setPassword("abc");
		adminDao.update(admin);
		assertEquals("abc", adminDao.findById(admin.getId()).getPassword());
	}
	
	@Test
	@Transactional
	@DisplayName("Delete")
	public void delete_admin() {
		assertTrue(adminDao.findAll().isEmpty());
		adminDao.save(admin);
		assertFalse(adminDao.findAll().isEmpty());
		adminDao.delete(admin);
		assertTrue(adminDao.findAll().isEmpty());
	}
	
	@Test
	@Transactional
	@DisplayName("Find by id")
	public void findById_admin() {
		adminDao.save(admin);
		assertEquals("kz", adminDao.findById(admin.getId()).getUser());
	}
	
	@Test
	@Transactional
	@DisplayName("Find all")
	public void find_all() {
		assertTrue(adminDao.findAll().isEmpty());
		adminDao.save(admin);
		TsscAdmin admin2 = new TsscAdmin();
		admin2 = new TsscAdmin();
		admin2.setPassword("123");
		admin2.setSuperAdmin("superadmin");
		admin2.setUser("k");
		adminDao.save(admin2);
		ArrayList<TsscAdmin> arAdmin = new ArrayList<TsscAdmin>();
		arAdmin.add(admin);
		arAdmin.add(admin2);
		assertEquals("kz", adminDao.findAll().get(0).getUser());
		assertEquals("k", adminDao.findAll().get(1).getUser());
	}
	
	@Test
	@Transactional
	@DisplayName("Find by user")
	public void findByUser_admin() {
		adminDao.save(admin);
		assertEquals("kz", adminDao.findByUser(admin.getUser()).get(0).getUser());
	}
	
}
