package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class CorreoTest {
	Email c1;
	
	@Test
	public void correoCorrectoTest() {
		c1 = new Email("pepitoGonzalez@gmail.com");
		assertTrue(c1.isValid());
	}
	
	@Test
	public void correoSinArrobaTest() {
		c1 = new Email("pepitoGonzalezgmail.com");
		assertFalse(c1.isValid());
	}
	
	@Test
	public void correoSinPuntoComTest() {
		c1 = new Email("pepitoGonzalez@gmail");
		assertFalse(c1.isValid());
	}
	
	@Test
	public void correoOtroDominioTest() {
		c1 = new Email("pepitoGonzalez@ciudad.com");
		assertFalse(c1.isValid());
	}
}
