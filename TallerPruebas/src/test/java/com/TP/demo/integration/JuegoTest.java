package com.TP.demo.integration;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import javax.validation.constraints.AssertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import com.TP.demo.TPApplication;
import com.TP.demo.exceptions.ElemIdAlrExistException;
import com.TP.demo.exceptions.ElemNotExiPreviousException;
import com.TP.demo.model.TsscGame;
import com.TP.demo.model.TsscTopic;
import com.TP.demo.repositories.IRepositorioTema;
import com.TP.demo.services.IServicioJuego;
import com.TP.demo.services.IServicioTema;
import com.TP.demo.services.ServicioJuego;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes= {TPApplication.class})
public class JuegoTest{

	@Autowired
	private IServicioJuego servicioJuego;
	@Autowired
	private IServicioTema topicService;
	
	private TsscGame defaultGame;
	private TsscTopic defaultAssociatedTopic;

	
	@Before
	public void anadirElJuegoPorDefecto() throws ElemIdAlrExistException {
		defaultAssociatedTopic = new TsscTopic();
		defaultAssociatedTopic.setName("Genero: Romantico");
		defaultAssociatedTopic.setDefaultGroups(60);
		defaultAssociatedTopic.setDefaultSprints(40);
		defaultGame = new TsscGame();
		defaultGame.setName("La mujer que realmente valoro mi amor");
	}
	
	@Test
	public void anadirJuegoSinTemaYCumplirLasCondicionesGuardar() throws ElemIdAlrExistException {
		servicioJuego.addGame(defaultGame);
		assertTrue(servicioJuego.exists(defaultGame.getId()));
	}
	
	@Test
	public void anadirElJuegoConElTemaExistenteYCumplirLasCondicionesGuardar() {
		topicService.addTopic(defaultAssociatedTopic);
		defaultGame.setTsscTopic(defaultAssociatedTopic);
		servicioJuego.addGame(defaultGame);
		assertTrue(servicioJuego.exists(defaultGame.getId()));
		
	}
	
	@Test
	public void anadirJuegoSinTemaExistenteNoGuardar() {
		defaultGame.setTsscTopic(defaultAssociatedTopic);
		servicioJuego.addGame(defaultGame);
		assertFalse(servicioJuego.exists(defaultGame.getId()));
	}
	
	@Test
	public void anadirElJuegoConElTemaExistenteNoCumplirLasCondicionesNoGuardar() {
		topicService.addTopic(defaultAssociatedTopic);
		defaultGame.setTsscTopic(defaultAssociatedTopic);
		defaultGame.setNGroups(0);
		defaultGame.setNSprints(0);
		servicioJuego.addGame(defaultGame);
		assertFalse(servicioJuego.exists(defaultGame.getId()));
	}
	
	@Test
	public void editarJuegoSinTemaYCumplirLasCondicionesGuardar() throws ElemNotExiPreviousException {
		servicioJuego.addGame(defaultGame);
		assertTrue(servicioJuego.exists(defaultGame.getId()));
		TsscGame gameOne = servicioJuego.getGame(defaultGame.getId());
		gameOne.setName("League of legends");
		servicioJuego.editGame(gameOne.getId(), gameOne);
		TsscGame gameTwo = servicioJuego.getGame(gameOne.getId());
		assertTrue(gameTwo.getName().equals("League of legends"));
	}
	
	@Test
	public void editarJuegoSinTemaYNoCumplirLasCondicionesNoGuardar() throws ElemNotExiPreviousException {
		servicioJuego.addGame(defaultGame);
		assertTrue(servicioJuego.exists(defaultGame.getId()));
		TsscGame gameOne = servicioJuego.getGame(defaultGame.getId());
		gameOne.setName("No me amo");
		gameOne.setNGroups(0);
		gameOne.setNSprints(0);
		servicioJuego.editGame(gameOne.getId(), gameOne);
		TsscGame gameTwo = servicioJuego.getGame(gameOne.getId());
		assertAll(() -> {
			assertTrue(gameTwo.getName().equals("La mujer que realmente valoro mi amor"));
		}, () -> {
			assertFalse(gameTwo.getName().equals("No me amo"));
		});
	}
	
