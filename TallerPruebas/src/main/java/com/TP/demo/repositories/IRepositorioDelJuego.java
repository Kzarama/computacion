package com.TP.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.TP.demo.model.TsscGame;

@Repository
public interface IRepositorioDelJuego extends CrudRepository<TsscGame, Long> {

}
