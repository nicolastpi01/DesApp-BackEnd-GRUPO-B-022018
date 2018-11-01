package model;

import java.util.ArrayList;
import java.util.List;

public class PopularCriteria {
	private final int minAuctionsToShow = 5;
	
	
	// Using comparator for return result in orden (orden de populares)
	// Una subasta es pop. si su cant. de postores es mayor o igual a la media
	// Agregar metadata de modo que haya varias subastas pop.
	public List<Auction> apply(List<Auction> auctions) {
		List<Auction> result = new ArrayList<Auction>();
		if (auctions.size() < minAuctionsToShow) {
			result = auctions;
		}
		else result = populars(auctions); 		
		
		return result;
	}
	
	private List<Auction> populars(List<Auction> auctions) {
		ArrayList<Auction> results = new ArrayList<Auction>();
		int average = this.average(auctions);
		for (int i=0; i < auctions.size(); i++) {
			if(auctions.get(i).isPopular(average)) results.add(auctions.get(i));
		}
		return results;
	}
	
	private int average(List<Auction> auctions) {
		int amountBidders = 0;
		int amountAuctions = auctions.size();
		for(int i=0; i < auctions.size(); i++) {
			amountBidders += auctions.get(i).biddersSize();
		}
		// redondeamos para arriba
		return (amountBidders / amountAuctions) + (amountBidders % amountAuctions);
	}
	

}
