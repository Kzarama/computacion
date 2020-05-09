package com.pack.taller.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.pack.taller.model.TsscAdmin;
import com.pack.taller.model.TsscGame;

@Repository
@Scope("singleton")
public class GameDao implements IGameDao {
	
	@PersistenceContext
	private EntityManager em;
	
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
	public List<TsscGame> findAll() {
		String jpql = "select a from TsscGame a";
		return em.createQuery(jpql).getResultList();
	}
	
	@Override
	public TsscGame findById(Long id) {
		return em.find(TsscGame.class, id);
	}

	@Override
	public List<TsscGame> findByName(String name) {
		String jpql = "Select a from TsscGame a where a.name = '" + name + "'";
		return em.createQuery(jpql).getResultList();
	}

	@Override
	public List<TsscGame> findByDescriptionTopic(String description) {
		String jpql = "Select g from TsscGame g, TsscTopic t where t.description = '" + description + "' and g.tsscTopic.id = t.id";
		return em.createQuery(jpql).getResultList();
	}

	@Override
	public List<TsscGame> findByIdTopic(Long id) {
		String jpql = "Select g from TsscGame g, TsscTopic t where t.id = '" + id + "' and g.tsscTopic.id = t.id";
		return em.createQuery(jpql).getResultList();
	}

	@Override
	public List<TsscGame> rangeDate(LocalDate fechaInicio, LocalDate fechaFin) {
		String jpql = "Select a from TsscGame a where a.scheduledDate between '" + fechaInicio + "' and '" + fechaFin + "'";
		return em.createQuery(jpql).getResultList();
	}

	@Override
	public List<TsscGame> rangeDateHour(LocalDate fechaInicio, LocalDate fechaFin, LocalTime horaInicio,
			LocalTime horaFin) {
		String jpql = "Select a from TsscGame a where a.scheduledDate between '" + fechaInicio + "' and '" + fechaFin + "' and a.scheduledTime between '" + horaInicio +"' and '" + horaFin + "'";
		return em.createQuery(jpql).getResultList();
	}

	@Override
	public List<TsscGame> rangeDatetTopicGame(LocalDate fecha) {
		String jpql = "select a from TsscGame a where a.scheduledDate = '" + fecha + "' order by scheduledTime desc";
		return em.createQuery(jpql).getResultList();
	}

	@Override
	public List<TsscGame> rangeDatetStoryNoTimeGame(LocalDate fecha) {
		String jpql = "select a from TsscGame a where a.scheduledDate = '" + fecha + "' and (11 > (select count(b) from TsscGame.tsscStory b) or a.tsscTimecontrols is null)";
		return em.createQuery(jpql).getResultList();
	}

}
