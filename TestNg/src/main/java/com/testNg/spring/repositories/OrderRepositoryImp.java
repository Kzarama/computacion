package com.testNg.spring.repositories;

import java.util.HashMap;

import com.testNg.spring.exceptions.ExceptionOrderNotFound;
import com.testNg.spring.exceptions.ExceptionSaveOrder;
import com.testNg.spring.model.Order;

public class OrderRepositoryImp implements OrderRepository {
	
	HashMap<String, Order> orderHash = new HashMap<String, Order>();
	
	@Override
	public void saveOrder(Order order) throws ExceptionSaveOrder {
		if (order == null) {
			throw new ExceptionSaveOrder("order empty");
		} else if (orderHash.containsKey(order.getId())) {
			throw new ExceptionSaveOrder("Order exist already");
		} else {
			orderHash.put(order.getId(), order);
		}
	}

	@Override
	public void updateOrder(Order order) throws ExceptionSaveOrder, ExceptionOrderNotFound {
		if (order == null) {
			throw new ExceptionSaveOrder("order empty");
		} else if (!orderHash.containsValue(order)) {
			throw new ExceptionOrderNotFound("Order not exist");
		} else {
			Order existingOrder = orderHash.get(order.getId());
			existingOrder.setCreador(order.getCreador());
			existingOrder.setFechaCreacion(order.getFechaCreacion());
			existingOrder.setDescripcion(order.getDescripcion());
			orderHash.replace(order.getId(), order);
		}
	}

	@Override
	public Order getOrder(String id) throws ExceptionOrderNotFound {
		return orderHash.get(id);
	}

	@Override
	public void deleteOrder(String id) throws ExceptionOrderNotFound {
		orderHash.remove(id);
	}
	
}
