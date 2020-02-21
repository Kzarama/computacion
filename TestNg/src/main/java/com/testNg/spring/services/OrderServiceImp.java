package com.testNg.spring.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.testNg.spring.exceptions.ExceptionOrderNotFound;
import com.testNg.spring.exceptions.ExceptionSaveOrder;
import com.testNg.spring.model.Order;
import com.testNg.spring.repositories.OrderRepositoryImp;

public class OrderServiceImp implements OrderService {
	
	@Autowired
	private OrderRepositoryImp orderService;
	
	@Override
	public void saveOrder(Order order) throws ExceptionSaveOrder {
		orderService.saveOrder(order);
	}

	@Override
	public void updateOrder(Order order) throws ExceptionSaveOrder, ExceptionOrderNotFound {
		orderService.updateOrder(order);
	}

}
