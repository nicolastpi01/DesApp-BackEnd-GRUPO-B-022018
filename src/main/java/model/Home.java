package model;

import java.util.ArrayList;
import java.util.List;

public class Home {
	
	public List<Auction> recentAuctions(List<Auction> auctions) {
		return RecentCriteria.getInstance().search(auctions);
	}

	public List<Auction> nextToFinishAuctions(List<Auction> auctions) {
		return FinishCriteria.getInstance().search(auctions);
	}

	public List<Auction> searchForDescription(String description, ArrayList<Auction> auctions) {
		return DescriptionCriteria.getInstance(description).search(auctions);
	}
	
	public List<Auction> searchForTitle(String title, ArrayList<Auction> auctions) {
		return TitleCriteria.getInstance(title).search(auctions);
	}

	public List<Auction> popularsAuctions(List<Auction> auctions) {
		return PopularCriteria.getInstance().search(auctions);
	}
	
}
