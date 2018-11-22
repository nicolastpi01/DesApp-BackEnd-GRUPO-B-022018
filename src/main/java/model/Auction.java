package model;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
	@JsonBackReference()
	private User owner;
	
	
	@JsonIgnore
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "auction_user", joinColumns = @JoinColumn(name = "auction_id"), 
					inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Set<User> bidders = new HashSet<User>();
	

	public Auction() {
		LocalDate od = LocalDate.now().plusDays(1);
		this.setOpeningDate(od.toDate());
		this.setEndingDate(od.plusDays(2).toDate());
		this.setEndingTime(0);
	} 
	
	 
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
	
	public boolean isPopular(int average) {
		return this.getBidders().size() >= average;
	}
	
	public boolean isRecent(int days) {
		LocalDate od = LocalDate.fromDateFields(openingDate);
		LocalDate today = LocalDate.now();
		return this.isBeforeOrEqual(od, today) && this.daysBetween(od, today) <= days;
	}
	
	public boolean isNextToFinish(int days) {
		LocalDate ed = LocalDate.fromDateFields(endingDate);
		LocalDate today = LocalDate.now();
		return ed.isAfter(today) && this.daysBetween(ed, today) <= days;
	}
	
	
	private int daysBetween(LocalDate date, LocalDate anotherDate) {
		return Math.abs(Days.daysBetween(date, anotherDate).getDays());
	}
	
	private Boolean isBeforeOrEqual(LocalDate aDate, LocalDate anotherDate) {
		return aDate.isBefore(anotherDate) || aDate.isEqual(anotherDate);
	}
	
	
	public int biddersSize() {
		return getBidders().size();
	}
	
	public void addBidder(User bidder) {
       this.bidders.add(bidder);
       bidder.addBidAuction(this);
    }
 
    public void removeBidder(User bidder) {
    	this.bidders.remove(bidder);
    	//bidder.removeBidAuction(this);
    }
    
    public boolean belongsTo(User user) {
		return owner.equals(user);
	}
    
    public boolean isLastBidder(User user) {
    	//int lastBidersPos = bidders.size() - 1;
    	return false;
		//return ! bidders.isEmpty() && bidders.toArray(0).get(lastBidersPos).equals(user);
	}
    
    
 
    /*

	

	
	

	public boolean hasASameDescription(String description) {
		return getDescription().equals(description);
	}

	public boolean hasASameTitle(String title) {
		return getTitle().equals(title);
	}


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
	
	private Boolean hasAValidSizeForTitle(String title) {
		return title.length() >= 10 && title.length() <= 30;
	}
	
	private Boolean hasAValidSizeForDescription(String description) {
		return description.length() >= 10 && description.length() <= 100;
	}
	
	public boolean canBid(User user) {
		return ! this.isInProgressFor(user) && ! isBidLast(user);
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
	
	public void addAPic(String url) {
		urlPics.add(url);
	}
	
	/////////////////////////////////// SETTERS && GETTERS  /////////////////////////////////////////////
	
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return this.title;
	}
	
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
