package model;


import model.exceptions.InvalidEmailFormatException;
import model.exceptions.InvalidNameException;
import model.exceptions.InvalidPasswordException;

public class UserValidation {

	// Las validaciones estan bien, pero por ahora no las usamos porque se hace muy pesado
	// escribir entidades "perfectas" para que pasen todas las validaciones. Por ej: correo con @ y .com, etc
	public void validate(User newUser) {
		//validateBirthday(newUser.getBirthday().toString()); //No se como validarlo
		//validateEmail(newUser.getEmail());
		//validateUserName(newUser.getUserName());
		//validateName(newUser.getName(), newUser);
		//validateName(newUser.getLastName(), newUser);
		//validatePassword(newUser.getPassword());
	}

	private void validateName(String name, User user) {
		if(name.length() > 30) {
			if(user.getName().equals(name)) throw new InvalidNameException("name");
			if(user.getLastName().equals(name)) throw new InvalidNameException("last name");
		}	
	}

	private void validateEmail(String email) {
		String regex = "^.*\\@(gmail|hotmail)\\.\\bcom\\b";
		if(! email.matches(regex)) throw new InvalidEmailFormatException();
	}
	
	private void validatePassword(String pass) {
		String regexCapitalLetter = "^.*[A-Z].*$";
		String regexNumber = "^.*[0-9].*$";
		if (! (pass.matches(regexCapitalLetter) && pass.matches(regexNumber) && 
				pass.length() >= 4 && pass.length() <= 10) ) throw new InvalidPasswordException();
	}

}
