package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class MailTest {
	Email c1;
	
	@Test
	public void validMailTest() {
		c1 = new Email("pepitoGonzalez@gmail.com");
		assertTrue(c1.isValid());
	}
	
	@Test
	public void mailWithoutArrobaTest() {
		c1 = new Email("pepitoGonzalezgmail.com");
		assertFalse(c1.isValid());
	}
	
	@Test
	public void mailWithoutDotComTest() {
		c1 = new Email("pepitoGonzalez@gmail");
		assertFalse(c1.isValid());
	}
	
	@Test
	public void mailAnothoerDomTest() {
		c1 = new Email("pepitoGonzalez@ciudad.com");
		assertFalse(c1.isValid());
	}
}
