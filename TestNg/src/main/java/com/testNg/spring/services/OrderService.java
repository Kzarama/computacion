package com.testNg.spring.services;

import com.testNg.spring.exceptions.ExceptionOrderNotFound;
import com.testNg.spring.exceptions.ExceptionSaveOrder;
import com.testNg.spring.model.Order;

public interface OrderService {
	
	public void saveOrder(Order order) throws ExceptionSaveOrder;
	public void updateOrder(Order order) throws ExceptionSaveOrder, ExceptionOrderNotFound;
	public Order getOrder(String id) throws ExceptionOrderNotFound;
	public void deleteOrder(String id) throws ExceptionOrderNotFound;
	
}
