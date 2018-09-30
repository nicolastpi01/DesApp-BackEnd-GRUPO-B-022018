package model;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

public class FinishCriteriaTest {
	Auction a1;
	Auction a2;
	Auction a3;
	Auction a4;
	Auction a5;
	Auction a6;
	
	FinishCriteria fc;
	
	public Auction createAuction() {
		Auction s = new Auction();
		
		return s;
	}
	
	@Before
	public void setUp() {
		
		a2 = createAuction();
		a2.setOpeningDate(new Date(01,10,2018));
		a2.setEndingDate(new Date(04,10,2018));
		
		a1 = createAuction();
		a1.setOpeningDate(new Date(11,10,2018));
		a1.setEndingDate(new Date(14,10,2018));
		
		a3 = createAuction();
		a3.setOpeningDate(new Date(21,10,2018));
		a3.setEndingDate(new Date(30,10,2018));
		
		fc = FinishCriteria.getInstance();
		
	}
	
	@Test
	public void getDescriptionFilteredAuctionsTest() {
		List<Auction> auctions = new ArrayList<Auction>();
		auctions.add(a2);
		auctions.add(a1);
		auctions.add(a3);
		
		
		List<Auction> subFiltradas = fc.search(auctions);
		assertEquals(1,subFiltradas.size());
	}
}
