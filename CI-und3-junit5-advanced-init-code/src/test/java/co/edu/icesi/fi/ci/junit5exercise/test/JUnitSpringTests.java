package co.edu.icesi.fi.ci.junit5exercise.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import co.edu.icesi.fi.ci.junit5exercise.model.Order;
import co.edu.icesi.fi.ci.junit5exercise.service.OrderService;


public class JUnitSpringTests {

	private OrderService orderService;

	public JUnitSpringTests(OrderService orderService) {
		this.orderService = orderService;
	}

	@BeforeAll
	public static void setUp() {
		System.out.println("-----> SETUP <-----");
	}

	@Test	
	public void testSampleServiceCreateNewOrder() {
		Order newOrder = new Order();
		newOrder.setSecurityCode("XYZ");
		newOrder.setDescription("Description");
		newOrder.setOrderId(1);
		if (newOrder != null) {
			assertTrue(orderService.createOrder(newOrder) instanceof Order);
			assertNotNull("Security isn't null", newOrder.getSecurityCode());
			assertNotNull("Description isn't not null", newOrder.getDescription());
		}
		assertNotNull(newOrder, "New Order is not null");

	}

	@Test
	public void testSampleServiceGetAccountDescription() {
		// Check if the return description has a 'Description:' string.
		assertTrue(orderService.getOrderDescription(1).contains("Description"));
	}

	@Test
	public void testSampleServiceGetAccountCode() {
		// Check if the return description has a 'Code:' string.
		assertTrue(orderService.getOrderStringCode(1).contains("XYZ"));
	}

	@Test
	public void testSampleServiceGetOrder() {

		Order existingOrder = orderService.getOrder(1);

		if (existingOrder != null) {
			assertTrue(orderService.getOrder(1) instanceof Order);
			assertNotNull("Security isn't null", existingOrder.getSecurityCode());
			assertNotNull("Description isn't null", existingOrder.getDescription());
		}
		assertNotNull(existingOrder, "Object is not null");
	}

	@AfterAll
	public static void afterTest() {
		System.out.println("-----> DESTROY <-----");
	}
}
