package model;

import java.util.ArrayList;
import java.util.List;
 
public class RecentCriteria {
	private static final int daysOfDifferenceAllowed = 3;

	public List<Auction> apply(List<Auction> auctions) {
		ArrayList<Auction> results = new ArrayList<Auction>();
		for (int i=0; i < auctions.size(); i++) {
			if(auctions.get(i).isRecent(daysOfDifferenceAllowed)) results.add(auctions.get(i));
		}
		return results;
	}
	

}
