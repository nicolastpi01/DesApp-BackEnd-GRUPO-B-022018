package model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidNameException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6609310674801614987L;
	
	public InvalidNameException(String name) {
		super(name + " invalid. maximum length 30 characters " );
	}

}
