package model;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class DescriptionCriteriaTest {
	/* 
	Auction a1;
	Auction a2;
	Auction a3;
	Auction a4;
	Auction a5;
	Auction a6;
	
	
	User u1;
	User u2;
	User u3;
	User u4;
	User u5;
	User u6;
	
	DescriptionCriteria dc;
	
	public Auction createAuction(List<User> postores, String descripcion) {
		Auction s = new Auction();
		s.setBidders(postores);
		s.setTitle(descripcion);
		s.setDescription(descripcion);
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
		
		
		List<User> bidders1 = new ArrayList<User>();
		bidders1.add(u1);
		bidders1.add(u2);
		
		List<User> bidders2 = new ArrayList<User>();
		bidders1.add(u1);
		bidders1.add(u2);
		
		List<User> bidders3 = new ArrayList<User>();
		bidders1.add(u1);
		bidders1.add(u2);
		
		List<User> bidders4 = new ArrayList<User>();
		bidders1.add(u1);
		bidders1.add(u2);
		
		List<User> bidders5 = new ArrayList<User>();
		bidders1.add(u1);
		bidders1.add(u2);
		
		List<User> bidders6 = new ArrayList<User>();
		bidders1.add(u1);
		bidders1.add(u2);
		
		a1 = createAuction(bidders1 ,"a red glass");
		
		a2 = createAuction(bidders2 ,"Jamaica's flag");
		
		a3 = createAuction(bidders3 ,"a hard disk");
		
		a4 = createAuction(bidders4 , "superman toys");
		
		a5 = createAuction(bidders5 ,"football video games");
		
		a6 = createAuction(bidders6 ,"electric guitar");
		
		dc = DescriptionCriteria.getInstance("fender electric guitar");
		
	}
	
	@Test
	public void getDescriptionFilteredAuctionsTest() {
		List<Auction> auctions = new ArrayList<Auction>();
		auctions.add(a1);
		auctions.add(a2);
		auctions.add(a3);
		auctions.add(a4);
		auctions.add(a5);
		auctions.add(a6);
		
		List<Auction> subFiltradas = dc.search(auctions);
		assertEquals(1,subFiltradas.size());
	}
	
	*/
}
