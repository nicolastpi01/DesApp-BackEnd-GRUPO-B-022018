package model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class RegistroTest {
	
	User u1;
	User u2;
	User u3;
	
	Registry r;
	
	@Before
	public void setUp() {
		u1 = new User("Pepito", "Gonzalez", new Email("pepito@hotmail.com"), new Pass("pass"), new Date(2010,03,03));
		u2 = new User("sultanito", "Gonzalez", new Email("sultanito@hotmail.com"), new Pass("pass"), new Date(2010,03,03));
		u3 = new User("menganito", "Gonzalez", new Email("menganito@hotmail.com"), new Pass("pass"), new Date(2010,03,03));
		List<User> usuarios = new ArrayList<User>();
		usuarios.add(u3);
		r = new Registry(usuarios);
	}
	
	
	@Test
	public void UsuarioNoExisteTest() {
		assertFalse(r.existeCorreoAsociado(u1));
	}
	
	@Test
	public void UsuarioYaExisteTest() {
		assertTrue(r.existeCorreoAsociado(u3));
	}
	
	
}
