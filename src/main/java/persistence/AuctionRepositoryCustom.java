package persistence;

import java.util.List;
import model.Auction;

public interface AuctionRepositoryCustom {
	
	List<Auction> findAuctionsByTitleAndAddress(String title, String direction);
	
	List<Auction> findAuctionsByOwner(Long id);


}
