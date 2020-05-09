package com.pack.taller.Dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.pack.taller.dao.GameDao;
import com.pack.taller.dao.TimecontrolDao;
import com.pack.taller.model.TsscTimecontrol;

@SpringBootTest
@DisplayName("Timecontrol")
public class TestTimecontrolDao {
	
	@Autowired
	private TimecontrolDao timecontrolDao;
	
	private TsscTimecontrol timecontrol;
	
	@BeforeEach
	public void initiallize() {
		timecontrol = new TsscTimecontrol();
		timecontrol.setAutostart("no");
		timecontrol.setIntervalRunning(new BigDecimal(1));
		timecontrol.setName("kz");
		timecontrol.setOrder(new BigDecimal(1));
		timecontrol.setState("activo");
		timecontrol.setType("1");
		timecontrol.setTimeInterval(new BigDecimal(1));
	}
	
	@Test
	@Transactional
	@DisplayName("Save")
	public void save() {
		assertTrue(timecontrolDao.findAll().isEmpty());
		timecontrolDao.save(timecontrol);
		assertFalse(timecontrolDao.findAll().isEmpty());
	}

	@Test
	@Transactional
	@DisplayName("Update")
	public void update() {
		assertTrue(timecontrolDao.findAll().isEmpty());
		timecontrolDao.save(timecontrol);
		assertEquals("kz", timecontrolDao.findAll().get(0).getName());
		timecontrol.setName("k");
		timecontrolDao.update(timecontrol);
		assertEquals("k", timecontrolDao.findAll().get(0).getName());
	}

	@Test
	@Transactional
	@DisplayName("Delete")
	public void delete() {
		assertTrue(timecontrolDao.findAll().isEmpty());
		timecontrolDao.save(timecontrol);
		assertFalse(timecontrolDao.findAll().isEmpty());
		timecontrolDao.delete(timecontrol);
		assertTrue(timecontrolDao.findAll().isEmpty());
	}

	@Test
	@Transactional
	@DisplayName("Find by id")
	public void findById() {
		timecontrolDao.save(timecontrol);
		assertEquals(timecontrol, timecontrolDao.findById(timecontrol.getId()));
	}

	@Test
	@Transactional
	@DisplayName("Find all")
	public void findAll() {
		timecontrolDao.save(timecontrol);
		TsscTimecontrol timecontrol2 = new TsscTimecontrol();
		timecontrol2.setAutostart("no");
		timecontrol2.setIntervalRunning(new BigDecimal(1));
		timecontrol2.setName("kz");
		timecontrol2.setOrder(new BigDecimal(1));
		timecontrol2.setState("activo");
		timecontrol2.setType("1");
		timecontrol2.setTimeInterval(new BigDecimal(1));
		timecontrolDao.save(timecontrol2);
		assertEquals(timecontrol, timecontrolDao.findAll().get(0));
		assertEquals(timecontrol2, timecontrolDao.findAll().get(1));
	}
	
}
