package com.pack.taller.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.pack.taller.model.TsscTimecontrol;

@Repository
@Scope("singleton")
public class TimecontrolDao implements ITimecontrolDao {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void save(TsscTimecontrol timecontrol) {
		em.persist(timecontrol);
	}

	@Override
	public void update(TsscTimecontrol timecontrol) {
		em.merge(timecontrol);
	}

	@Override
	public void delete(TsscTimecontrol timecontrol) {
		em.remove(timecontrol);
	}

	@Override
	public TsscTimecontrol findById(Long id) {
		return em.find(TsscTimecontrol.class, id);
	}

	@Override
	public List<TsscTimecontrol> findAll() {
		String jpql = "select a from TsscTimecontrol a";
		return em.createQuery(jpql).getResultList();
	}

}
