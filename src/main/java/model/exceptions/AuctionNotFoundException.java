package model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AuctionNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = -1896102345817037544L;
	
	public AuctionNotFoundException(Long id) {
		super("Could not find auction " + id);
	}


}
