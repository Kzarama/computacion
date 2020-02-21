package co.edu.icesi.fi.ci.junit5exercise.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.edu.icesi.fi.ci.junit5exercise.model.Order;
import co.edu.icesi.fi.ci.junit5exercise.service.OrderService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RepetitiveTests {
	
	private OrderService orderService;

	@Autowired
	public RepetitiveTests(OrderService orderService) {
		this.orderService = orderService;
	}

	@BeforeAll
	public static void setUp() {
		System.out.println("-----> SETUP <-----");
	}
	
	@BeforeEach
	public void createOrder() {
		Order newOrder = new Order();
		newOrder.setDescription("Description");
		newOrder.setOrderDate(LocalDate.now());
		newOrder.setOrderId(1);
		newOrder.setOrderStatus("0");
		newOrder.setSecurityCode("XYZ");
		orderService.createOrder(newOrder);
	}
	
	@Nested
	class PruebasCreacion {
		
		@Test
		@RepeatedTest(100)
		@DisplayName("Test create new order")
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
	}
	
	@org.junit.jupiter.api.Nested
	class PruebasConsulta {
		
		@Test
		@RepeatedTest(100)
		@DisplayName("Get account description")
		public void testSampleServiceGetAccountDescription() {
			// Check if the return description has a 'Description:' string.
			assertTrue(orderService.getOrderDescription(1).contains("Description"));
		}

		@Test
		@RepeatedTest(100)
		@DisplayName("Get account code")
		public void testSampleServiceGetAccountCode() {
			// Check if the return description has a 'Code:' string.
			assertTrue(orderService.getOrderStringCode(1).contains("XYZ"));
		}

		@Test
		@RepeatedTest(100)
		@DisplayName("Get order")
		public void testSampleServiceGetOrder() {
			Order existingOrder = orderService.getOrder(1);
			if (existingOrder != null) {
				assertTrue(orderService.getOrder(1) instanceof Order);
				assertNotNull("Security isn't null", existingOrder.getSecurityCode());
				assertNotNull("Description isn't null", existingOrder.getDescription());
			}
			assertNotNull(existingOrder, "Object is not null");
		}
		
	}

	@AfterAll
	public static void afterTest() {
		System.out.println("-----> DESTROY <-----");
	}
}
