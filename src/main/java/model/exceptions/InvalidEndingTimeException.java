package model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidEndingTimeException extends RuntimeException {

	private static final long serialVersionUID = -7638147840878478873L;
	
	public InvalidEndingTimeException() {
		super(" Invalid ending time, the time must be betweend 0 and 23 hrs");
	}

}
