package com.TP.demo.unitaryTest;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.TP.demo.exceptions.ElemNotExiPreviousException;
import com.TP.demo.model.TsscGame;
import com.TP.demo.model.TsscStory;
import com.TP.demo.model.TsscTopic;
import com.TP.demo.repositories.IRepositorioDelJuego;
import com.TP.demo.repositories.IRespositorioHistoria;
import com.TP.demo.services.IServicioHistoria;
import com.TP.demo.services.IServicioJuego;
import com.TP.demo.services.ServicioHistoria;
import com.TP.demo.services.ServicioJuego;

@RunWith(MockitoJUnitRunner.Silent.class)
public class HistoriaServicioTest {

	private IServicioHistoria servicioHistoria;
	private IRespositorioHistoria repositorioHistoria;
	private IRepositorioDelJuego repositorioJuego;
	
	@Before
	public void inicializarElServicioDeTemas() {
		repositorioHistoria = mock(IRespositorioHistoria.class);
		repositorioJuego = mock(IRepositorioDelJuego.class); 		
		servicioHistoria = spy(new ServicioHistoria(repositorioHistoria, repositorioJuego));
	}	
	
	@Test
	public void cuandoGuardarLaHistoriaConLaAsociacionDeJuegoExistentesYCumplirConLasCondicionesQueSonTrue() {
		TsscStory historia = new TsscStory();
		historia.setBusinessValue(new BigDecimal(500));
		historia.setPriority(new BigDecimal(40.0));
		historia.setInitialSprint(new BigDecimal(30.0));
		historia.setTsscGame(new TsscGame());
		doReturn(true).when(repositorioJuego).existsById(historia.getTsscGame().getId());
		assertTrue(this.servicioHistoria.addStory(historia));
	}
	
	@Test
	public void cuandoGuardarLaHistoriaSinLaAsociacionDeJuegoExistenteYCumplirConLasCondicionesQueDanFalse() {
		TsscStory historia = new TsscStory();
		historia.setBusinessValue(new BigDecimal(0));
		historia.setPriority(new BigDecimal(0));
		historia.setInitialSprint(new BigDecimal(0));
		historia.setTsscGame(new TsscGame());
		doReturn(false).when(repositorioHistoria).existsById(historia.getTsscGame().getId());
		assertFalse(this.servicioHistoria.addStory(historia));
	}
	
	@Test
	public void cuandoGuardarLaHistoriaConLaAsociacionDeJuegosYNoSeCumplenLasCondicionesQueDanFalse() {
		TsscStory historia = new TsscStory();
		historia.setBusinessValue(new BigDecimal(0));
		historia.setPriority(new BigDecimal(0));
		historia.setInitialSprint(new BigDecimal(0));
		historia.setTsscGame(new TsscGame());
		doReturn(true).when(repositorioHistoria).existsById(historia.getTsscGame().getId());
		assertFalse(this.servicioHistoria.addStory(historia));
	}
	
	@Test
	public void cuandoEditoLaHistoriaConLaAsociacionDeJuegoExistenteYCumplaConLasCondicionesDeGuardado() throws ElemNotExiPreviousException {
		Long prueba1 = new Long(12);
		Long prueba2 = new Long(15);
		doReturn(Optional.of(new TsscStory())).when(repositorioHistoria).findById(prueba1);
		doReturn(Optional.of(new TsscGame())).when(repositorioJuego).findById(prueba2);
		TsscStory story = repositorioHistoria.findById(new Long(12)).get();
		story.setBusinessValue(new BigDecimal(700.0));
		story.setPriority(new BigDecimal(700.0));
		story.setInitialSprint(new BigDecimal(700.0));
		story.setTsscGame(this.repositorioJuego.findById(prueba2).get());
		doReturn(true).when(repositorioJuego).existsById(story.getTsscGame().getId());
		this.servicioHistoria.editStory(prueba1, story);
		verify(repositorioHistoria, times(1)).save(story);
	}
	
	@Test
	public void cuandoEditoLaHistoriaConLaAsociacionDeJuegoExistenteYQueNoCumplaConLasCondicionesDeGuardado() throws ElemNotExiPreviousException {
		Long prueba1 = new Long(12);
		Long prueba2 = new Long(15);
		doReturn(Optional.of(new TsscStory())).when(repositorioHistoria).findById(prueba1);
		doReturn(Optional.of(new TsscGame())).when(repositorioJuego).findById(prueba2);
		TsscStory story = repositorioHistoria.findById(new Long(12)).get();
		story.setBusinessValue(new BigDecimal(0));
		story.setPriority(new BigDecimal(0));
		story.setInitialSprint(new BigDecimal(0));
		story.setTsscGame(this.repositorioJuego.findById(prueba2).get());
		this.servicioHistoria.editStory(prueba1, story);
		verify(repositorioHistoria, times(0)).save(story);
	}
	
	
	@Test
	public void cuandoEditoLaHistoriaSinLaAsociacionDeJuegoExistenteSinGuardarYLanzaExcepcion() throws ElemNotExiPreviousException {
		Long prueba1 = new Long(12);
		Long prueba2 = new Long(15);
		doReturn(Optional.of(new TsscStory())).when(repositorioHistoria).findById(prueba1);
		doReturn(null).when(repositorioJuego).findById(prueba2);
		TsscStory story = repositorioHistoria.findById(new Long(12)).get();
		story.setTsscGame(null);
		verify(repositorioHistoria, times(0)).save(story);
	}
	
	@Test
	public void cuandoEditoUnaHistoriaQueNoExistiaPreviamenteYNoLaGuardo() {
		Long prueba = new Long(12);
		TsscStory story = new TsscStory();
		doReturn(Optional.empty()).when(repositorioHistoria).findById(prueba);
		verify(repositorioHistoria, times(0)).save(story);
	}
	

	@Test
	public void cuandoEditoLaHistoriaConIDNuloSinGuardarYLanzaExcepcion() {
		doThrow(new IllegalArgumentException()).when(repositorioHistoria).findById(null);
		TsscStory historia = new TsscStory();
		assertThrows(IllegalArgumentException.class, () -> {
			this.servicioHistoria.editStory(null, historia);
		});
		verify(repositorioHistoria, times(0)).save(historia);
	}
	
	
}
