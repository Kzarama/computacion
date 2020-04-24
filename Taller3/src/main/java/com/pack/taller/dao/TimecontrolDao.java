package com.pack.taller.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.pack.taller.model.TsscTimecontrol;

public class TimecontrolDao implements ITimecontrolDao {
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("");
	private EntityManager em = emf.createEntityManager();
	
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
