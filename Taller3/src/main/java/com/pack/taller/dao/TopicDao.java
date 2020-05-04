package com.pack.taller.dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.pack.taller.model.TsscTopic;

@Repository
@Scope("singleton")
public class TopicDao implements ITopicDao {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void save(TsscTopic topic) {
		em.persist(topic);
	}

	@Override
	public void update(TsscTopic topic) {
		em.merge(topic);
	}

	@Override
	public void delete(TsscTopic topic) {
		em.remove(topic);
	}

	@Override
	public TsscTopic findById(Long id) {
		return em.find(TsscTopic.class, id);
	}

	@Override
	public List<TsscTopic> findAll() {
		String jpql = "Select a from TsscTopic a";
		return em.createQuery(jpql).getResultList();
	}

	@Override
	public List<TsscTopic> findByName(String name) {
		String jpql = "Select a from TsscTopic a where a.name = '" + name + "'";
		return em.createQuery(jpql).getResultList();
	}

	@Override
	public List<TsscTopic> findByDescription(String description) {
		String jpql = "Select a from TsscTopic a where a.description = '" + description + "'";
		return em.createQuery(jpql).getResultList();
	}

}
