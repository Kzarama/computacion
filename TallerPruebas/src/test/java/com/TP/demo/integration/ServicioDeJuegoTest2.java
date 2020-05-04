package com.TP.demo.integration;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.TP.demo.TPApplication;
import com.TP.demo.exceptions.ElemIdAlrExistException;
import com.TP.demo.model.TsscGame;
import com.TP.demo.model.TsscStory;
import com.TP.demo.model.TsscTimecontrol;
import com.TP.demo.model.TsscTopic;
import com.TP.demo.services.IServicioJuego;
import com.TP.demo.services.IServicioTema;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= {TPApplication.class})
public class ServicioDeJuegoTest2 {

	@Autowired
	private IServicioJuego gameService;
	@Autowired
	private IServicioTema topicService;
	
	private TsscGame defaultGame;
	private TsscTopic defaultAssociatedTopic;
	
	
	@Before
	public void anadirElJuegoPorDefecto(){
		defaultAssociatedTopic = new TsscTopic();
		defaultAssociatedTopic.setName("Error del profesor");
		defaultAssociatedTopic.setDefaultGroups(30);
		defaultAssociatedTopic.setDefaultSprints(20);
		List<TsscStory> stories = new ArrayList<>();
		TsscStory storyOne = new TsscStory();
		storyOne.setAltDescripton("El profesor hizo mal las tablas");
		TsscStory storyTwo = new TsscStory();
		storyTwo.setAltDescripton("No se corrigio toncees lo mando asi");
		stories.add(storyOne);
		stories.add(storyTwo);
		List<TsscTimecontrol> schedule = new ArrayList<TsscTimecontrol>();
		TsscTimecontrol scheduleOne = new TsscTimecontrol();
		scheduleOne.setName("Cronograma chukistrukis 1");
		TsscTimecontrol scheduleTwo = new TsscTimecontrol();
		scheduleTwo.setName("Cronograma chukistrukis 2");
		schedule.add(scheduleOne);
		schedule.add(scheduleTwo);
		defaultAssociatedTopic.setTsscStories(stories);
		defaultAssociatedTopic.setTsscTimesControls(schedule);
		topicService.addTopic(defaultAssociatedTopic);
		defaultGame = new TsscGame();
		defaultGame.setName("La mujer que realmente valoro NO mi amor");
		defaultGame.setTsscTopic(defaultAssociatedTopic);
	}
	
	@Test
	public void asociarLasHistoriasYLaLineaDeTiempoDelTemaAlJuego() {
		gameService.addGame(defaultGame);
		TsscGame j = gameService.getGame(defaultGame.getId());
		TsscStory story = j.getTsscStories().get(0);
		assertTrue(j.getTsscStories().size() == 2);
		assertTrue(j.getTsscStories().get(0).getAltDescripton().equals("El profesor hizo mal las tablas"));
		assertAll(() -> {
			assertTrue(j.getTsscStories().get(0).getAltDescripton().equals("El profesor hizo mal las tablas"));
		}, () -> {
			assertTrue(j.getTsscStories().get(1).getAltDescripton().equals("No se corrigio toncees lo mando asi"));
		}, () ->{
			assertTrue(j.getTsscTimecontrols().get(0).getName().equals("Cronograma chukistrukis 1"));
		}, () ->{
			assertTrue(j.getTsscTimecontrols().get(1).getName().equals("Cronograma chukistrukis 2"));
		}, () ->{
			
		});
	}
	
}
