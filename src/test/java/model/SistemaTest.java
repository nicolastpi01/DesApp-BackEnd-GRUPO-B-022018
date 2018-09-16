package model;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import model.exceptions.MasDeCincoSubastasEnProgresoException;


public class SistemaTest {
	Usuario usuarioNicolas;
	Usuario usuarioVigo;
	Sistema sistema;
	Subasta subasta0;
	Subasta subasta1;
	Subasta subasta2;
	Subasta subasta3;
	Subasta subasta4;
	Subasta subasta5;
	
	@Before
	public void setUp() {
		usuarioNicolas = new Usuario("Nicolás", "García", new Correo("nicolasgarcia@gmail.com"), new Pass("TonySoprano100"), new Fecha(12,03,1991));
		usuarioVigo = new Usuario("Guido", "Pujadas", new Correo("guidopujadas@gmail.com"), new Pass("SilvioDante11"), new Fecha(20,06,1992));
		sistema = new Sistema();
		subasta0 = new Subasta();
		subasta1 = new Subasta();
		subasta2 = new Subasta();
		subasta3 = new Subasta();
		subasta4 = new Subasta();
		subasta5 = new Subasta();
		
	}
	
	@Test
	// Todas las variaciones para registrarse, con los errores (nombre demasiado largo, formato Fecha incorrecto, etc)
	public void usuarioRegistrarseTest() {
		sistema.registrarse(usuarioNicolas);
		assertEquals(sistema.getUsuarios().size(), 1);
		sistema.registrarse(usuarioVigo);
		assertEquals(sistema.getUsuarios().size(), 2);
	}
	
	
	
	@Test
	// Todas las variaciones con las que se puede inciar sesion (incluso las que dan error)
	public void iniciarSesionTest() {
		
	}
	
	@Test
	public void usuarioRealizaUnaOfertaParaUnaSubastaTest() {}
	
	@Test
	public void crearUnaSubastaTest() {
		sistema.crear(subasta0, usuarioNicolas); 
		assertEquals(sistema.getSubastas().size(), 1);
		assertTrue(subasta0.esNueva());
		assertEquals(subasta0.getPropietario(), usuarioNicolas);
	}
	

	@Test(expected=MasDeCincoSubastasEnProgresoException.class)
	public void crearUnaSubastaCuandoTengoMasDeCincoEnProgresoTest() {
		sistema.crear(subasta0, usuarioNicolas);
		sistema.crear(subasta1, usuarioNicolas);
		sistema.crear(subasta2, usuarioNicolas);
		sistema.crear(subasta3, usuarioNicolas);
		sistema.crear(subasta4, usuarioNicolas);
		subasta0.setEstado(new EnProgreso());
		subasta1.setEstado(new EnProgreso());
		subasta2.setEstado(new EnProgreso());
		subasta3.setEstado(new EnProgreso());
		subasta4.setEstado(new EnProgreso());
		// Las cinco subastas en progreso
		sistema.crear(subasta5, usuarioNicolas);
	}
	
	
	
	/////////////////////////// BUSQUEDAS  //////////////////////////////////////////////////
	
	
	@Test
	public void buscarSubastaPorTituloTest() {
		sistema.registrarse(usuarioNicolas);
		sistema.crear(subasta0, usuarioNicolas);
		subasta0.setTitulo("Guantelete del Infinito");
		ArrayList<Subasta> subastasConTitulo = sistema.buscarPorTitulo("Guantelete del Infinito");
		assertEquals(subastasConTitulo.size(), 1);
		Subasta subastaGuantelete = subastasConTitulo.get(0);
		assertEquals(subastaGuantelete.getTitulo(), "Guantelete del Infinito");
	}
	
	@Test
	public void buscarSubastasPorDescripcionTest() {
		sistema.registrarse(usuarioNicolas);
		sistema.crear(subasta0, usuarioNicolas);
		subasta0.setDescripcion("La subasta estaba un poco seca");
		ArrayList<Subasta> subastasPorDescripcion = sistema.buscarPorDescripcion("La subasta estaba un poco seca");
		assertEquals(subastasPorDescripcion.size(), 1);
		Subasta subastaConDescripcion = subastasPorDescripcion.get(0);
		assertEquals(subastaConDescripcion.getDescripcion(), "La subasta estaba un poco seca");
	}
	
	@Test
	// Ultimas 15 subastas
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
	// Ultimas 15 subastas
	public void buscarSubastasProximasAFinalizarTest() {
		sistema.registrarse(usuarioNicolas);
		sistema.crear(subasta0, usuarioNicolas);
		sistema.crear(subasta1, usuarioNicolas);
		subasta0.setEstado(new EnProgreso());
		subasta1.setEstado(new EnProgreso());
		subasta0.setFechaFinalizacion(new Fecha(17,9,2018));
		subasta1.setFechaFinalizacion(new Fecha(18,9,2018));
		ArrayList<Subasta> proximasAFinalizar = sistema.buscarProximasAFinalizar();
		assertEquals(proximasAFinalizar.size(), 2);
	}
	
	//@Test
	// Ultimas 15 subastas
	// NO FUNCIONA --> (es medio complejo)
	//public void buscarSubastasPopularesTest() {
		// crear las subastas
		// Agregar tantos postores a estas subastas de modo que solo dos sean populares...
	//	ArrayList<Subasta> populares = sistema.buscarPopulares();
	//	assertEquals(populares.size(), 2);
	//}
	
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