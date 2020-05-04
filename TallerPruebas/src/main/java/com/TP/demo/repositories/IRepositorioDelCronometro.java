package com.TP.demo.repositories;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.TP.demo.model.TsscTimecontrol;

@Repository
public interface IRepositorioDelCronometro extends CrudRepository<TsscTimecontrol, Long> {

	
}
