package model;

import java.util.List;

public class AuctionSearcher {
	private PopularCriteria popu; 
	private RecentCriteria recent;
	private FinishCriteria finish;
	
	public AuctionSearcher() {
		this.popu = new PopularCriteria();
		this.recent = new RecentCriteria();
		this.finish = new FinishCriteria();
	}

	public List<Auction> getPopulars(List<Auction> auctions) {
		return this.popu.apply(auctions);	
	}

	public List<Auction> getRecents(List<Auction> auctions) {
		return this.recent.apply(auctions);
		
	}

	public List<Auction> getNextToFinish(List<Auction> auctions) {
		return this.finish.apply(auctions);
		
	}

}
