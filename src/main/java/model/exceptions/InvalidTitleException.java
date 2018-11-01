package model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidTitleException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5408511484576944458L;
	
	public InvalidTitleException() {
		super(" invalid title, minimum length 10 characters, maximum 30 characters ");
	}

}
