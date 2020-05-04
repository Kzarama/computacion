package com.TP.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.TP.demo.model.TsscGroup;
import com.TP.demo.model.TsscStory;


@Repository
public interface IRespositorioHistoria extends CrudRepository<TsscStory, Long> {

}
