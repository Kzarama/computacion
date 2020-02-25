package co.edu.icesi.ci.junit.spring.service;

import co.edu.icesi.ci.junit.spring.model.UserOrder;

public interface UserOderService {

	public String getOrderDescription(int id);

	public String getOrderStringCode(int id);

	public UserOrder getOrder(int id);

	public UserOrder createOrder(UserOrder order);

	public UserOrder updateOrder(UserOrder order);

	public void deleteOrder(UserOrder order);

}
