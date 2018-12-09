package service;


import java.util.List;
import java.util.Set;
import org.springframework.web.bind.annotation.*;

import com.mashape.unirest.http.exceptions.UnirestException;

import model.Auction;
import model.User;

@RestController
public class UserController {
	private UserService service;
	
	public UserController(UserService service) {
		this.service = service;
	}
	
	
	////// for revision
	@GetMapping("/users/bidAuctions/{userId}")
	public Set<Auction> getBidderAuctionsForAUserById(@PathVariable Long userId) {
		return this.service.searchBidderAuctionsForAUserById(userId);
	}
	
	@GetMapping("/users/myAuctions/{userId}")
	Set<Auction> getUserAuctionsById(@PathVariable Long userId) {
		return this.service.searchUserAuctionsById(userId);
	}
	//////for revision
	
	
	
	@GetMapping("/users")
	List<User> all() {
		return this.service.getAll();
	}
	
	
	@PostMapping("/users/register")
	void sigIn(@RequestBody User newUser) {	
		this.service.sigIn(newUser);
	}
	
	 
	@PostMapping("/users/authenticate")
	User logIn(@RequestParam("email") String email) throws UnirestException {	
		return this.service.logIn(email);
	}
	
	@PostMapping("/users/logout/{id}")
	void logOut(@PathVariable Long id) {	
		this.service.logOut(id);
	}
	
	
	@PutMapping("/users/{id}")
	User replaceUser(@PathVariable Long id, @RequestBody User newUser) {
		return this.service.updateUser(id, newUser);
	}
	
	@GetMapping("/users/{id}")
	User one(@PathVariable Long id) {	
		return this.service.getOne(id);
	}
	
	@DeleteMapping("/users/{id}")
	void deleteUser(@PathVariable Long id) {
		this.service.delete(id);
	}

}
