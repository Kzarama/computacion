package com.pack.taller.repository;

import org.springframework.data.repository.CrudRepository;

import com.pack.taller.model.TsscGame;

public interface GamesRepository extends CrudRepository<TsscGame, Long>{

}
