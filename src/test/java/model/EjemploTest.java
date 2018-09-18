package model;

public class EjemploTest {
	
	Usuario u1;
	Usuario u2;
	Subasta s1;
	
	/*
	private Date generarFecha(Integer dia, Integer mes, Integer anio) {
		Calendar cal = Calendar.getInstance();
	    cal.set(anio, mes, dia);
	    return cal.getTime();
	}
	
	@Before
	public void setUp() {
		
		Calendar cal = Calendar.getInstance();
	    cal.set(1985, 1, 8);
	    
		//u1 = new Usuario("pepito", "Gomez", "pepito@gomez.com", "123", new LocalDate(1985,01,01));
		//u2 = new Usuario("sultano", "Gomez", "sultano@gomez.com", "123", new LocalDate(1985,06,01));
		
		//s1 = new Subasta("reloj", "suizo", "av de Mayo 111, CABA", "URL", 1000, new LocalDate(2018,9,9),
		//				new LocalDate(1985,01,01),new LocalTime(18, 30, 0));
	}
	
	/*

	@Test
	public void testOfertarSubastaCorrectamente() {
		u2.ofertarEnSubasta(100,s1);
		assertEquals(1100,s1.precioActual);
	}
	
	@Test(expected = SubastaMismoUsuarioException.class)
	public void testOfertarSubastaMismoUsuarioException() {
		u1.ofertarEnSubasta(100,s1);
	}
	
	*/
	//Las ofertas se hacen sobre el 5% del valor base
	
	// Si un usuario realiza una oferta dentro de los últimos 5' anteriores a terminar la subasta,
	//el sistema debe extender el fin de la subasta por 5' más
	
	//De todos modos una subasta nunca podrá superar 48hs más de la fecha de finalización inicial
	
}
