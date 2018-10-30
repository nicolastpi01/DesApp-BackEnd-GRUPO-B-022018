package service;

import org.springframework.stereotype.Service;

import model.User;
import model.UserValidation;
import model.exceptions.UserNotFoundException;

@Service
public class UserService {
	private final UserRepository repository;
	private final UserValidation validation;
	
	public UserService(UserRepository repository) {
		this.repository = repository;
		this.validation = new UserValidation();
	}
	
	// logIn logOut

	// create a newUser
	public User sigIn(User newUser) {
		this.validation.validate(newUser);
		return repository.save(newUser);
	}

	// update a existing user
	public User updateUser(Long id, User newUser) {
		this.validation.validate(newUser);
		return repository.findById(id)
			.map(user -> {
				user.setName(newUser.getName());
				user.setLastName(newUser.getLastName());
				user.setUserName(newUser.getUserName());
				user.setBirthday(newUser.getBirthday());
				user.setEmail(newUser.getEmail());
				user.setPassword(newUser.getPassword());
				return repository.save(user);
			})
			.orElseGet(() -> {
			newUser.setId(id);
			return repository.save(newUser);
		});
	}
	
	public User getOne(Long id) {
		User user = repository.findById(id)
			.orElseThrow(() -> new UserNotFoundException(id));
		return user;
	}

	public void delete(Long id) {
		repository.findById(id)
		.orElseThrow(() -> new UserNotFoundException(id));
			repository.deleteById(id);		
	}
	
	
	

}
