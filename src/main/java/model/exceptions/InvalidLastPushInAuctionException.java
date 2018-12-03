package model.exceptions;

public class InvalidLastPushInAuctionException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8896691157578449974L;
	
	public InvalidLastPushInAuctionException(Long auctionId, Long userId) {
		super("Can't push in an auction w/ id:" + auctionId +  "where the user w/ id:" + userId +"is the last bidder");
	}

}
