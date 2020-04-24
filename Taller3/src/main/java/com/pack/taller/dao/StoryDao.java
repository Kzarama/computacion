package com.pack.taller.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.pack.taller.model.TsscStory;

public class StoryDao implements IStoryDao {
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("");
	private EntityManager em = emf.createEntityManager();
	
	@Override
	public void save(TsscStory story) {
		em.persist(story);
	}

	@Override
	public void update(TsscStory story) {
		em.merge(story);
	}

	@Override
	public void delete(TsscStory story) {
		em.remove(story);
	}

	@Override
	public TsscStory findById(Long id) {
		return em.find(TsscStory.class, id);
	}

	@Override
	public List<TsscStory> findAll() {
		String jpql = "select a from TsscStory a";
		return em.createQuery(jpql).getResultList();
	}

}
