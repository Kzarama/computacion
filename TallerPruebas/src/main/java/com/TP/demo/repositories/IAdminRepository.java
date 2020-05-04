package com.TP.demo.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.TP.demo.model.TsscAdmin;

@Repository
public interface IAdminRepository extends CrudRepository<TsscAdmin, Long> {
	
	List<TsscAdmin> findByUser(String user);
	
}
