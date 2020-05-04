package com.pack.taller.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.pack.taller.model.TsscAdmin;

@Repository
@Scope("singleton")
public class AdminDao implements IAdminDao {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void save(TsscAdmin admin) {
		em.persist(admin);
	}
	
	@Override
	public void update(TsscAdmin admin) {
		em.merge(admin);
	}
	
	@Override
	public void delete(TsscAdmin admin) {
		em.remove(admin);
	}
	
	@Override
	public TsscAdmin findById(Long id) {
		return em.find(TsscAdmin.class, id);
	}
	
	@Override
	public List<TsscAdmin> findAll() {
		String jpql = "select a from TsscAdmin a";
		return em.createQuery(jpql).getResultList();
	}

	@Override
	public List<TsscAdmin> findByUser(String user) {
		String jpql = "Select a from TsscAdmin a where a.user = '" + user + "'";
		return em.createQuery(jpql).getResultList();
	}
	
}
