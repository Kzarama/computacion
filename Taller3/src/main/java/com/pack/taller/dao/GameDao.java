package com.pack.taller.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.pack.taller.model.TsscGame;

public class GameDao implements IGameDao {
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("");
	private EntityManager em = emf.createEntityManager();
	
	@Override
	public void save(TsscGame game) {
		em.persist(game);
	}

	@Override
	public void update(TsscGame game) {
		em.merge(game);
	}

	@Override
	public void delete(TsscGame game) {
		em.remove(game);
	}

	@Override
	public TsscGame findById(Long id) {
		return em.find(TsscGame.class, id);
	}

	@Override
	public List<TsscGame> findAll() {
		String jpql = "select a from TsscGame a";
		return em.createQuery(jpql).getResultList();
	}

	@Override
	public TsscGame findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TsscGame findByDescription(String description) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
