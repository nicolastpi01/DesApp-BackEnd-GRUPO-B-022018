package model;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table
public class Auction {
	private @Id @GeneratedValue Long id;
	private String title;
	private String description;
	private String address;
	private HashSet<String> urlPics = new HashSet<String>();
	private int initialPrice;
	private int currentPrice;
	@Enumerated(EnumType.STRING)
	private State state = State.NUEVA; // Se puede mejorar la eficiencia de cualquier enum
	private int endingTime;
	@Temporal(TemporalType.DATE)
	private Date openingDate;
    @Temporal(TemporalType.DATE)
	private Date endingDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
	private User owner;
	
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "auction_user", joinColumns = @JoinColumn(name = "auction_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Set<User> bidders = new HashSet<User>();
	
	
	public Auction() {} 
	
	 
	public Auction(String title, String description, String address, int initialPrice, Date openingDate, 
			Date endingDate, int endingTime) {
		
		this.setTitle(title);
		this.setDescription(description);
		this.setAddress(address);
		this.setInitialPrice(initialPrice);
		this.setCurrentPrice(initialPrice);
		this.setOpeningDate(openingDate);
		this.setEndingDate(endingDate);
		this.setEndingTime(endingTime);
	}
	
	
	
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Auction )) return false;
			return id != null && id.equals(((Auction) o).id);
	}
	
	@Override
	public int hashCode() {
		return 31;
	}
	
	public void addBidder(User bidder) {
       this.bidders.add(bidder);
        bidder.getAuctionsInWhichIBid().add(this);
    }
 
    public void removeBidder(User bidder) {
    	this.bidders.remove(bidder);
    	bidder.getAuctionsInWhichIBid().remove(this);
    }
 
	
	//public boolean isNew() {
	//	return state.isNew();
	//}

	//public boolean isInProgress() {
	//	return state.isInProgress();
	//}

	//public boolean isInProgressFor(User owner) {
	//	return state.isInProgress() && belongsTo(owner);
	//}

	//private boolean belongsTo(User user) {
	//	return owner.equals(user);
	//}
	
	public void addAPic(String url) {
		urlPics.add(url);
	}

	public boolean hasASameDescription(String description) {
		return getDescription().equals(description);
	}

	public boolean hasASameTitle(String title) {
		return getTitle().equals(title);
	}

	
	//public boolean isNextToFinish(int days) {
	//	return endingDate.happensWithinDays(days);
	//}
	
	//public boolean isRecent(int days) {
	//	return this.openingDate.hasBeenPublishedDaysAgo(days);
	//}
	
	/* 
	private boolean hasBeenPublished(int days) {
		return openingDate.happenedXDaysAgo(days);
	}

	public void addBidder(User bidderUser) {
		if (! belongsTo(bidderUser)) bidders.add(bidderUser);
		else throw new AnOwnerCanNotParticipateAsABidderInHisOwnAuctionException();
	}
	
	public void addABidder(User bidderUser) {
		bidders.add(bidderUser);
	}

	public boolean isPopular(int average) {
		return this.getBidders().size() >= average;
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
	
	 The exception not belongs to this class
	 Two exceptions, lack one
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
	
	*/
	
	/////////////////////////////////// SETTERS && GETTERS  /////////////////////////////////////////////
	
	public Set<User> getBidders() {
		return this.bidders;
	}
	
	
	public void setAddress(String address) {
		this.address = address;	
	}
	
	public String getAddress() {
		return this.address;
	}
	
	public void setOpeningDate(Date openingDate) {
		this.openingDate = openingDate;
	}
	
	public Date getOpeningDate() {
		return this.openingDate;
	}
	
	public void setEndingDate(Date endingDate) {
		this.endingDate = endingDate;
	}
	
	public Date getEndingDate() {
		return this.endingDate;
	}
	
	public void setState(State state) {
		this.state = state;
	}
	
	public State getState() {
		return this.state;
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
		this.title = title;
	}

	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setInitialPrice(int initialPrice) {
		this.initialPrice = initialPrice;
	}
	
	public int getInitialPrice() {
		return this.initialPrice;
	}
	
	public void setCurrentPrice(int currentPrice) {
		this.currentPrice= currentPrice;
	}
	
	public int getCurrentPrice() {
		return this.currentPrice;
	}
	
	
	public void setEndingTime(int endingTime) {
		this.endingTime = endingTime;
	}
	
	public int getEndingTime() {
		return this.endingTime;
	}

	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public HashSet<String> getUrlPics() {
		return this.urlPics;
	}
	
	public void setUrlPics(HashSet<String> urlPics) {
		this.urlPics = urlPics;
	}

}
