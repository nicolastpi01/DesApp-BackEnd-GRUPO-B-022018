package model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidOpeningDateException extends RuntimeException {

	private static final long serialVersionUID = -6626474389972889397L;
	
	public InvalidOpeningDateException() {
		super (" Invalid opening date, the opening date must be, at least, one day after the current date ");
	}

}
