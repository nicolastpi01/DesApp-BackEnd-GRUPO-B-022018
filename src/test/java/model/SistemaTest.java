package model;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import model.exceptions.YouCanNotHaveMoreThanFiveAuctionsInProgressException;


public class SistemaTest {
	User usuarioNicolas;
	User usuarioVigo;
	User usuarioPostor0;
	User usuarioPostor1;
	User usuarioPostor2;
	User usuarioPostor3;
	System sistema;
	Auction subasta0;
	Auction subasta1;
	Auction subasta2;
	Auction subasta3;
	Auction subasta4;
	Auction subasta5;
	
	@Before
	public void setUp() {
		usuarioNicolas = new User("Nicolás", "García", new Email("nicolasgarcia@gmail.com"), new Pass("TonySoprano100"), new Date(12,03,1991));
		usuarioVigo = new User("Guido", "Pujadas", new Email("guidopujadas@gmail.com"), new Pass("SilvioDante11"), new Date(20,06,1992));
		sistema = new System();
		subasta0 = new Auction();
		subasta1 = new Auction();
		subasta2 = new Auction();
		subasta3 = new Auction();
		subasta4 = new Auction();
		subasta5 = new Auction();
		usuarioPostor0 = new User();
		usuarioPostor1 = new User();
		usuarioPostor2 = new User();
		usuarioPostor3 = new User();
	}
	
	@Test
	// Todas las variaciones con las que se puede inciar sesion (incluso las que dan error)
	public void iniciarSesionTest() {	
	}
	
	//////////////////////////////////  REGISTRO   ///////////////////////////////////////////////////////////
	
	@Test
	public void realizaUnaOfertaParaUnaSubastaTest() {}
	
	@Test
	public void crearUnaSubastaTest() {
		// Funciona porque no hace la verificacion de Usuario registrado y log.
		sistema.crear(subasta0, usuarioNicolas); 
		assertEquals(sistema.getSubastas().size(), 1);
		assertTrue(subasta0.esNueva());
		assertEquals(subasta0.getPropietario(), usuarioNicolas);
		sistema.crear(subasta1, usuarioVigo); 
		assertEquals(sistema.getSubastas().size(), 2);
		assertTrue(subasta1.esNueva());
		assertEquals(subasta1.getPropietario(), usuarioVigo);
	}
	

	@Test(expected=YouCanNotHaveMoreThanFiveAuctionsInProgressException.class)
	public void crearUnaSubastaCuandoTengoMasDeCincoEnProgresoTest() {
		// Funciona porque no hace la verificacion de Usuario registrado y log.
		sistema.crear(subasta0, usuarioNicolas);
		sistema.crear(subasta1, usuarioNicolas);
		sistema.crear(subasta2, usuarioNicolas);
		sistema.crear(subasta3, usuarioNicolas);
		sistema.crear(subasta4, usuarioNicolas);
		subasta0.setEstado(new InProgress());
		subasta1.setEstado(new InProgress());
		subasta2.setEstado(new InProgress());
		subasta3.setEstado(new InProgress());
		subasta4.setEstado(new InProgress());
		// Las cinco subastas en progreso
		sistema.crear(subasta5, usuarioNicolas);
	}
	
	@Test
	public void eliminarUnaSubastaTest() {
		// Funciona porque no hace la verificacion de Usuario registrado y log.
		sistema.crear(subasta0, usuarioNicolas);
		sistema.crear(subasta1, usuarioVigo);
		sistema.eliminar(subasta0, usuarioNicolas);
		assertEquals(1,sistema.getSubastas().size());
	}
	
	@Test
	public void editarUnaSubastaTest() {
		
	}
	
	
	/////////////////////////// BUSQUEDAS  //////////////////////////////////////////////////
	
	
	@Test
	public void buscarSubastaPorTituloTest() {
		sistema.registrarse(usuarioNicolas);
		sistema.crear(subasta0, usuarioNicolas);
		subasta0.setTitulo("Guantelete del Infinito");
		subasta0.setEstado(new InProgress());
		List<Auction> subastasConTitulo = sistema.buscarPorTitulo("Guantelete del Infinito");
		assertEquals(subastasConTitulo.size(), 1);
		Auction subastaGuantelete = subastasConTitulo.get(0);
		assertEquals(subastaGuantelete.getTitulo(), "Guantelete del Infinito");
	}
	
