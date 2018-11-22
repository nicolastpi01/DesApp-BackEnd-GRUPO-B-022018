package model;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

@Data
@Entity 
@Table 
public class User {
	
	@Id @GeneratedValue
	private Long id;
	private String name;
	private String lastName;
	private String userName;
	private String email;
	private String password;
	@Temporal(TemporalType.DATE) // puede ser mas eficiente
	private Date birthday;
	
	
	@JsonManagedReference
	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true) // Puede ser mas eficiente
    private Set<Auction> auctionsThatIOwn = new HashSet<Auction>();
	
	
	@ManyToMany(mappedBy = "bidders")
	private Set<Auction> auctionsInWhichIBid = new HashSet<Auction>();
	
	public User() {}
	
	public User(String name, String lastName, String userName, String email, String password, Date birthday) {
		this.setName(name);
		this.setLastName(lastName);
		this.setUserName(userName);
		this.setEmail(email);
		this.setPassword(password);
		this.setBirthday(birthday);
	}
	 

	public void addAuction(Auction auction) {
        this.auctionsThatIOwn.add(auction);
        auction.setOwner(this);
    }
	
	public void addBidAuction(Auction auction) {
		this.auctionsInWhichIBid.add(auction);
	}
	
	public void removeBidAuction(Auction auction) {
		this.auctionsInWhichIBid.remove(auction);
	}
 
    public void removeAuction(Auction auction) {
        this.auctionsThatIOwn.remove(auction);
        auction.setOwner(null);
    }
	
	////////////////////////////////// GETTERS & SETTERS   //////////////////////////////////////////////////////
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Auction> getAuctionsInWhichIBid() {
		return this.auctionsInWhichIBid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Set<Auction> getAuctionsThatIOwn() {
		return this.auctionsThatIOwn;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public void setBirthday(Date birthday) {
		this.birthday = birthday;	
	}
	
	public Date getBirthday() {
		return this.birthday;
	}

}
