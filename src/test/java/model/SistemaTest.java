package model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import model.exceptions.MasDeCincoSubastasEnProgresoException;


public class SistemaTest {
	Usuario usuario;
	Sistema sistema;
	Subasta subasta0;
	Subasta subasta1;
	Subasta subasta2;
	Subasta subasta3;
	Subasta subasta4;
	Subasta subasta5;
	
	@Before
	public void setUp() {
		sistema = new Sistema();
		subasta0 = new Subasta();
		subasta1 = new Subasta();
		subasta2 = new Subasta();
		subasta3 = new Subasta();
		subasta4 = new Subasta();
		subasta5 = new Subasta();
		usuario = new Usuario();
	}

	/* 
	@Test
	public void verificandoCantidadDeSubastasEsCeroTest() {
		assertEquals(0, sistema.getSubastas().size());
	}
	
	@Test
	public void verificandoAgregarSubastasTest() {
		sistema.agregar(subasta0);
		assertEquals(1, sistema.getSubastas().size());
		sistema.agregar(subasta1);
		assertEquals(2, sistema.getSubastas().size());
		sistema.agregar(subasta2);
		assertEquals(3, sistema.getSubastas().size());
	}
	*/
	
	@Test
	public void crearUnaSubastaTest() {
		sistema.crear(subasta0, usuario); 
		assertEquals(sistema.getSubastas().size(), 1);
		assertTrue(subasta0.esNueva());
		assertEquals(subasta0.getPropietario(), usuario);
	}
	

	@Test(expected=MasDeCincoSubastasEnProgresoException.class)
	public void crearUnaSubastaCuandoTengoMasDeCincoEnProgresoTest() {
		sistema.crear(subasta0, usuario);
		sistema.crear(subasta1, usuario);
		sistema.crear(subasta2, usuario);
		sistema.crear(subasta3, usuario);
		sistema.crear(subasta4, usuario);
		subasta0.setEstado(new EnProgreso());
		subasta1.setEstado(new EnProgreso());
		subasta2.setEstado(new EnProgreso());
		subasta3.setEstado(new EnProgreso());
		subasta4.setEstado(new EnProgreso());
		// Las cinco subastas en progreso
		sistema.crear(subasta5, usuario);
	}
	
	/* 
	@Test
	public void usuarioRealizaUnaOfertaParaUnaSubastaTest() {
		
	}
	
	@Test
	// Todas las variaciones con las que se puede inciar sesion (incluso las que dan error)
	public void usuarioIniciarSesionTest() {
		
	}
	
	@Test
	// Todas las variaciones para registrarse, incluso las que dan error
	public void usuarioRegistrarseTest() {
		
	}
	
	@Test
	// Ultimas 15 subastas
	public void buscarUltimasSubastasIniciadasTest() {
		
	}
	
	@Test
	// Ultimas 15 subastas
	public void buscarUltimasSubastasProximasAFinalizarTest() {
		
	}
	
	@Test
	// Ultimas 15 subastas
	public void buscarUltimasSubastasConMasPostoresTest() {
		
	}
	
	*/
}
