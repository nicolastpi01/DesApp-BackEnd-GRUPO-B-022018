package model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PassTest {
	
	Pass pass;
	
	@Test
	public void passIsValid() {
		pass = new Pass("rerthg5Tddd");
		assertTrue(pass.isValid());
	}
	
	@Test
	public void passNotValidLacksNumber() {
		pass = new Pass("rerthgTddd");
		assertFalse(pass.isValid());
	}
	
	@Test
	public void passNotValidLacksCapitalLetter() {
		pass = new Pass("rerthg111ddd");
		assertFalse(pass.isValid());
	}
	
	@Test
	public void passNotValidLessThan3Chars() {
		pass = new Pass("res");
		assertFalse(pass.isValid());
	}
	
	@Test
	public void passNotValidMoreThan20Chars() {
		pass = new Pass("poejfkdoeifjeoqpaoA1ium");
		assertFalse(pass.isValid());
	}
}
