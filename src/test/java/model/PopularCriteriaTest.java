package model;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class PopularCriteriaTest {
	/* 
	Auction s1;
	Auction s2;
	Auction s3;
	Auction s4;
	Auction s5;
	Auction s6;
	
	User u1;
	User u2;
	User u3;
	User u4;
	User u5;
	User u6;
	
	PopularCriteria pc;
	
	
	public Auction createAuction(List<User> postores, Integer numeroSubasta) {
		Auction s = new Auction();
		s.setBidders(postores);
		s.setTitle("La Subasta numero " + numeroSubasta);
		return s;
	}
	
	@Before
	public void setUp() {
		
		u1 = new User();
		u2 = new User();
		u3 = new User();
		u4 = new User();
		u5 = new User();
		u6 = new User();
		
		List<User> postores1 = new ArrayList<User>();
		postores1.add(u1);
		postores1.add(u2);
		postores1.add(u3);
		s1 = createAuction(postores1,1);
		
		List<User> postores2 = new ArrayList<User>();
		postores2.add(u1);
		s2 = createAuction(postores2,2);
		
		List<User> postores3 = new ArrayList<User>();
		postores3.add(u1);
		postores3.add(u2);
		postores3.add(u3);
		postores3.add(u4);
		postores3.add(u5);
		postores3.add(u6);
		s3 = createAuction(postores3,3);
		
		List<User> postores4 = new ArrayList<User>();
		postores4.add(u1);
		postores4.add(u2);
		postores4.add(u3);
		postores4.add(u4);
		postores4.add(u5);
		
		s4 = createAuction(postores4,4);
		
		List<User> postores5 = new ArrayList<User>();
		postores5.add(u1);
		postores5.add(u2);
		postores5.add(u3);
		postores5.add(u4);
		postores5.add(u5);
		s5 = createAuction(postores5,5);
		
		List<User> postores6 = new ArrayList<User>();
		postores6.add(u1);
		postores6.add(u2);
		
		s6 = createAuction(postores6,6);
		
		pc = new PopularCriteria();
	}
	
	@Test
	public void ordenarSubastaPorCriterioPopularTest() {
		List<Auction> subastas = new ArrayList<Auction>();
		subastas.add(s1);
		subastas.add(s2);
		subastas.add(s3);
		subastas.add(s4);
		subastas.add(s5);
		subastas.add(s6);
		List<Auction> subFiltradas = pc.search(subastas);
		assertEquals("La Subasta numero 3",subFiltradas.get(0).getTitle());
		assertEquals("La Subasta numero 6",subFiltradas.get(4).getTitle());
		assertEquals(5,subFiltradas.size());
	}
	
	
	@Test
	public void promedioPostoresPorSubastasTest() {
		List<Auction> subastas = new ArrayList<Auction>();
		subastas.add(s1);
		subastas.add(s2);
		subastas.add(s3);
		double promedioPostores = pc.averageBiddersPerAuction(subastas);
		assertEquals(3.3333333333333335,promedioPostores,0);
	}
	
	*/
}
