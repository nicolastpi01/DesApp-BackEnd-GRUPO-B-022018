package model;

import static org.junit.Assert.*;
import org.junit.Test;

public class CorreoTest {
	Correo c1;
	
	@Test
	public void correoCorrectoTest() {
		c1 = new Correo("pepitoGonzalez@gmail.com");
		assertTrue(c1.esValido());
	}
	
	@Test
	public void correoSinArrobaTest() {
		c1 = new Correo("pepitoGonzalezgmail.com");
		assertFalse(c1.esValido());
	}
	
	@Test
	public void correoSinPuntoComTest() {
		c1 = new Correo("pepitoGonzalez@gmail");
		assertFalse(c1.esValido());
	}
	
	@Test
	public void correoOtroDominioTest() {
		c1 = new Correo("pepitoGonzalez@ciudad.com");
		assertFalse(c1.esValido());
	}
}
