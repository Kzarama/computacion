package co.edu.icesi.ci.junit.spring.repositories;

import org.springframework.data.repository.CrudRepository;

import co.edu.icesi.ci.junit.spring.model.UserOrder;

public interface UserOrderRepository extends CrudRepository<UserOrder, Integer> {
	
	
	
}
