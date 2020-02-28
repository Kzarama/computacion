package com.testNg.spring;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.Test;

@DisplayName("Test TestNG")
@Test
@ContextConfiguration(locations = { "classpath:spring-test-config.xml" })
public class TestDummy {
	
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
