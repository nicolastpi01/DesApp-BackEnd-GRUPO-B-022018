package model;

import java.util.Date;
import java.util.HashSet;

import org.joda.time.LocalDate;

import model.exceptions.InvalidDescriptionException;
import model.exceptions.InvalidEndingTimeException;
import model.exceptions.InvalidOpeningDateException;
import model.exceptions.InvalidTitleException;

public class AuctionValidation {

	// las validaciones estan bien pero las saco por simplicidad de construccion de entidades
	public void validate(Auction newAuction) {
		//validateTitle(newAuction.getTitle());
		//validateDescription(newAuction.getDescription());
		//validateOpeningDate(newAuction.getOpeningDate());
		//validateEndingDate(newAuction.getEndingDate(), newAuction.getOpeningDate());
		//validatePics(newAuction.getUrlPics());
		//validateEndingTime(newAuction.getEndingTime());
		
	}

	private void validateEndingDate(Date endingDate, Date openingDate) {
		// ending date debe ser al menos dos dias superior a opening date, caso contrario excepcion!!
		//if (! ) throw new InvalidEndingDateException();	
	}

	private void validateOpeningDate(Date openingDate) {
		if (! openingDate.after(new Date())) throw new InvalidOpeningDateException();
	}

	private void validatePics(HashSet<String> urlPics) {
		// Alguna forma de validar que cada string sea de una forma determinada, una url, etc
		
	}

	private void validateEndingTime(int endingTime) {
		if (endingTime < 0 || endingTime > 23) throw new InvalidEndingTimeException();
	}

	private void validateDescription(String description) {
		if(description.length() < 10 || description.length() > 100) throw new InvalidDescriptionException();
	}

	private void validateTitle(String title) {
		if(title.length() < 10 || title.length() > 50) throw new InvalidTitleException();
	}

	public void validateOffert(User user, Auction auction) {
		if (auction.belongsTo(user)) throw new RuntimeException();
		if (auction.isLastBidder(user)) throw new RuntimeException();
	}

}
