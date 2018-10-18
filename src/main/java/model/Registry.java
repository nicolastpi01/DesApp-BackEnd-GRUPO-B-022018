package model;

import java.util.List;
import java.util.stream.Collectors;

import model.exceptions.MailAlreadyExistException;

public class Registry {
	
	List <User> registeredUsers;
	
	public Registry(List<User> users) {
		this.registeredUsers = users;
	}

	public Boolean isLogIn(User user) {
		return true;
	}

	//public boolean canSigIn(User user) {
	//	if (existsEmail(user)) throw new MailAlreadyExistException();
	//	else return true;
	//}

	//public boolean existsEmail(User user) {
	//	List<String> correos = registeredUsers.stream().map(u -> u.emailName()).collect(Collectors.toList());
	//	return correos.contains(user.emailName());
	//}

	public void sigIn(User user) {
		registeredUsers.add(user);
	}
}