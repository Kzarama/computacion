package com.TP.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TP.demo.exceptions.ElemNotExiPreviousException;
import com.TP.demo.exceptions.TSGMC;
import com.TP.demo.model.TsscTopic;
import com.TP.demo.repositories.IRepositorioTema;
import com.TP.demo.repositories.IRespositorioHistoria;


@Service
public class ServicioTema implements IServicioTema{
	
	private IRepositorioTema topicRepo;
	
	@Autowired
	public ServicioTema(IRepositorioTema topicRepo) {
		// TODO Auto-generated constructor stub
		this.topicRepo = topicRepo;
	}
	
	@Override
	public boolean addTopic(TsscTopic topic) {
		try {
			if(topic.getDefaultSprints() > 0 && topic.getDefaultGroups() > 0) {
				this.topicRepo.save(topic);
				return true;
			}
			else {
				return false;
			}
		}
		catch(Exception e) {
			return false;
		}

	}

	@Override
	public void deleteTopic(Long id) {
		topicRepo.deleteById(id);
	}

	@Override
	public void editTopic(Long key, TsscTopic topic) throws ElemNotExiPreviousException, IllegalArgumentException {
		if(this.topicRepo.findById(key).isPresent() && topic != null) {
			if(topic.getDefaultGroups() > 0 && topic.getDefaultSprints() > 0) {
				this.topicRepo.save(topic);				
			}
		}
		else {
			throw new ElemNotExiPreviousException("El elemento no existia previamente");
		}
	}


	@Override
	public TsscTopic getTopic(Long key) {
		// TODO Auto-generated method stub
		return topicRepo.findById(key).get();
	}

	@Override
	public boolean exists(Long key) {
		// TODO Auto-generated method stub
		return topicRepo.existsById(key);
	}

	@Override
	public Iterable<TsscTopic> findAll() {
		return topicRepo.findAll();
	}

	@Override
	public TsscTopic findById(Long id) {
		// TODO Auto-generated method stub
		return topicRepo.findById(id).get();
	}

	
	
	
}
