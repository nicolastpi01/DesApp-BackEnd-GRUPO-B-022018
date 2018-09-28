package model;

import java.util.ArrayList;
import java.util.List;
import model.exceptions.DescriptionCharacterLengthMustBeMin10Max100Exception;
import model.exceptions.EndingDateIsNotAtLeastTwoDaysHigherThanOpeningDateException;
import model.exceptions.InvalidTimeException;
import model.exceptions.TheOpeningDateMustBeHigherThanTheCurrentDateException;
import model.exceptions.YouCanNotModifyAnAuctionWithBiddersException;
import model.exceptions.AnOwnerCanNotParticipateAsABidderInHisOwnAuctionException;
import model.exceptions.TitleLengthMin10Max50Exception;

public class Auction {
	String title;
	String description;
	String address;
	ArrayList<String> urlPics = new ArrayList<String>();
	// initial
	int price;
	Date openingDate;
	Date endingDate;
	int endTime;
	AuctionState state = new NewSubasta();
	User owner;
	List<User> bidders = new ArrayList<User>();
	
	// for simplicity in test
	public Auction() {} 
	
	public Auction(String title, String description, String address, int price,
			Date openinDate, Date endingDate, int endTime) {
		
		this.setTitle(title);
		this.setDescription(description);
		this.setAddress(address);
		this.setPrice(price);
		this.setOpeningDate(openingDate);
		this.setEndingDate(endingDate);
		this.setEndTime(endTime);
	}
	
	public boolean isNew() {
		return state.isNew();
	}

	public boolean isInProgress() {
		return state.isInProgress();
	}

	public boolean isInProgressFor(User owner) {
		return state.isInProgress() && belongsTo(owner);
	}

	private boolean belongsTo(User user) {
		return owner.equals(user);
	}
	
	public void addAPic(String url) {
		urlPics.add(url);
	}

	public boolean hasASameDescription(String description) {
		return getDescription().equals(description);
	}

	public boolean hasASameTitle(String title) {
		return getTitle().equals(title);
	}

	private boolean hasBeenPublished(int days) {
		return openingDate.happenedDaysAgo(days);
	}

	public boolean isNextToFinish(int days) {
		return endingDate.happensWithinDays(days);
	}
	
	public boolean isRecent(int days) {
		return hasBeenPublished(days);
	}

	public void addBidder(User bidderUser) {
		if (! belongsTo(bidderUser)) bidders.add(bidderUser);
		else throw new AnOwnerCanNotParticipateAsABidderInHisOwnAuctionException();
	}

	public boolean isPopular(int averageBidders) {
		return getBidders().size() >= averageBidders;
	}
	
	public int biddersSize() {
		return getBidders().size();
	}
	
	private Boolean hasAValidSizeForTitle(String title) {
		return title.length() >= 10 && title.length() <= 30;
	}
	
	private Boolean hasAValidSizeForDescription(String description) {
		return description.length() >= 10 && description.length() <= 100;
	}
	
	public boolean canBid(User user) {
		return ! this.isInProgressFor(user) && ! isBidLast(user);
	}
	
	public boolean isBidLast(User user) {
		int posLastBidder = bidders.size() - 1;
		return ! bidders.isEmpty() && bidders.get(posLastBidder).equals(user); 
	}
	
	// The exception not belongs to this class
	// Two exceptions, lack one
	public boolean canBeModified(User usuario) {
		if (getBidders().size() == 0 && belongsTo(usuario)) return true;
		else throw new YouCanNotModifyAnAuctionWithBiddersException();
	}

	public boolean hasABidder(User user) {
		Boolean isBidder = false;
		for(int i=0; i < bidders.size(); i++) {
			isBidder = isBidder || bidders.get(i).equals(user);
		}
		return isBidder;
	}
	
	/////////////////////////////////// SETTERS && GETTERS   ////////////////////////////////////////////////
	
	
	private void setAddress(String address) {
		this.address = address;	
	}
	
	public void setOpeningDate(Date openingDate) {
		if(openingDate.isAfterToday()) this.openingDate = openingDate;
		else throw new TheOpeningDateMustBeHigherThanTheCurrentDateException();		
	}
	
	// requiere mas verificación? mmm, donde?
	public void setEndingDate(Date endingDate) {
		if(endingDate.isLaterForAtLeastTwoDays(openingDate)) this.endingDate = endingDate;	
		else throw new EndingDateIsNotAtLeastTwoDaysHigherThanOpeningDateException();
	}
	
	public void setState(AuctionState state) {
		this.state = state;
	}
	
	public void setOwner(User owner) {
		this.owner = owner;
	}
	
	public User getOwner() {
		return this.owner;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public void setTitle(String title) {
		if (this.hasAValidSizeForTitle(title)) this.title = title;
		else throw new TitleLengthMin10Max50Exception();
	}

	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String description) {
		if (this.hasAValidSizeForDescription(description)) this.description = description;
		else throw new DescriptionCharacterLengthMustBeMin10Max100Exception();
	}
	
	public List<User> getBidders() {
		return this.bidders;
	}
	
	private void setPrice(int price) {
		this.price = price;
	}
	
	private void setEndTime(int endTime) {
		if (endTime >= 0 && endTime <= 23) this.endTime = endTime;
		else throw new InvalidTimeException();
	}

}
