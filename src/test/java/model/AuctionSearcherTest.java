package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.List;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

public class AuctionSearcherTest {
	private AuctionSearcher searcher;
	private List<Auction> auctions;
	private Auction auction0;
	private Auction auction1;
	private Auction auction2;
	private Auction auction3;
	private Auction auction4;
	private User user0;
	private User user1;
	private User user2;
		
	@Before
	public void init() {
		this.searcher = new AuctionSearcher();
		user0 = new User();
		user1 = new User();
		user2 = new User();
		auction0 = new Auction();
		auction1 = new Auction();
		auction2 = new Auction();
		auction3 = new Auction();
		auction4 = new Auction();
		auctions = new ArrayList<Auction>();
	}
	 
	@Test
	public void getPopularsWhenAverageIsTwoTest() {
		auction0.addBidder(user0);
		auction1.addBidder(user0);
		auction1.addBidder(user1);
		auction2.addBidder(user0);
		auction2.addBidder(user1);
		auction2.addBidder(user2);
		auction3.addBidder(user0);
		auction3.addBidder(user1);
		auction3.addBidder(user2);
		auction4.addBidder(user0);
		auctions.add(auction0);
		auctions.add(auction1);
		auctions.add(auction2);
		auctions.add(auction3);
		auctions.add(auction4);
		assertEquals(3, searcher.getPopulars(auctions).size());
	}
	
	@Test
	public void getPopularsWhenTheAmountOfAuctionsIsInferiorToPermitTest() {
		// No importa si son pop o no, como son 4 (minimo permitidas para mostrar 5) retorna esas 4
		// Para tener algo que mostrar siempre
		auctions.add(auction0);
		auctions.add(auction1);
		auctions.add(auction2);
		auctions.add(auction3);
		assertEquals(4, searcher.getPopulars(auctions).size());
	}
	
	@Test
	public void getPopularsWhenAverageIsThreeTest() {
		auction0.addBidder(user0);
		auction1.addBidder(user0);
		auction1.addBidder(user1);
		auction1.addBidder(user2);
		auction2.addBidder(user0);
		auction2.addBidder(user1);
		auction2.addBidder(user2);
		auction3.addBidder(user0);
		auction3.addBidder(user1);
		auction3.addBidder(user2);
		auction4.addBidder(user0);
		auctions.add(auction0);
		auctions.add(auction1);
		auctions.add(auction2);
		auctions.add(auction3);
		auctions.add(auction4);
		assertEquals(3, searcher.getPopulars(auctions).size());
		assertTrue(searcher.getPopulars(auctions).contains(auction1));
		assertTrue(searcher.getPopulars(auctions).contains(auction2));
		assertTrue(searcher.getPopulars(auctions).contains(auction3));	
	}
	
	@Test
	public void getRecentsWhenAuctionStartTodayTest() {
		auction0.setOpeningDate(new java.util.Date());
		auctions.add(auction0);
		assertEquals(1, searcher.getRecents(auctions).size());
		assertTrue(searcher.getRecents(auctions).contains(auction0));	
	}
	
	@Test
	public void getRecentsWhenAuctionStartThreeDaysAgoTest() {
		auction1.setOpeningDate(LocalDate.now().minusDays(3).toDate());
		auctions.add(auction1);
		assertEquals(1, searcher.getRecents(auctions).size());
		assertTrue(searcher.getRecents(auctions).contains(auction1));
	}
	
	@Test
	public void getRecentsWhenAuctionStartFourDaysAgoTest() {
		auction1.setOpeningDate(LocalDate.now().minusDays(4).toDate());
		auctions.add(auction1);
		assertEquals(0, searcher.getRecents(auctions).size());
		assertFalse(searcher.getRecents(auctions).contains(auction0));
	}
	
	@Test
	public void getRecentsWhenAuctionStartTomorrowTest() {
		auction0.setOpeningDate(LocalDate.now().plusDays(1).toDate());
		auctions.add(auction0);
		assertEquals(0, searcher.getRecents(auctions).size());
		assertFalse(searcher.getRecents(auctions).contains(auction0));
	}
	
	@Test
	public void getNextToFinishWhenAuctionEndingTodayTest() {
		// Una subasta que termina hoy, no es una subasta prox. a finalizar sino una subasta terminada
		auction0.setEndingDate(new java.util.Date());
		auctions.add(auction0);
		assertEquals(0, searcher.getNextToFinish(auctions).size());
	}
	
	@Test
	public void getNextToFinishWhenAuctionEndingTomorrowTest() {
		// Una subasta que termina hoy, no es una subasta prox. a finalizar sino una subasta terminada
		auction0.setEndingDate(LocalDate.now().plusDays(1).toDate());
		auctions.add(auction0);
		assertEquals(1, searcher.getNextToFinish(auctions).size());
		assertTrue(searcher.getNextToFinish(auctions).contains(auction0));
	}
	
	@Test
	public void getNextToFinishWhenAuctionEndingInThreeDaysTest() {
		auction0.setEndingDate(LocalDate.now().plusDays(3).toDate());
		auctions.add(auction0);
		assertEquals(1, searcher.getNextToFinish(auctions).size());
		assertTrue(searcher.getNextToFinish(auctions).contains(auction0));
	}
	
	@Test
	public void getNextToFinishWhenAuctionEndingInFourDaysTest() {
		auction0.setEndingDate(LocalDate.now().plusDays(4).toDate());
		auctions.add(auction0);
		assertEquals(0, searcher.getNextToFinish(auctions).size());
	}
	
	@Test
	public void getNextToFinishWhenAuctionEndingYesterdayTest() {
		auction0.setEndingDate(LocalDate.now().minusDays(1).toDate());
		auctions.add(auction0);
		assertEquals(0, searcher.getNextToFinish(auctions).size());
	}
	
	@Test
	public void getNextToFinishTest() {
		auction0.setEndingDate(LocalDate.now().plusDays(1).toDate());
		auction1.setEndingDate(LocalDate.now().plusDays(2).toDate());
		auction2.setEndingDate(LocalDate.now().plusDays(3).toDate());
		auction3.setEndingDate(LocalDate.now().plusDays(4).toDate());
		auctions.add(auction0);
		auctions.add(auction1);
		auctions.add(auction2);
		auctions.add(auction3);
		assertEquals(3, searcher.getNextToFinish(auctions).size());
		assertTrue(searcher.getNextToFinish(auctions).contains(auction0));
		assertTrue(searcher.getNextToFinish(auctions).contains(auction1));
		assertTrue(searcher.getNextToFinish(auctions).contains(auction2));
	}

}
  