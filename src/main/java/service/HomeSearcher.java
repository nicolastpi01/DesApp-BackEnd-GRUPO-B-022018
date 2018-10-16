package service;

import java.util.List;
import org.springframework.stereotype.Component;
import model.Auction;
import model.FinishCriteria;
import model.PopularCriteria;
import model.RecentCriteria;

@Component
public class HomeSearcher {
	private PopularCriteria popularCriteria = new PopularCriteria();
	private RecentCriteria recentCriteria = new RecentCriteria();
	private FinishCriteria finishCriteria = new FinishCriteria();

	/*
	public List<Auction> searchPopulars(List<Auction> auctions) {
		return popularCriteria.apply(auctions);
	}

	public List<Auction> searchRecents(List<Auction> auctions) {
		return recentCriteria.apply(auctions);
	}

	public List<Auction> searchToFinalize(List<Auction> auctions) {
		return finishCriteria.apply(auctions);
	}
	*/
}
