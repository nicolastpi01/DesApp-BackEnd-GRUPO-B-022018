package model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class RegistroTest {
	
	Usuario u1;
	Usuario u2;
	Usuario u3;
	
	Registro r;
	
	@Before
	public void setUp() {
		u1 = new Usuario("Pepito", "Gonzalez", new Correo("pepito@hotmail.com"), new Pass("pass"), new Fecha(2010,03,03));
		u2 = new Usuario("sultanito", "Gonzalez", new Correo("sultanito@hotmail.com"), new Pass("pass"), new Fecha(2010,03,03));
		u3 = new Usuario("menganito", "Gonzalez", new Correo("menganito@hotmail.com"), new Pass("pass"), new Fecha(2010,03,03));
		List<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios.add(u3);
		r = new Registro(usuarios);
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
