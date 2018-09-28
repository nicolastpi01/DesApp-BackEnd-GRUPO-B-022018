package model;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import model.exceptions.YouCanNotHaveMoreThanFiveAuctionsInProgressException;


public class SystemTest {
	User nicolasUser;
	User vigoUser;
	User bidderUser0;
	User bidderUser1;
	User bidderUser2;
	User bidderUser3;
	System system;
	Auction auction0;
	Auction auction1;
	Auction auction2;
	Auction auction3;
	Auction auction4;
	Auction auction5;
	
	@Before
	public void setUp() {
		nicolasUser = new User("Nicolás", "García", new Email("nicolasgarcia@gmail.com"), new Pass("TonySoprano100"), new Date(12,03,1991));
		vigoUser = new User("Guido", "Pujadas", new Email("guidopujadas@gmail.com"), new Pass("SilvioDante11"), new Date(20,06,1992));
		system = new System();
		auction0 = new Auction();
		auction1 = new Auction();
		auction2 = new Auction();
		auction3 = new Auction();
		auction4 = new Auction();
		auction5 = new Auction();
		bidderUser0 = new User();
		bidderUser1 = new User();
		bidderUser2 = new User();
		bidderUser3 = new User();
	}
	
	@Test
	// Todas las variaciones con las que se puede inciar sesion (incluso las que dan error)
	public void sigInTest() {	
	}
	
	//////////////////////////////////  REGISTRO   ///////////////////////////////////////////////////////////
	
	@Test
	public void makeABidForAnAuctionTest() {}
	
	@Test
	public void createAnAuctionTest() {
		// Funciona porque no hace la verificacion de Usuario registrado y log.
		system.create(auction0, nicolasUser); 
		assertEquals(system.getAuctions().size(), 1);
		assertTrue(auction0.isNew());
		assertEquals(auction0.getOwner(), nicolasUser);
		system.create(auction1, vigoUser); 
		assertEquals(system.getAuctions().size(), 2);
		assertTrue(auction1.isNew());
		assertEquals(auction1.getOwner(), vigoUser);
	}
	

	@Test(expected=YouCanNotHaveMoreThanFiveAuctionsInProgressException.class)
	public void createAnAuctionWhenIHaveMoreThanFiveAuctionsInProgressTest() {
		// Funciona porque no hace la verificacion de Usuario registrado y log.
		system.create(auction0, nicolasUser);
		system.create(auction1, nicolasUser);
		system.create(auction2, nicolasUser);
		system.create(auction3, nicolasUser);
		system.create(auction4, nicolasUser);
		auction0.setState(new InProgress());
		auction1.setState(new InProgress());
		auction2.setState(new InProgress());
		auction3.setState(new InProgress());
		auction4.setState(new InProgress());
		// Las cinco subastas en progreso
		system.create(auction5, nicolasUser);
	}
	
	@Test
	public void deleteAnAuctionTest() {
		// Funciona porque no hace la verificacion de Usuario registrado y log.
		system.create(auction0, nicolasUser);
		system.create(auction1, vigoUser);
		system.delete(auction0, nicolasUser);
		assertEquals(1, system.getAuctions().size());
	}
	
	@Test
	public void editAnAuctionTest() {}
	
	
	/////////////////////////// BUSQUEDAS  //////////////////////////////////////////////////
	
	
	@Test
	public void searchAnAuctionForTitleTest() {
		system.sigIn(nicolasUser);
		system.create(auction0, nicolasUser);
		auction0.setTitle("Guantelete del Infinito");
		auction0.setState(new InProgress());
		List<Auction> auctionWithTitle = system.searchForTitle("Guantelete del Infinito");
		assertEquals(auctionWithTitle.size(), 1);
		Auction subastaGuantelete = auctionWithTitle.get(0);
		assertEquals(subastaGuantelete.getTitle(), "Guantelete del Infinito");
	}
	
	@Test
	public void searchAnAuctionForDescriptionTest() {
		system.sigIn(nicolasUser);
		system.create(auction0, nicolasUser);
		auction0.setState(new InProgress());
		auction0.setDescription("La subasta estaba un poco seca");
		List<Auction> auctionForDescription = system.searchForDescription("La subasta estaba un poco seca");
		assertEquals(auctionForDescription.size(), 1);
		Auction auctionWithDescription = auctionForDescription.get(0);
		assertEquals(auctionWithDescription.getDescription(), "La subasta estaba un poco seca");
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
