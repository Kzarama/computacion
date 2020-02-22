package co.edu.icesi.fi.ci.junit5exercise.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.edu.icesi.fi.ci.junit5exercise.model.Order;
import co.edu.icesi.fi.ci.junit5exercise.service.OrderService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class JUnitSpringTests {
	
	@Autowired
	private OrderService orderService;

	@BeforeAll
	public static void setUp() {
		System.out.println("-----> SETUP <-----");
	}
	
	@BeforeEach
	public void createOrder() {
		Order newOrder = new Order();
		newOrder.setDescription("Description");
		newOrder.setOrderDate(LocalDate.of(2000, 1, 1));
		newOrder.setOrderId(1);
		newOrder.setOrderStatus("0");
		newOrder.setSecurityCode("XYZ");
		orderService.createOrder(newOrder);
	}
	
	@Nested
	@DisplayName("Pruebas de creacion")
	class PruebasCreacion {
		
		@Test
		@Tag("mutable")
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
		
		@Test
		@Tag("mutable")
		public void updateOrder1() {
			assertEquals("Description", orderService.getOrderDescription(1));
			Order order = orderService.getOrder(1);
			order.setDescription("New description");
			orderService.updateOrder(order);
			assertEquals("New description", orderService.getOrderDescription(1));
		}
		
		@Test
		@Tag("mutable")
		public void updateOrder2() {
			assertEquals("0", orderService.getOrderStatus(1));
			Order order = orderService.getOrder(1);
			order.setOrderStatus("1");
			orderService.updateOrder(order);
			assertEquals("1", orderService.getOrderStatus(1));
		}
		
		@Test
		@Tag("mutable")
		public void updateOrder3() {
			assertEquals(LocalDate.of(2000, 1, 1), orderService.getOrderDate(1));
			Order order = orderService.getOrder(1);
			order.setOrderDate(LocalDate.of(2020, 1, 1));
			orderService.updateOrder(order);
			assertEquals(LocalDate.of(2020, 1, 1), orderService.getOrderDate(1));
		}
		
		@Test
		@Tag("mutable")
		public void deleteOrder() {
			assertEquals("Description", orderService.getOrderDescription(1));
		}
		
	}
	
	@Nested
	@DisplayName("Pruebas de consulta")
	class PruebasConsulta {
		
		@Test
		@Tag("no_mutable")
		@DisplayName("Get account description")
		public void testSampleServiceGetAccountDescription() {
			// Check if the return description has a 'Description:' string.
			assertTrue(orderService.getOrderDescription(1).contains("Description"));
		}

		@Test
		@Tag("no_mutable")
		@DisplayName("Get account code")
		public void testSampleServiceGetAccountCode() {
			// Check if the return description has a 'Code:' string.
			assertTrue(orderService.getOrderStringCode(1).contains("XYZ"));
		}

		@Test
		@Tag("no_mutable")
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
		
		@Test
		@Tag("no_mutable")
		public void getOrderDate() {
			assertEquals(LocalDate.of(2000, 1, 1), orderService.getOrderDate(1));
		}
		
		@Test
		@Tag("no_mutable")
		public void getOrderStatus() {
			assertEquals("0", orderService.getOrderStatus(1));
		}
		
		@Test
		@Tag("no_mutable")
		public void getOrderDescription() {
			assertEquals("Description", orderService.getOrderDescription(1));
		}
		
	}

	@AfterAll
	public static void afterTest() {
		System.out.println("-----> DESTROY <-----");
	}
}
