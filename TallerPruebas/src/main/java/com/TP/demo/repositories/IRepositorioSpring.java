package com.TP.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.TP.demo.model.TsscGroup;
import com.TP.demo.model.TsscSprint;

@Repository
public interface IRepositorioSpring extends CrudRepository<TsscSprint, Long> {

}
