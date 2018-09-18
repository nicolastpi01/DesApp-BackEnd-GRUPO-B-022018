package model;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class CriterioPopularTest {
	Subasta s1;
	Subasta s2;
	Subasta s3;
	Subasta s4;
	Subasta s5;
	Subasta s6;
	
	Usuario u1;
	Usuario u2;
	Usuario u3;
	Usuario u4;
	Usuario u5;
	Usuario u6;
	
	CriterioPopular cp;
	
	public Subasta crearSubasta(List<Usuario> postores, Integer numeroSubasta) {
		Subasta s = new Subasta();
		s.setPostores(postores);
		s.setTitulo("La Subasta numero " + numeroSubasta);
		return s;
	}
	
	@Before
	public void setUp() {
		
		u1 = new Usuario();
		u2 = new Usuario();
		u3 = new Usuario();
		u4 = new Usuario();
		u5 = new Usuario();
		u6 = new Usuario();
		
		List<Usuario> postores1 = new ArrayList<Usuario>();
		postores1.add(u1);
		postores1.add(u2);
		postores1.add(u3);
		s1 = crearSubasta(postores1,1);
		
		List<Usuario> postores2 = new ArrayList<Usuario>();
		postores1.add(u1);
		s2 = crearSubasta(postores2,2);
		
		List<Usuario> postores3 = new ArrayList<Usuario>();
		postores3.add(u1);
		postores3.add(u2);
		postores3.add(u3);
		postores3.add(u4);
		postores3.add(u5);
		postores3.add(u6);
		s3 = crearSubasta(postores3,3);
		
		List<Usuario> postores4 = new ArrayList<Usuario>();
		postores4.add(u1);
		postores4.add(u2);
		postores4.add(u3);
		postores4.add(u4);
		postores4.add(u5);
		
		s4 = crearSubasta(postores4,4);
		
		List<Usuario> postores5 = new ArrayList<Usuario>();
		postores5.add(u1);
		postores5.add(u2);
		postores5.add(u3);
		postores5.add(u4);
		postores5.add(u5);
		s5 = crearSubasta(postores5,5);
		
		List<Usuario> postores6 = new ArrayList<Usuario>();
		postores6.add(u1);
		postores6.add(u2);
		
		s6 = crearSubasta(postores6,6);
		
		cp = new CriterioPopular();
	}
	
	@Test
	public void ordenarSubastaPorCriterioPopularTest() {
		List<Subasta> subastas = new ArrayList<Subasta>();
		subastas.add(s1);
		subastas.add(s2);
		subastas.add(s3);
		subastas.add(s4);
		subastas.add(s5);
		subastas.add(s6);
		List<Subasta> subFiltradas = cp.buscar(subastas);
		assertEquals("La Subasta numero 3",subFiltradas.get(0).getTitulo());
		assertEquals("La Subasta numero 6",subFiltradas.get(4).getTitulo());
		assertEquals(5,subFiltradas.size());
	}
}
