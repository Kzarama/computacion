package com.TP.demo.services;

import com.TP.demo.exceptions.ElemNotExiPreviousException;
import com.TP.demo.model.TsscStory;
import com.TP.demo.model.TsscTopic;

public interface IServicioTema {

	public boolean addTopic(TsscTopic topic);
	
	public void deleteTopic(Long id);
	
	public void editTopic(Long key, TsscTopic topic) throws ElemNotExiPreviousException;
	
	public TsscTopic getTopic(Long key);
	
	public boolean exists(Long key);
	
	public Iterable<TsscTopic> findAll();
	
	public TsscTopic findById(Long id);
	
}
