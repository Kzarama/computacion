package com.pack.taller.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.pack.taller.model.TsscGame;

@Service
public interface GameService {
	
	public boolean saveGame(TsscGame game) throws Exception;
	public boolean saveGame2(TsscGame game) throws Exception;
	public boolean editGame(TsscGame game, Long id) throws Exception;
	public void deleteGame(Long id);
	public TsscGame findById(Long id);
	public Iterable<TsscGame> findAll();
	public List<TsscGame> findByName(String name);
	public List<TsscGame> findByDescription(String description);
	public List<TsscGame> findByIdTopic(Long id);
	public List<TsscGame> rangeDate(LocalDate fechaInicio, LocalDate fechaFin);
	public List<TsscGame> rangeDateHour(LocalDate fechaInicio, LocalDate fechaFin, LocalTime horaInicio, LocalTime horaFinal);
	public List<TsscGame> rangeDatetTopicGame(LocalDate fecha);
	public List<TsscGame> rangeDatetStoryNoTimeGame(LocalDate fecha);
	
}
