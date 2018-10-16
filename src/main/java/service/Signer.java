package service;

import model.User;
import model.exceptions.TooLongLastNameException;
import model.exceptions.TooLongNameException;

public class Signer {
	
	public void validate(User user) {
		validateName(user.getName());
		validateLastName(user.getLastName());
	}
	
	private void validateName(String name) {
		if (isTooLong(name))
			throw new TooLongNameException(); 
	}

	private void validateLastName(String lastName) {
		if (isTooLong(lastName)) 
			throw new TooLongLastNameException(); 
	}
	
	private Boolean isTooLong(String name) {
		return name.length() > 30;
	}

}
