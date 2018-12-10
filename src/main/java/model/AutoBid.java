package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.*;

@Entity
@Table
public class AutoBid {
	
	private @Id @GeneratedValue Long id; 
	private Long userId;
	private int amount;
	
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auction_id")
	private Auction auctionOwner;
	
	public AutoBid() {}
	
	public AutoBid(Long userId, int amount) {
		this.setUserId(userId);
		this.setAmount(amount);
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public void setAuctionOwner(Auction auctionOwner) {
		this.auctionOwner = auctionOwner;
	}
	
	public Auction getAuctionOWner() {
		return this.auctionOwner;
	}

}
