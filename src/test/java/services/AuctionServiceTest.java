package services;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import model.Auction;
import service.AuctionRepository;
import service.AuctionService;

public class AuctionServiceTest {

	private AuctionService service;
	private Auction auction0;
	private AuctionRepository repository;
	
	@Before
	public void init() {
		service = new AuctionService(repository);
		auction0 = new Auction();
	}
	
	@Test
	public void deleteAuctionTest() {
		service.delete((long) 1);
		assertEquals(service.getAll().size(), 0);
	}
}
