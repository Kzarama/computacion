package com.pack.taller.dao;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import com.pack.taller.model.TsscGame;

public interface IGameDao {
	
	public void save(TsscGame game);
	public void update(TsscGame game);
	public void delete(TsscGame game);
	public TsscGame findById(Long id);
	public List<TsscGame> findAll();
	public List<TsscGame> findByName(String name);
	public List<TsscGame> findByDescriptionTopic(String description);
	public List<TsscGame> findByIdTopic(Long id);
	public List<TsscGame> rangeDate(LocalDate fechaInicio, LocalDate fechaFin);
	public List<TsscGame> rangeDateHour(LocalDate fechaInicio, LocalDate fechaFin, LocalTime horaInicio, LocalTime horaFin);
	public List<TsscGame> rangeDatetTopicGame(LocalDate fecha);
	public List<TsscGame> rangeDatetStoryNoTimeGame(LocalDate fecha);
	
}
