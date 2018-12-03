package model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidPushOwnerException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7417445857070303064L;
	
	public InvalidPushOwnerException(Long auctionId, Long userId) {
		super("Can't push in an auction with id: " + auctionId + "from an user with id: " + userId + 
				"because user is the owner");
	}

}
