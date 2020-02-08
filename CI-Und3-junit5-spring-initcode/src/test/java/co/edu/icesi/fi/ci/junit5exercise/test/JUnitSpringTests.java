package co.edu.icesi.fi.ci.junit5exercise.test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import co.edu.icesi.fi.ci.junit5exercise.main.AppConfig;
import co.edu.icesi.fi.ci.junit5exercise.model.Order;
import co.edu.icesi.fi.ci.junit5exercise.service.OrderService;

//@ExtendWith(AppConfig.class)
@ContextConfiguration(classes = AppConfig.class) 
public class JUnitSpringTests {
	
	//@Autowired
	private OrderService orderService;
	private Order order;
	
	public JUnitSpringTests(OrderService orderService) {
		this.orderService = orderService;
	}
	
	@BeforeAll
	static void initializingTests() {
		System.out.println("initializing tests");
	}
	
	@BeforeEach
	void initializeOrder() {
		order = new Order();
		order.setDescription("0");
		order.setSecurityCode("0");
		order.setOrderDate(new Date(2020, 02, 06));
		order.setOrderId(0);
		order.setOrderStatus("0");
		
	}
	
	@Test
	void getDescription() {
		orderService.createOrder(order);
		assertTrue(orderService.getOrderDescription(0).startsWith("Description:"));
	}
	
	@Test
	void getAccountCode() {
		assertTrue(orderService.getOrderStringCode(0).startsWith("Account Code:"));
	}
	
	@Test
	void createOrder() {
		assertAll("create orders", 
				() -> assertNotNull(order), 
				() -> assertNotNull(order.getDescription()), 
				() -> assertNotNull(order.getSecurityCode())
				);
	}
	
	@Test
	void queryOrder() {
		assertAll("query orders", 
				() -> assertNotNull(order.getDescription()), 
				() -> assertNotNull(order.getSecurityCode()),
				() -> assertNotNull(order.getOrderDate()),
				() -> assertNotNull(order.getOrderId()),
				() -> assertNotNull(order.getOrderStatus()) 
				);
	}
	
	@AfterAll
	static void testsFinished() {
		System.out.println("tests finished");
	}
	
	public static void setUp() {
		System.out.println("-----> SETUP <-----");
	}

	public void testSampleServiceGetAccountDescription() {
		// Check if the return description has a 'Description:' string.
	}

	public void testSampleServiceGetAccountCode() {
		// Check if the return description has a 'Code:' string.
	}

	public static void afterTest() {
		System.out.println("-----> DESTROY <-----");
	}
}
