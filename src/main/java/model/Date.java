package model;

import java.util.function.Function;

import org.joda.time.Days;
import org.joda.time.LocalDate;

public class Date {
	LocalDate myDate;
	
	public Date(int day, int month, int year) {
		myDate = new LocalDate(year,month,day);
	}
	
	private Boolean daysBetween(LocalDate endDate,Function<Integer, Boolean> funct) {
		int daysOfDifference = Days.daysBetween(myDate, endDate).getDays();
		return funct.apply(daysOfDifference);
		
	} 

	public boolean happenedXDaysAgo(int days) {	
		return daysBetween(LocalDate.now(),daysdOfDiff -> daysdOfDiff == days);
	}

	public boolean happensWithinDays(int days) {
		return daysBetween(LocalDate.now(),daysdOfDiff -> days <= daysdOfDiff );
	}

	public boolean isAfterToday() {
		return myDate.isAfter(LocalDate.now());
	}
	
	public boolean isLaterForAtLeastTwoDays(LocalDate publicationDate) {
		//enves de crear un LocalDate a partir de datos de un Date, directamente recibis un LocalDate por
		//param y evitar crear algo despues.
		return daysBetween(publicationDate,daysdOfDiff -> 2 > daysdOfDiff );
	}
}
