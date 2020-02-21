package com.testNg.spring.repositories;

import java.util.HashMap;

import com.testNg.spring.model.Order;

public class OrderRepositoryImp implements OrderRepository {
	
	HashMap<String, Order> orderHash = new HashMap<String, Order>();
	
	@Override
	public void safeOrder(Order order) {
		orderHash.put(order.getId(), order);
	}

	@Override
	public void updateOrder(Order order) {
		Order existingOrder = orderHash.get(order.getId());
		existingOrder.setCreador(order.getCreador());
		existingOrder.setFechaCreacion(order.getFechaCreacion());
		existingOrder.setDescripcion(order.getDescripcion());
		orderHash.replace(order.getId(), order);
	}
	
}
