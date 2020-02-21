package com.testNg.spring.repositories;

import com.testNg.spring.model.Order;

public interface OrderRepository {
	
	public void safeOrder(Order order);
	public void updateOrder(Order order);
	
}
