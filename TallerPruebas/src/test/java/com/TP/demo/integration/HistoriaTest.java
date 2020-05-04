package com.TP.demo.integration;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.TP.demo.TPApplication;
import com.TP.demo.exceptions.ElemNotExiPreviousException;
import com.TP.demo.model.TsscGame;
import com.TP.demo.model.TsscStory;
import com.TP.demo.model.TsscTopic;
import com.TP.demo.services.IServicioHistoria;
import com.TP.demo.services.IServicioJuego;
import com.TP.demo.services.IServicioTema;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= {TPApplication.class})
public class HistoriaTest {

	
	@Autowired
	private IServicioHistoria storyService;
	@Autowired
	private IServicioJuego gameService;
	private TsscStory defaultStory;
	private TsscGame defaultAssociatedGame;
		
	@Before
	public void anadirPropiedadesDelTemaPorDefecto() {
		defaultAssociatedGame = new TsscGame();
		defaultAssociatedGame.setName("League of Legends");
		defaultStory = new TsscStory();
		defaultStory.setAltDescripton("Juego 5v5 muy tilt");
		defaultStory.setBusinessValue(new BigDecimal(20));
		defaultStory.setPriority(new BigDecimal(70));
		defaultStory.setInitialSprint(new BigDecimal(80));
		defaultStory.setTsscGame(defaultAssociatedGame);
		System.out.println(storyService);
	}
	
	@Test
	public void anadirLaHistoriaConElJuegoExistenteYCumplirConLasPropiedadAGuardar() {
		gameService.addGame(defaultAssociatedGame);
		storyService.addStory(defaultStory);
		assertTrue(storyService.exists(defaultStory.getId()));
	}
	
	@Test
	public void anadirLaHistoriaSinExistirElJuegoYCumplirConLasPropiedadesDeNoGuardar() {
		storyService.addStory(defaultStory);
		assertFalse(storyService.exists(defaultStory.getId()));
	}
	
	@Test
	public void anadirLaHistoriaConElJuegoExistenteYNoCumplirConLasPropiedadesNoGuardar() {
		gameService.addGame(defaultAssociatedGame);		
		defaultStory.setBusinessValue(new BigDecimal(0));
		defaultStory.setPriority(new BigDecimal(0));
		defaultStory.setInitialSprint(new BigDecimal(0));
		defaultStory.setAltDescripton("Juego 5v5 muy tilt");
		storyService.addStory(defaultStory);
		assertFalse(storyService.exists(defaultStory.getId()));
	}
	
	@Test
	public void editarLaHistoriaConElJuegoExistenteYCumplirConLasPropiedadesGuardarLosCambios() throws ElemNotExiPreviousException {
		gameService.addGame(defaultAssociatedGame);
		storyService.addStory(defaultStory);
		TsscStory story = storyService.getStory(defaultStory.getId());
		story.setAltDescripton("Ya no es tilt 5v5 si no 10v20");
		storyService.editStory(story.getId(), story);
		TsscStory storyTwo = storyService.getStory(defaultStory.getId());
		assertTrue(storyTwo.getAltDescripton().equals("Ya no es tilt 5v5 si no 10v20"));
	}
	
	@Test
	public void editarLaHistoriaConElJuegoExistenteYNoCumplirConLasPropiedadesDeNoGuardarLosCambios() throws ElemNotExiPreviousException {
		gameService.addGame(defaultAssociatedGame);
		storyService.addStory(defaultStory);
		TsscStory story = storyService.getStory(defaultStory.getId());
		story.setAltDescripton("Juego 5v5 muy tilt");
		story.setBusinessValue(new BigDecimal(0));
		story.setPriority(new BigDecimal(0));
		story.setInitialSprint(new BigDecimal(0));
		storyService.editStory(story.getId(), story);
		TsscStory storyTwo = storyService.getStory(defaultStory.getId());
		assertAll(() -> {
			assertFalse(storyTwo.getAltDescripton().equals("Historia del futbol"));
		}, () -> {
			assertTrue(storyTwo.getAltDescripton().equals("Juego 5v5 muy tilt"));
		});
	}
	
	@Test
	public void editarLaHistoriaSinExistirElJuegoYCumplirConLasPropiedadesDeNoGuardarLosCambios() throws ElemNotExiPreviousException {
		gameService.addGame(defaultAssociatedGame);
		storyService.addStory(defaultStory);
		TsscStory story = storyService.getStory(defaultStory.getId());
		story.setTsscGame(null);
		story.setAltDescripton("Juego 5v5 muy tilt");
		story.setBusinessValue(new BigDecimal(10));
		story.setPriority(new BigDecimal(30));
		story.setInitialSprint(new BigDecimal(40));
		storyService.editStory(story.getId(), story);
		TsscStory storyTwo = storyService.getStory(defaultStory.getId());
		assertAll(() -> {
			assertFalse(storyTwo.getAltDescripton().equals("Peor juego del mundo"));
		}, () -> {
			assertTrue(storyTwo.getAltDescripton().equals("Juego 5v5 muy tilt"));
		});
	}
	
	@Test
	public void editarLaHistoriaSinExistirElJuegoYNoCumplirConLasPropiedadesDeNoGuardarLosCambios() throws ElemNotExiPreviousException {
		gameService.addGame(defaultAssociatedGame);
		storyService.addStory(defaultStory);
		TsscStory story = storyService.getStory(defaultStory.getId());
		story.setTsscGame(null);
		story.setAltDescripton("Historia de la revolucion cubana");
		story.setBusinessValue(new BigDecimal(0));
		story.setPriority(new BigDecimal(0));
		story.setInitialSprint(new BigDecimal(0));
		storyService.editStory(story.getId(), story);
		TsscStory storyTwo = storyService.getStory(defaultStory.getId());
		assertAll(() -> {
			assertFalse(storyTwo.getAltDescripton().equals("Historia de la revolucion cubana"));
		}, () -> {
			assertTrue(storyTwo.getAltDescripton().equals("Juego 5v5 muy tilt"));
		});
	}
	
}
