package service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import model.Auction;
import model.User;
import model.exceptions.AuctionNotFoundException;
import model.exceptions.UserNotFoundException;

@RestController
public class UserController {
	private final UserRepository repository;
	private final UserResourceAssembler assembler;
	private final Signer signer;
	
	public UserController(UserRepository repository, UserResourceAssembler assembler) {
		this.repository = repository;
		this.assembler = assembler;
		this.signer = new Signer();
	}
	
	@PostMapping("/users")
	ResponseEntity<?> sigIn(@RequestBody User newUser) throws URISyntaxException {
		
		signer.validate(newUser);	
		
		Resource<User> resource = assembler.toResource(repository.save(newUser));

		return ResponseEntity
			.created(new URI(resource.getId().expand().getHref()))
			.body(resource);
	}
	
	
	// logIn, logOut
	
	
	/* 
	@PutMapping("/users/{id}")
	ResponseEntity<?> updateUser(@RequestBody User newUser, @PathVariable Long id) throws URISyntaxException {

		User updatedUser = repository.findById(id)
			.map(user -> {
				user.setName(newUser.getName());
				user.setLastName(newUser.getLastName());
				// more setters
				return repository.save(user);
			})
			.orElseGet(() -> {
				newUser.setId(id);
				return repository.save(newUser);
			});

		Resource<User> resource = assembler.toResource(updatedUser);

		return ResponseEntity
			.created(new URI(resource.getId().expand().getHref()))
			.body(resource);
	}
	*/
	
	@GetMapping("/users/{id}")
	Resource<User> one(@PathVariable Long id) {

		User user = repository.findById(id)
			.orElseThrow(() -> new UserNotFoundException(id));

		return assembler.toResource(user);
	}
	
	@DeleteMapping("/users/{id}")
	ResponseEntity<?> deleteUser(@PathVariable Long id) {
		
				repository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(id));
				
		repository.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}

}
