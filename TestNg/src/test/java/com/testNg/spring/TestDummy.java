package com.testNg.spring;

import static org.junit.Assert.assertTrue;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.testNg.spring.boot.TestNgApplication;

@SpringBootTest (classes = TestNgApplication.class)
public class TestDummy extends AbstractTestNGSpringContextTests {
	
	@Test(groups = {"grupo1"})
	void dummy1() {
		assertTrue(true);
	}
	
	@Test(groups = {"grupo1"})
	void dummy2() {
		assertTrue(true);
	}
	
	@Test(groups = {"grupo2"})
	void dummy3() {
		assertTrue(true);
	}
	
	@Test(groups = {"grupo2"})
	void dummy4() {
		assertTrue(true);
	}
	
	@Test(groups = {"grupo1", "grupo2"})
	void dummy5() {
		assertTrue(true);
	}
	
}
