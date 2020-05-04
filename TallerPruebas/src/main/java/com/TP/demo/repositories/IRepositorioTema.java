package com.TP.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.TP.demo.model.TsscGroup;
import com.TP.demo.model.TsscTopic;


@Repository
public interface IRepositorioTema extends CrudRepository<TsscTopic, Long>{

	
	
}
