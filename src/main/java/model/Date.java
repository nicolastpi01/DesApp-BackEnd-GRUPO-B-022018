package model;

import org.joda.time.Days;
import org.joda.time.LocalDate;

public class Date {
	int day;
	int month;
	int year;
	
	public Date(int day, int month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}

	public boolean happenedDaysAgo(int days) {
		LocalDate myDate = new LocalDate(year,month,day);
		LocalDate today = LocalDate.now();
		int daysOfDifference = Days.daysBetween(myDate, today).getDays();
		return  daysOfDifference == days;
	}

	public boolean happensWithinDays(int days) {
		LocalDate myDate = new LocalDate(year,month,day);
		LocalDate today = LocalDate.now();
		int daysOfDifference = Days.daysBetween(myDate, today).getDays();
		return daysOfDifference <= days;
	}

	public boolean isAfterToday() {
		LocalDate myDate = new LocalDate(year,month,day);
		return myDate.isAfter(LocalDate.now());
	}
	
	public boolean isLaterForAtLeastTwoDays(Date publicationDate) {	
		LocalDate myDate = new LocalDate(year,month,day);
		LocalDate publication = new LocalDate(publicationDate.getYear(),publicationDate.getMonth(),publicationDate.getDay());
		int daysOfDifference = Days.daysBetween(myDate, publication).getDays();
		return daysOfDifference > 2 && myDate.isAfter(publication); 
	}
	
	/////////////////////////////// GETTERS && SETTERS  /////////////////////////////////////////////////////
	
	public int getDay() {
		return this.day;
	}
	
	public int getMonth() {
		return this.month;
	}
	
	public int getYear() {
		return this.year;
	}

}
