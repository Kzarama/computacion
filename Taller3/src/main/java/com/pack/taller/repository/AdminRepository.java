package com.pack.taller.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.pack.taller.model.TsscAdmin;

public interface AdminRepository extends CrudRepository<TsscAdmin, Long> {
	
	List<TsscAdmin> findByUser(String user);
	
}
