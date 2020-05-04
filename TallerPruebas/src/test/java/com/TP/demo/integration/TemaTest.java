package com.TP.demo.integration;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.TP.demo.TPApplication;
import com.TP.demo.exceptions.ElemIdAlrExistException;
import com.TP.demo.exceptions.ElemNotExiPreviousException;
import com.TP.demo.model.TsscGame;
import com.TP.demo.model.TsscTopic;
import com.TP.demo.services.IServicioJuego;
import com.TP.demo.services.IServicioTema;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= {TPApplication.class})
public class TemaTest {

	@Autowired
	private IServicioTema temaServicio;
	private TsscTopic temaPorDefecto;
	
	@Before
	public void anadirElJuegoPorDefecto() {
		temaPorDefecto = new TsscTopic();
		temaPorDefecto.setName("Genero de pelea");
		temaPorDefecto.setDefaultGroups(55);
		temaPorDefecto.setDefaultSprints(36);
		temaServicio.addTopic(temaPorDefecto);
	}
	
	@Test
	public void anadirElTemaConSpringYLosGruposSuperioresACero() {
		TsscTopic t1 = new TsscTopic();
		t1.setName("Genero de la lloracion");
		t1.setDefaultGroups(50);
		t1.setDefaultSprints(70);
		temaServicio.addTopic(t1);
		assertAll(() -> {
			assertTrue(temaServicio.exists(temaPorDefecto.getId()));
		}, () -> {
			assertTrue(temaServicio.exists(t1.getId()));
		});
	
	}
	
	@Test
	public void anadirTemaConSpringYLosGruposMenoresACeroNoSeGuardan() {
		TsscTopic t1 = new TsscTopic();
		t1.setName("Genero de la lloracion");
		t1.setDefaultGroups(0);
		t1.setDefaultSprints(0);
		temaServicio.addTopic(t1);
		assertAll(() -> {
			assertTrue(temaServicio.exists(temaPorDefecto.getId()));
		}, () -> {
			assertFalse(temaServicio.exists(t1.getId()));
		});
	}
	
	@Test
	public void editarTemaGuardandoCambios() throws ElemNotExiPreviousException {
		temaPorDefecto.setName("Genero de romanticismo");
		temaPorDefecto.setDefaultGroups(66);
		temaServicio.editTopic(temaPorDefecto.getId(), temaPorDefecto);
		TsscTopic t1 = temaServicio.getTopic(temaPorDefecto.getId());
		assertTrue(t1.getName().equals("Genero de romanticismo") && t1.getDefaultGroups() == 66);		
	}
	
	@Test
	public void editarTemaSinGuardarLosCambiosCuandoSpringOLosGruposSonMenoresACero() throws ElemNotExiPreviousException {
		temaPorDefecto.setName("Genero de romanticismo");
		temaPorDefecto.setDefaultGroups(0);
		temaServicio.editTopic(temaPorDefecto.getId(), temaPorDefecto);
		TsscTopic t1 = temaServicio.getTopic(temaPorDefecto.getId());
		assertAll(() -> {
			assertTrue(t1.getName().equals("Genero de pelea"));
		}, () -> {
			assertFalse(t1.getName().equals("Genero de romanticismo"));
		});
	}
	
	
	
}
