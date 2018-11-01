package service;


import java.util.Set;
import org.springframework.stereotype.Service;
import model.Auction;
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
	
	// USAR Criteria
		//TODAS LAS SUBASTAS EN LAS QUE PARTICIPO UN USUARIO (POR ID)
		
		//@GetMapping("/auctions/owner/{id}")
		//@CrossOrigin(origins = "http://localhost:4200")
		//List<Auction> getUserAuctionsById(@PathVariable Long id) {
		//	return this.service.searchUserAuctionsById(id);
		//}
	
	
	
	// logIn logOut
	
	// todas las subastas de un usuario por id
	public Set<Auction> searchUserAuctionsById(Long id) {
		User user = repository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(id));
			return user.getAuctionsThatIOwn();
	}
	
	// todas las subastas donde pujo un usuario por id
	public Set<Auction> searchBidderAuctionsForAUserById(Long id) {
		User user = repository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(id));
			return user.getAuctionsInWhichIBid();
	}

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
			.orElseThrow(() -> new UserNotFoundException(id));
	}
	
	
	public User getOne(Long id) {
		User user = repository.findById(id)
			.orElseThrow(() -> new UserNotFoundException(id));
		return user;
	}

	// No podes tener una subasta donde esten pujando otros usuarios
	// Si estabas pujando sobre una subasta automaticamente la perdes, y se la lleva el anterior postor
	public void delete(Long id) {
		repository.findById(id)
		.orElseThrow(() -> new UserNotFoundException(id));
			repository.deleteById(id);		
	}
	

}
