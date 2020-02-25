package co.edu.icesi.ci.junit.spring;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import co.edu.icesi.ci.junit.spring.model.UserOrder;
import co.edu.icesi.ci.junit.spring.service.UserOderService;
import lombok.extern.java.Log;

@Log
public class JUnitSpringExample {

	@Autowired
	private UserOderService userOderService;

	@BeforeAll
	public static void setUp() {
		log.info("-----> SETUP <-----");
	}

	@Test
	public void testSampleServiceGetAccountDescription() {
		// Check if the return description has a 'Description:' string.
		assertTrue(userOderService.getOrderDescription(0).contains("Description:"));
	}

	@Test
	public void testSampleServiceGetAccountCode() {
		// Check if the return description has a 'Code:' string.
		assertTrue(userOderService.getOrderStringCode(0).contains("Code:"));
	}

	@Test
	public void testSampleServiceCreateNewOrder() {
		UserOrder newOrder = new UserOrder();
		newOrder.setSecurityCode("XYZ");
		newOrder.setDescription("Description");
		if (newOrder != null) {
			assertNotNull("Security isn't null", newOrder.getSecurityCode());
			assertNotNull("Description isn't not null", newOrder.getDescription());
		}

		assertNotNull(newOrder,"New Order is not null");

	}

	@Test
	public void testSampleServiceGetOrder() {

		UserOrder existingOrder = userOderService.getOrder(0);

		if (existingOrder != null) {
			assertNotNull("Security isn't null", existingOrder.getSecurityCode());
			assertNotNull("Description isn't null", existingOrder.getDescription());
		}

		assertNotNull(existingOrder,"Object is not null");
	}

	@AfterAll
	public static void afterTest() {
		log.info("-----> DESTROY <-----");
	}
}
