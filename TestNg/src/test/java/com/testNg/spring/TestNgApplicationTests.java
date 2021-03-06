package com.testNg.spring;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.testNg.spring.boot.TestNgApplication;
import com.testNg.spring.exceptions.ExceptionOrderNotFound;
import com.testNg.spring.exceptions.ExceptionSaveOrder;
import com.testNg.spring.model.Order;
import com.testNg.spring.services.OrderServiceImp;

@SpringBootTest (classes = TestNgApplication.class)
public class TestNgApplicationTests extends AbstractTestNGSpringContextTests {
	
	@Autowired
	private OrderServiceImp orderService;
	
	@BeforeMethod
	void createOrderBefore() throws ExceptionSaveOrder {
		Order order = new Order();
		order.setCreador("k");
		order.setDescripcion("Description");
		order.setFechaCreacion(LocalDate.of(2000, 1, 1));
		order.setId("1");
		orderService.saveOrder(order);
	}
	
	@AfterMethod
	void deleteOrderAfter() throws ExceptionOrderNotFound {
		orderService.deleteOrder("1");
	}
	
	@Test
	@DisplayName("Save order")
	void testSaveOrder() throws ExceptionOrderNotFound, ExceptionSaveOrder {
		Order order = new Order();
		order.setCreador("2");
		order.setDescripcion("2");
		order.setFechaCreacion(LocalDate.of(2001, 2, 2));
		order.setId("2");
		orderService.saveOrder(order);
		assertEquals(order, orderService.getOrder("2"));
	}
	
	@Test
	@DisplayName("Update order")
	void testUpdateOrder() throws ExceptionOrderNotFound, ExceptionSaveOrder {
		Order order = orderService.getOrder("1");
		assertAll("Test before update",
				() -> assertEquals("k", orderService.getOrder("1").getCreador()),
				() -> assertEquals(LocalDate.of(2000, 1, 1), orderService.getOrder("1").getFechaCreacion()),
				() -> assertEquals("Description", orderService.getOrder("1").getDescripcion())
				);
		order.setCreador("z");
		order.setFechaCreacion(LocalDate.of(2020, 1, 1));
		order.setDescripcion("New Description");
		orderService.updateOrder(order);
		assertAll("Test after update",
				() -> assertEquals("z", orderService.getOrder("1").getCreador()),
				() -> assertEquals(LocalDate.of(2020, 1, 1), orderService.getOrder("1").getFechaCreacion()),
				() -> assertEquals("New Description", order.getDescripcion())
				);
	}
	
	@Test
	@DisplayName("Create order exception exists already")
	void testCreateOrderExceptionOrderSave() throws ExceptionSaveOrder, ExceptionOrderNotFound {
		Order order = new Order();
		order.setCreador("k");
		order.setDescripcion("Description");
		order.setFechaCreacion(LocalDate.of(2000, 1, 1));
		order.setId("1");
		assertThrows(ExceptionSaveOrder.class,
				() -> {orderService.saveOrder(order);
				});
	}
	
	@Test
	@DisplayName("Create order exception order null")
	void testCreateOrderExceptionOrderNull() throws ExceptionSaveOrder {
		Order order = null;
		assertThrows(ExceptionSaveOrder.class,
				() -> {orderService.saveOrder(order);
				});
	}
	
	@Test
	@DisplayName("Create order exception order null")
	void testUpdateOrderExceptionOrderNull() {
		Order order = null;
		assertThrows(ExceptionSaveOrder.class,
				() -> {orderService.updateOrder(order);},
				"Order empty");
	}
	
	@Test
	@DisplayName("Create order exception order not exists")
	void testUpdateOrderExceptionOrderNotExists() {
		Order order = new Order();
		order.setCreador("k");
		order.setDescripcion("Description");
		order.setFechaCreacion(LocalDate.of(2000, 1, 1));
		order.setId("2");
		assertThrows(ExceptionOrderNotFound.class,
				() -> {orderService.updateOrder(order);},
				"Order not exist");
	}
	
}
