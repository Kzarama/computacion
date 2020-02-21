package com.testNg.spring.repositories;

import com.testNg.spring.exceptions.ExceptionOrderNotFound;
import com.testNg.spring.exceptions.ExceptionSaveOrder;
import com.testNg.spring.model.Order;

public interface OrderRepository {
	
	public void saveOrder(Order order) throws ExceptionSaveOrder;
	public void updateOrder(Order order) throws ExceptionSaveOrder, ExceptionOrderNotFound;
	
}
