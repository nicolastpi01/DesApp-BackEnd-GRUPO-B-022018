package model;



import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
	private State state = State.NUEVA; 
	private int endingTime; // Por ahora no lo estoy usando (guarda con esto)
	@Temporal(TemporalType.DATE)
	private Date openingDate;
    @Temporal(TemporalType.DATE)
	private Date endingDate;
    @Temporal(TemporalType.DATE)
	private Date plusEndingDate;
   
    
	private Long lastBidderId;
	
    
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
	@JsonBackReference()
	private User owner;
	
	
	
	//@JsonIgnore // va?????
	@JsonManagedReference
	@OneToMany(mappedBy = "auction", cascade = CascadeType.ALL, orphanRemoval = true) 
    private Set<Offert> offerts = new HashSet<Offert>();
	
	
	
	@JsonIgnore
	@ManyToMany(cascade = { CascadeType.PERSIST })
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
	
	/* 
	// Add five minutes to auction
	public void minPlus() {
		
		DateTime dateTime;
		if (this.plusEndingDate !=  null) dateTime = new DateTime(this.plusEndingDate);
		else dateTime = new DateTime(this.endingDate);
		
		this.plusEndingDate = dateTime.plusMinutes(5).toDate();
		String st = plusEndingDate + ""; //
		System.out.println( st + "\n"); //
	}
	
	
	public boolean endingNow() {
		return todayIsEndingDate() || this.exceedsTheTimeLimit();  
	}
	
	
	private boolean todayIsEndingDate() {
		Date today = new Date();
		if(this.plusEndingDate != null) {
			System.out.println("pase por aca \n");
			System.out.println( this.plusEndingDate + "\n");
		return today.after(this.plusEndingDate) && 
				Math.abs(this.plusEndingDate.getTime() - today.getTime()) > 300000;
		}
		else {
			return today.after(this.getEndingDate());  
		}
	}
	*/
	
	// the time limit for an auction ending date's is past the 48 hrs
	private boolean exceedsTheTimeLimit() {
		Date today = new Date();
		return today.after(this.getEndingDate()) && 
				Math.abs(this.endingDate.getTime() - today.getTime()) > 172800000;
	}


	public boolean beginToday() {
		Date today = new Date();
		return  getDayOfMonth(this.openingDate) == getDayOfMonth(today) &&
				getMonth(this.openingDate) == getMonth(today) &&
				getYear(this.openingDate) == getYear(today); 
			//Math.abs(this.openingDate.getTime() - (new java.util.Date().getTime())) <= 86400000; 
	}
	
	private static int getDayOfMonth(Date aDate) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(aDate);
	    return cal.get(Calendar.DAY_OF_MONTH);
	}
	
	private static int getMonth(Date aDate) {
		Calendar cal = Calendar.getInstance();
	    cal.setTime(aDate);
	    return cal.get(Calendar.MONTH);
	}
	
	private static int getYear(Date aDate) {
		Calendar cal = Calendar.getInstance();
	    cal.setTime(aDate);
	    return cal.get(Calendar.YEAR);
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
		if (! this.bidders.contains(bidder)) {
			this.bidders.add(bidder);
			bidder.addBidAuction(this);
		}
    }
	
	
    public void removeBidder(User bidder) {
    	this.bidders.remove(bidder);
    	//bidder.removeBidAuction(this);
    }
    
    public boolean belongsTo(User user) {
		return owner.equals(user);
	}
    
    
    public boolean isLastBidder(User user) {
    	return user.getId() == this.lastBidderId;
	}
    
    
	public void addAPic(String url) {
		urlPics.add(url);
	}
	
	
	public void addOffert(User user) {
		Offert newOffert = new Offert(user); 
		this.offerts.add(newOffert);
		newOffert.setAuction(this);
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

	public void modifyCurrentPrice() {
		if (this.currentPrice != this.initialPrice) this.currentPrice = this.currentPrice + (this.currentPrice * 15 / 100);
		else this.currentPrice = this.initialPrice + (this.initialPrice * 15 / 100); 
		
	}

	
	public Long getLastBidderId() {
		return lastBidderId;
	}

	public void setLastBidderId(Long lastBidderId) {
		this.lastBidderId = lastBidderId;
	}


}
