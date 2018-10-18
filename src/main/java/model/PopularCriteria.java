package model;

import java.util.ArrayList;
import java.util.List;

public class PopularCriteria {
	private final int minimumOfAuctionsAllowedToShow = 5;
	
	
	/* 
	public List<Auction> apply(List<Auction> auctions) {
		List<Auction> pop = new ArrayList<Auction>();
		if (auctions.size() <= minimumOfAuctionsAllowedToShow) pop = auctions;
		else {
				int media = average(auctions);
				for (int i=0; i < auctions.size(); i++) {
					if (auctions.get(i).isPopular(media)) pop.add(auctions.get(i));
			}		
		}
		return pop;
	}
	
	private int average(List<Auction> auctions) {
		int amountBidders = 0;
		int amountAuctions = auctions.size();
		for(int i=0; i < auctions.size(); i++) {
			amountBidders += auctions.get(i).biddersSize();
		}
		//System.out.prinln(amountBidders % amountAuctions);
		return amountBidders % amountAuctions;
	}

	*/

}
