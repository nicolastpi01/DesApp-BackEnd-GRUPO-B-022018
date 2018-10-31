package service;


import java.util.Set;
import org.springframework.web.bind.annotation.*;
import model.Auction;
import model.User;

@RestController
public class UserController {
	private UserService service;
	//private final UserResourceAssembler assembler;
	
	public UserController(UserService service) {
		this.service = service;
		//this.assembler = assembler;
	}
	
	@PostMapping("/users")
	User sigIn(@RequestBody User newUser) {	
		return this.service.sigIn(newUser);
	}
	
	// logIn, logOut
	
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
