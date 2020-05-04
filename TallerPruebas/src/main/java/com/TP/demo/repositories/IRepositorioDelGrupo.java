package com.TP.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.TP.demo.model.TsscGroup;

@Repository
public interface IRepositorioDelGrupo extends CrudRepository<TsscGroup, Long>{

}
