package model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidUpdateAuctionInProgressException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6661226607434919416L;
	
	public InvalidUpdateAuctionInProgressException() {
		super(" Invalid update auction. you cannot modify an auction with status in progress ");
	}

}
