package model;


public class HomeTest {
	Home home;
	Subasta subastaPopular;
	Subasta subastaReciente;
	Subasta subastaPorTerminar;
	Subasta subastaConTitulo;
	Subasta subastaConDescripcion;
	/* 
	@Before
	public void setUp() {
		home = new Home();
		subastaPopular = new Subasta(); // para que esta subasta sea popular tienen que pujar cierta cantidad de usuario por ella (media complicada de testear)
		subastaReciente = new Subasta();
		subastaPorTerminar = new Subasta();
		subastaConTitulo = new Subasta();
		subastaConDescripcion = new Subasta();
		// Hacer que subastaPopular sea popular de alguna manera (tiene que ser pop segun la puja por la misma)
	}
	
	
	@Test
	public void subastasPopularesTest() {
		home.agregar(subastaPopular); // Se agregar subatas al home pero en realidad las subastas salen de otro lado
		// lo unico que hace home es el filtro
		assertEquals(home.subastasPopulares().size(), 1);
		// Agrego otra subasta pop y verifico que hay una subasta mas...y así...
	}
	
	@Test
	public void subastasRecientesTest() {
		home.agregar(subastaReciente);
		assertEquals(home.subastasRecientes().size(), 1);
		// Agrego otra subasta reciente y verifico que hay una subasta mas...
	}
	
	@Test
	public void subastasPorTerminarTest() {
		home.agregar(subastaPorTerminar);
		assertEquals(home.subastasPorTerminar().size(), 1);
		// Agrego otra subasta por terminar y verifico que hay una subasta mas...
	}
	
	@Test
	public void busquedaPorTituloTest() {
		subastaConTitulo.setTitulo("Guantelete del Infinito");
		home.agregar(subastaConTitulo);
		assertEquals(home.buscarPorTitulo("Guantelete del Infinito").size(), 1);
	}
	
	@Test
	public void busquedaPorDescripcionTest() {
		//subastaConDescripcion.setDescripcion("No lo sé Rick, parece falso");
		//home.agregar(subastaConDescripcion);
		//assertEquals(home.buscarPorDescripcion("No lo sé Rick, parece falso").size(), 1);
	}
	*/
}
