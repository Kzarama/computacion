package com.pack.taller.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.pack.taller.model.TsscTopic;

public class TopicDao implements ITopicDao {
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("");
	private EntityManager em = emf.createEntityManager();
	
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
		String jpql = "select a from TsscTopic a";
		return em.createQuery(jpql).getResultList();
	}

	@Override
	public TsscTopic findByDescription(String description) {
		return em.find(TsscTopic.class, description);
	}

}
