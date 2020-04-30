package com.pack.taller.dao;

import java.time.LocalDate;
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
	public List<TsscGame> findByName(String name) {
		String jpql = "Select a from TsscGame a where a.name == '" + name + "'";
		return em.createQuery(jpql).getResultList();
	}

	@Override
	public List<TsscGame> findByDescription(String description) {
		String jpql = "Select a from TsscGame a where a.description == '" + description + "'";
		return em.createQuery(jpql).getResultList();
	}

	@Override
	public List<TsscGame> findByIdTopic(Long id) {
		String jpql = "Select a.tsscGames from TsscTopic a where a.id == '" + id + "'";
		return em.createQuery(jpql).getResultList();
	}

	@Override
	public List<TsscGame> rangeDate(LocalDate fechaInicio, LocalDate fechaFin) {
		
		return null;
	}

	@Override
	public List<TsscGame> rangeDateHour(LocalDate fechaInicio, LocalDate fechaFin) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
