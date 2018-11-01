package service;


import org.springframework.data.jpa.domain.Specification;
import model.Auction;
import model.State;

public class AuctionSpecifications {
	
	public static Specification<Auction> hasTitle(String title) {
        return (auction, cq, cb) -> cb.equal(auction.get("title"), title);
    }
	
	public static Specification<Auction> hasDescription(String description) {
        return (auction, cq, cb) -> cb.equal(auction.get("description"), description);
    }

    public static Specification<Auction> addressContains(String address) {
        return (book, cq, cb) -> cb.like(book.get("address"), "%" + address + "%");
    }
    
    public static Specification<Auction> auctionsByUserId(Long id) {
    	return (auction, cq, cb) -> cb.equal(auction.get("owner").get("id"), id);
    }
    
    public static Specification<Auction> auctionsByState(State s) {
    	return (auction, cq, cb) -> cb.equal(auction.get("state"), s);
    }
    

}
