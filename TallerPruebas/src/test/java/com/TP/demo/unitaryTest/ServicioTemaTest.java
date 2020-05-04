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

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.TP.demo.exceptions.ElemNotExiPreviousException;
import com.TP.demo.exceptions.TSGMC;
import com.TP.demo.model.TsscTopic;
import com.TP.demo.repositories.IRepositorioTema;
import com.TP.demo.services.IServicioTema;
import com.TP.demo.services.ServicioTema;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ServicioTemaTest {
	
	private IServicioTema servicioTema;
	private IRepositorioTema repositorioTema;
	
	@Before
	public void initicializarElServicioTema() {
		repositorioTema = mock(IRepositorioTema.class);
		servicioTema = spy(new ServicioTema(repositorioTema));
	}
	
	@Test
	public void cuandoAgregoTemasSinAsociarConSpringYGruposMasGRandeQueCeroYDevuelveTrue() {
		TsscTopic tema = new TsscTopic();
		tema.setDefaultSprints(1);
		tema.setDefaultGroups(1);
		assertTrue(this.servicioTema.addTopic(tema));	
		verify(repositorioTema, times(1)).save(tema);
	}
	
	@Test
	public void cuandoEditoTemasSinAsociarlosConSpringYGruposMayoresACeroGuardados() throws ElemNotExiPreviousException {
		Long prueba = new Long(12);
		doReturn(Optional.of(new TsscTopic())).when(repositorioTema).findById(new Long(12));
		TsscTopic tema = repositorioTema.findById(new Long(12)).get();
		tema.setDefaultGroups(new Long(20));
		tema.setDefaultSprints(new Long(30));
		this.servicioTema.editTopic(prueba, tema);
		verify(repositorioTema, times(1)).save(tema);
	}
	
	@Test
	public void cuandoEditoTemasSinAsociarlosConSpringYTengoGruposMenoresOIgualesAceroSinGuardar() throws ElemNotExiPreviousException {
		Long prueba = new Long(12);
		doReturn(Optional.of(new TsscTopic())).when(repositorioTema).findById(new Long(12));
		TsscTopic tema = repositorioTema.findById(new Long(12)).get();
		tema.setDefaultGroups(new Long(0));
		tema.setDefaultSprints(new Long(0));
		this.servicioTema.editTopic(prueba, tema);
		verify(repositorioTema, times(0)).save(tema);
	}
	
	@Test
	public void cuandoEditoTemasQueNoExistianPreviamenteSinGuardar() {
		Long prueba = new Long(12);
		TsscTopic tema = new TsscTopic();
		doReturn(Optional.empty()).when(repositorioTema).findById(prueba);
		assertThrows(ElemNotExiPreviousException.class, () -> {
			this.servicioTema.editTopic(prueba, tema);
		});
		verify(repositorioTema, times(0)).save(tema);
	}
	
	@Test
	public void cuandoEditoTemasSinIdNulo() {
		doThrow(new IllegalArgumentException()).when(repositorioTema).findById(null);
		TsscTopic tema = new TsscTopic();
		assertThrows(IllegalArgumentException.class, () -> {
			this.servicioTema.editTopic(null, tema);
		});
		verify(repositorioTema, times(0)).save(tema);
	}
	
	@Test
	public void cuandoAgregoTemassinAsociarlosConSpringYTengoGruposMenosresOIgualesQueCeroQueRetornanFalso() {
		TsscTopic tema = new TsscTopic();
		tema.setDefaultSprints(0);
		tema.setDefaultGroups(0);
		assertFalse(this.servicioTema.addTopic(tema));	
	}
			
	
}
