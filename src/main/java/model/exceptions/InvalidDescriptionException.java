package model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidDescriptionException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8414987254785472736L;
	
	public InvalidDescriptionException() {
		super(" invalid description, minimum length 10 characters, maximum 100 characters " );
	}

}
