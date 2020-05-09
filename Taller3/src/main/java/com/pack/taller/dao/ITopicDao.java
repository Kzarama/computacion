package com.pack.taller.dao;

import java.time.LocalDate;
import java.util.List;

import com.pack.taller.model.TsscTopic;

public interface ITopicDao {
	
	public void save(TsscTopic topic);
	public void update(TsscTopic topic);
	public void delete(TsscTopic topic);
	public TsscTopic findById(Long id);
	public List<TsscTopic> findAll();
	public List<TsscTopic> findByName(String name);
	public List<TsscTopic> findByDescription(String description);
	
}
