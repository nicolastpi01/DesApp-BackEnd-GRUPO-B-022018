package model;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PopularCriteria implements SearchCriteria {
	private static final PopularCriteria instance = new PopularCriteria();
	private final int minimumOfAuctionsPermitted = 5;
	
	@Override
	public List<Auction> search(List<Auction> auctions) {
		Collections.sort(auctions,(sub1, sub2) ->  sub2.biddersSize() - sub1.biddersSize());
		return auctions.stream().limit(minimumOfAuctionsPermitted).collect(Collectors.toList());
	}
	
	public double averageBiddersPerAuction(List<Auction> auctions) {
        return auctions.stream().mapToDouble(sub ->  sub.getBidders().size()).average().getAsDouble();    
	}
	
    public static PopularCriteria getInstance() {
        return instance;
    }

}
