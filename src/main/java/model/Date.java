package model;

import java.util.function.Function;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.joda.time.Days;
import org.joda.time.LocalDate;

public class Date {
	private
	Long id;
	private int year;
	private int month;
	private int day;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auction_id")
	private Auction auction;
	
	
	public Date() {}
	
	public Date(int day, int month, int year) {
		this.setYear(year);
		this.setMonth(month);
		this.setDay(day);	
	}
	
	/* 
	private Boolean daysBetween(LocalDate date,Function<Integer, Boolean> funct) {
		LocalDate start;
		LocalDate end;
		
		if(date.isBefore(myDate)) {
	
			start = date;
			end = myDate;
		}else {
			start = myDate;
			end = date;
		}
		int daysOfDifference = Days.daysBetween(start,end).getDays();	
		return funct.apply(daysOfDifference);
	} 

	public boolean hasBeenPublishedDaysAgo(int days) {	
		return daysBetween(LocalDate.now(), daysdOfDiff -> daysdOfDiff == days);
	}

	public boolean happensWithinDays(int days) {
		return daysBetween(LocalDate.now(), daysdOfDiff -> days >= daysdOfDiff );
	}

	public boolean isAfterToday() {
		return myDate.isAfter(LocalDate.now());
	}
	
	public boolean isLaterForAtLeastTwoDays(LocalDate publicationDate) {
		//enves de crear un LocalDate a partir de datos de un Date, directamente recibis un LocalDate por
		//param y evitar crear algo despues.
		return daysBetween(publicationDate,daysdOfDiff -> 2 < daysdOfDiff );
	}
	*/
	
	//////////////////////////////// GETTERS & SETTERS

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public void setAuction(Auction auction) {
		this.auction = auction;
	}
	
	public Auction getAuction() {
		return this.auction;
	}
	
}
