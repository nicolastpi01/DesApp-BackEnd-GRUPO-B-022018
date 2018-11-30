package service;


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
	
	@PostMapping("/users/register")
	void sigIn(@RequestBody User newUser) {	
		this.service.sigIn(newUser);
	}
	
	@PostMapping("/users/authenticate")
	User logIn(@RequestParam("email") String email) throws UnirestException {	
		return this.service.logIn(email);
	}
	
	// logOut
	
	@GetMapping("/users/bidAuctions/{userId}")
	public Set<Auction> getBidderAuctionsForAUserById(@PathVariable Long userId) {
		return this.service.searchBidderAuctionsForAUserById(userId);
	}
	
	@GetMapping("/users/myAuctions/{userId}")
	Set<Auction> getUserAuctionsById(@PathVariable Long userId) {
		return this.service.searchUserAuctionsById(userId);
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
