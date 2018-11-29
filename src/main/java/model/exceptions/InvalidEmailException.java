package model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvalidEmailException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1561173978405704327L;

	public InvalidEmailException(String email) {
		super(" your email" +email+ "is not found. You must be registered with email and username ");
	}

}
