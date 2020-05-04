package com.pack.taller.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.pack.taller.model.TsscStory;

@Repository
@Scope("singleton")
public class StoryDao implements IStoryDao {
	
	@PersistenceContext
	private EntityManager em;
	
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