	@Test
	public void buscarSubastasPorDescripcionTest() {
		sistema.registrarse(usuarioNicolas);
		sistema.crear(subasta0, usuarioNicolas);
		subasta0.setEstado(new InProgress());
		subasta0.setDescripcion("La subasta estaba un poco seca");
		List<Auction> subastasPorDescripcion = sistema.buscarPorDescripcion("La subasta estaba un poco seca");
		assertEquals(subastasPorDescripcion.size(), 1);
		Auction subastaConDescripcion = subastasPorDescripcion.get(0);
		assertEquals(subastaConDescripcion.getDescripcion(), "La subasta estaba un poco seca");
	}
	
	/*
	@Test
	// Ultimas 15 subastas (Como max)
	// Esta bien, pero ahora se hizo muy complicado testearlo
	
	public void buscarSubastasRecientesTest() {
		sistema.registrarse(usuarioNicolas);
		sistema.crear(subasta0, usuarioNicolas);
		sistema.crear(subasta1, usuarioNicolas);
		subasta0.setEstado(new EnProgreso());
		subasta1.setEstado(new EnProgreso());
		subasta0.setFechaPublicacion(new Fecha(14,9,2018));
		subasta1.setFechaPublicacion(new Fecha(15,9,2018));
		ArrayList<Subasta> subastasRecientes = sistema.buscarRecientes();
		assertEquals(subastasRecientes.size(), 2);
	}
	
	@Test
	// Esta bien, pero ahora se hizo muy complicado testearlo
	// Ultimas 15 subastas (Como max)
	public void buscarSubastasProximasAFinalizarTest() {
		sistema.registrarse(usuarioNicolas);
		sistema.crear(subasta0, usuarioNicolas);
		sistema.crear(subasta1, usuarioNicolas);
		subasta0.setEstado(new EnProgreso());
		subasta1.setEstado(new EnProgreso());
		subasta0.setFechaFinalizacion(new Fecha(17,9,2018));
		subasta1.setFechaFinalizacion(new Fecha(16,9,2018));
		ArrayList<Subasta> proximasAFinalizar = sistema.buscarProximasAFinalizar();
		assertEquals(proximasAFinalizar.size(), 2);
	}
	
	*/
	
	///////////////////////////// BUSQUEDAS  ///////////////////////////////////////////////
	/*
	@Test
	 
	public void modificarUnaSubastaTest() {
		// Inicializar la subasta con ciertas caracteristicas
		sistema.crear(subasta0, usuarioNicolas);
		sistema.modificar(subasta0, usuarioNicolas); // El sist. verifica si efectivamente es posible modificarla 
		// Criterio de modificacion
		// Verificar la modificacion
	}
	
	@Test // Retorna una excepcion, ya que la subasta no puede ser modificada en ese momento [Probablemente si alguien ya pujo por ella]
	public void modificarUnaSubastaCuandoNoEsPosiblePorAlgunCriterioTest() {
		// Inicializar la subasta con cierto contexto que permita saltar la excepcion cuando se la quiera modificar
		sistema.crear(subasta0, usuarioNicolas);
		sistema.modificar(subasta0, usuarioNicolas); // La Excepcion
	}
	
	@Test
	public void eliminarUnaSubastaTest() {
		sistema.crear(subasta0, usuarioNicolas);
		sistema.eliminar(subasta0, usuarioNicolas); // El sist. verifica si efectivamente es posible eliminarla 
		// Verificar la modificacion (verificar en el Sist. que no esta mas esa subasta)
	}
	
	@Test // La Excepcion se dispara cuando quiero eliminar una subasta en un contexto donde es imposible hacerlo
	public void eliminarUnaSubastaCuandoNoEsPosiblePorAlgunCriterioTest() {
		// Inicializar la subasta con cierto contexto que permita saltar la excepcion cuando se la quiera eliminar
		sistema.crear(subasta0, usuarioNicolas);
		sistema.eliminar(subasta0, usuarioNicolas); // El sist. lanza la Excep. 
	}
	
	@Test
	

	*/
	
	
}
