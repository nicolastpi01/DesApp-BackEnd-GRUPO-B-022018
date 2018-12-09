package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table
public class Offert {
	
	private @Id @GeneratedValue Long id;
	private Long UserId;
	private String date;
	private String hour;
	private int tramo = 0;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auction_id")
	@JsonBackReference()
	private Auction auction;
	
	public Offert() {}
	

	public Offert(Long userId) {
		Calendar calendar = new GregorianCalendar();
	    SimpleDateFormat ft = new SimpleDateFormat ("dd.MM.yyyy");
		this.setUserId(userId);
		this.setHour(calendar.get(Calendar.HOUR_OF_DAY) +":" + calendar.get(Calendar.MINUTE));
		this.setDate(ft.format(new Date()));
		this.tramo = this.incrementTramo();
	}
	
	private int incrementTramo() {
		this.tramo = this.tramo + 1;
		return this.tramo - 1;
	}

	public Long getUserId() {
		return UserId;
	}


	public void setUserId(Long userId) {
		UserId = userId;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getHour() {
		return hour;
	}


	public void setHour(String hour) {
		this.hour = hour;
	}


	public int getTramo() {
		return tramo;
	}


	public void setTramo(int tramo) {
		this.tramo = tramo;
	}

	public void setAuction(Auction auction) {
		this.auction = auction;
		
	}
	
	public Auction getAuction() {
		return this.auction;
	}

}
