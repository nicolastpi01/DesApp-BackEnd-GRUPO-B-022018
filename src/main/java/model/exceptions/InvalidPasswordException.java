package model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidPasswordException extends RuntimeException {

	private static final long serialVersionUID = -3958094547352694411L;
	
	public InvalidPasswordException() {
		super(" couldn't created password. alphanumeric format required. Minimum length 4 maximum 10 ");
	}

}
