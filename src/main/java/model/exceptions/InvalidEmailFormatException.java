package model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidEmailFormatException extends RuntimeException {

	private static final long serialVersionUID = -8289156963963749982L;
	
	public InvalidEmailFormatException() {
		super(" the email couldn't be created, this must contain email format ");
	}

}
