package com.TP.demo.unitaryTest;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.*;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.TP.demo.exceptions.ElemNotExiPreviousException;
import com.TP.demo.model.TsscGame;
import com.TP.demo.model.TsscStory;
import com.TP.demo.model.TsscTimecontrol;
import com.TP.demo.model.TsscTopic;
import com.TP.demo.repositories.IRepositorioDelJuego;
import com.TP.demo.repositories.IRepositorioTema;
import com.TP.demo.services.IServicioJuego;
import com.TP.demo.services.IServicioTema;
import com.TP.demo.services.ServicioJuego;
import com.TP.demo.services.ServicioTema;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ServicioJuegoTest {

	private IServicioJuego servicioJuego;
	private IRepositorioDelJuego repositorioJuego;
	private IRepositorioTema repositorioTema;
	private TsscTopic tema;
	
	@Before
	public void inicializarServicioTema() {
		repositorioTema = mock(IRepositorioTema.class);
		repositorioJuego = mock(IRepositorioDelJuego.class);
		servicioJuego = spy(new ServicioJuego(repositorioJuego, repositorioTema));
		tema = spy(TsscTopic.class);
	}
		
	@Test
	public void cuandoEditoJuegosConSpringYGruposMayoresQueCeroGuardados() throws ElemNotExiPreviousException {
		Long m = new Long(12);
		doReturn(Optional.of(new TsscGame())).when(repositorioJuego).findById(m);
		TsscGame juego = repositorioJuego.findById(new Long(12)).get();
		juego.setNGroups(12);
		juego.setNSprints(20);
		this.servicioJuego.editGame(m, juego);
		verify(repositorioJuego, times(1)).save(juego);
	}
	
	@Test
	public void cuandoEditoJuegosConSpringYGruposMenoresQueCeroNoGuardados() throws ElemNotExiPreviousException {
		Long m = new Long(12);
		doReturn(Optional.of(new TsscGame())).when(repositorioJuego).findById(new Long(12));
		TsscGame juego = repositorioJuego.findById(new Long(12)).get();
		juego.setNGroups(0);
		juego.setNSprints(0);
		this.servicioJuego.editGame(m, juego);
		verify(repositorioJuego, times(0)).save(juego);
	}
	
	@Test
	public void cuandoEditoUnJuegoQueNoExistePreviamenteSinGuardarYLanzaExcepcion() throws ElemNotExiPreviousException {
		Long m = new Long(12);
		TsscGame juego = new TsscGame();
		doReturn(Optional.empty()).when(repositorioJuego).findById(m);

		verify(repositorioJuego, times(0)).save(juego);
	}
	
	@Test
	public void cuandoEditoUnJuegoConIdNuloSinGuardarYLanzaExcepcion() {
		doThrow(new IllegalArgumentException()).when(repositorioJuego).findById(null);
		TsscGame juego = new TsscGame();
		assertThrows(IllegalArgumentException.class, () -> {
			this.servicioJuego.editGame(null, juego);
		});
		verify(repositorioJuego, times(0)).save(juego);
	}
	
	@Test
	public void cuandoEditoUnJuegoConExistenciaDeTemaGuardado() throws ElemNotExiPreviousException {
		Long m = new Long(12);
		TsscGame juego = new TsscGame();
		TsscTopic topic = new TsscTopic();
		topic.setId(m);
		doReturn(Optional.of(tema)).when(repositorioJuego).findById(m);
		this.servicioJuego.editGame(m, juego);
		verify(repositorioJuego, times(1)).save(juego);
	}
	
	@Test
	public void cuandoEditoUnJuegoConNingunaExistenciaDeTemaSinGuardar() {
		Long m = new Long(12);
		TsscGame juego = new TsscGame();
		doReturn(Optional.empty()).when(repositorioTema).findById(m);

		verify(repositorioJuego, times(0)).save(juego);
	}
	
	@Test
	public void cuandoGuardoUnJuegoSinAsociarloConSpringYGruposmayoresQueceroDanTrue() {
		TsscGame juego = new TsscGame();
		assertTrue(this.servicioJuego.addGame(juego));	
		verify(repositorioJuego, times(1)).save(juego);
	}
	
	@Test
	public void cuandoGuardoUnJuegoSinAsociacionConSpringYGruposMenoresQueCeroDanFalse() {
		TsscGame juego = new TsscGame();
		juego.setNSprints(0);
		juego.setNGroups(0);
		assertFalse(this.servicioJuego.addGame(juego));	
	}
	
	@Test
	public void cuandoSeGuardeElJuegoConElTemaExistenteSeDevolveraVerdadero() {
		TsscGame juego = new TsscGame();
		juego.setTsscTopic(new TsscTopic());
		doReturn(true).when(repositorioTema).existsById(juego.getTsscTopic().getId());
		assertTrue(this.servicioJuego.addGame(juego));	
		verify(repositorioJuego, times(1)).save(juego);
	}
	
	@Test
	public void cuandoSeGuardeElJuegoSinExistirTemaDevolveraFalse() {
		TsscGame juego = new TsscGame();
		juego.setTsscTopic(new TsscTopic());
		doReturn(false).when(repositorioJuego).existsById(juego.getTsscTopic().getId());
		assertFalse(this.servicioJuego.addGame(juego));	
	}
	
	@Test
	public void cuandoSeGuardeElJuegoConExistenciaDeTemaDevolveraVerdadero() {
		TsscGame juego = new TsscGame();
		long m = new Long(12);
		tema.setId(m);
		doReturn(Optional.of(tema)).when(repositorioTema).findById(m);
		doReturn(new ArrayList<TsscTimecontrol>()).when(tema).getTsscTimesControls();
		doReturn(new ArrayList<TsscStory>()).when(tema).getTsscStories();
		juego.setTsscTopic(tema);
		doReturn(true).when(repositorioTema).existsById(m);
		juego.setTsscTopic(repositorioTema.findById(m).get());
		juego.setTsscStories(juego.getTsscTopic().getTsscStories());
		juego.setTsscTimecontrol(juego.getTsscTopic().getTsscTimesControls());
		assertTrue(this.servicioJuego.addGame(juego));	
		assertTrue(juego.getTsscStories().equals(tema.getTsscStories()) &&
				juego.getTsscTimecontrols().equals(tema.getTsscTimesControls()));
		verify(repositorioJuego, times(1)).save(juego);
	}
	
	@Test
	public void cuandoSeGuardeElJuegoConExitenciaDelTemaDevolveraVerdadero() {
		TsscGame juego = new TsscGame();
		long m = new Long(12);
		tema.setId(m);
		doReturn(Optional.of(tema)).when(repositorioTema).findById(m);
		doReturn(null).when(tema).getTsscTimesControls();
		doReturn(null).when(tema).getTsscStories();
		juego.setTsscTopic(tema);
		doReturn(true).when(repositorioTema).existsById(m);
		juego.setTsscTopic(repositorioTema.findById(m).get());
		juego.setTsscStories(juego.getTsscTopic().getTsscStories());
		juego.setTsscTimecontrol(juego.getTsscTopic().getTsscTimesControls());
		assertTrue(this.servicioJuego.addGame(juego));	
		assertTrue(juego.getTsscStories() == null &&
				juego.getTsscTimecontrols() == null);
		verify(repositorioJuego, times(1)).save(juego);
	}
	
}