	@Test
	public void editarElJuegoConElTemaExistenteYCumplirLasCondicionesGuardar() throws ElemNotExiPreviousException {
		topicService.addTopic(defaultAssociatedTopic);
		defaultGame.setTsscTopic(defaultAssociatedTopic);
		servicioJuego.addGame(defaultGame);
		assertTrue(servicioJuego.exists(defaultGame.getId()));
		TsscGame gameOne = servicioJuego.getGame(defaultGame.getId());
		gameOne.setName("League of legends");
		servicioJuego.editGame(gameOne.getId(), gameOne);
		TsscGame gameTwo = servicioJuego.getGame(gameOne.getId());
		assertTrue(gameTwo.getName().equals("League of legends"));
	}
	
	@Test
	public void editarElJuegoConElTemaExistenteYNoCumplirLasCondicionesNoGuardar() throws ElemNotExiPreviousException {
		topicService.addTopic(defaultAssociatedTopic);
		defaultGame.setTsscTopic(defaultAssociatedTopic);
		servicioJuego.addGame(defaultGame);
		assertTrue(servicioJuego.exists(defaultGame.getId()));
		TsscGame gameOne = servicioJuego.getGame(defaultGame.getId());
		gameOne.setName("Genero: Romantico");
		gameOne.setNGroups(0);
		gameOne.setNSprints(0);
		servicioJuego.editGame(gameOne.getId(), gameOne);
		TsscGame gameTwo = servicioJuego.getGame(gameOne.getId());
		assertAll(() -> {
			assertTrue(gameTwo.getName().equals("La mujer que realmente valoro mi amor"));
		}, () -> {
			assertFalse(gameTwo.getName().equals("Genero: Romantico"));
		});
	}
	
	@Test
	public void editarElJuegoConElTemaNoExistenteYCumplirConLasCondicionesNoGuardar() throws ElemNotExiPreviousException {
		topicService.addTopic(defaultAssociatedTopic);
		defaultGame.setTsscTopic(defaultAssociatedTopic);
		servicioJuego.addGame(defaultGame);
		TsscGame gameOne = servicioJuego.getGame(defaultGame.getId());
		gameOne.setName("lol runaterra");
		gameOne.setTsscTopic(new TsscTopic());
		servicioJuego.editGame(gameOne.getId(), gameOne);
		TsscGame gameTwo = servicioJuego.getGame(gameOne.getId());
		assertAll(() -> {
			assertTrue(gameTwo.getName().equals("La mujer que realmente valoro mi amor"));
		}, () -> {
			assertFalse(gameTwo.getName().equals("lol runaterra"));
		});
	}
	
	@Test
	public void editarElJuegoConUnTemaNoExistenteYNoCumplirConLasCondicionesNoGuardar() throws ElemNotExiPreviousException {
		topicService.addTopic(defaultAssociatedTopic);
		defaultGame.setTsscTopic(defaultAssociatedTopic);
		servicioJuego.addGame(defaultGame);
		TsscGame juegoUno = servicioJuego.getGame(defaultGame.getId());
		juegoUno.setName("Ahora ella no me ama ni en el juego");
		juegoUno.setNGroups(0);
		juegoUno.setNSprints(0);
		juegoUno.setTsscTopic(new TsscTopic());
		servicioJuego.editGame(juegoUno.getId(), juegoUno);
		TsscGame juegoDos = servicioJuego.getGame(juegoUno.getId());
		assertAll(() -> {
			assertTrue(juegoDos.getName().equals("La mujer que realmente valoro mi amor"));
		}, () -> {
			assertFalse(juegoDos.getName().equals("Ahora ella no me ama ni en el juego"));
		});
	}
	
	
}
