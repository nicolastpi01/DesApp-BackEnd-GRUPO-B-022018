package service;


import org.springframework.web.bind.annotation.*;
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
	
	@PutMapping("/users/{id}")
	User replaceUser(@PathVariable Long id, @RequestBody User newUser) {
		return this.service.updateUser(id, newUser);
	}
	
	@GetMapping("/users/{id}")
	User one(@PathVariable Long id) {	
		return this.service.getOne(id);
	}
	
	// No podes tener una subasta donde esten pujando otros usuarios
	// Si estabas pujando sobre una subasta automaticamente la perdes, y se la lleva el anterior postor
	@DeleteMapping("/users/{id}")
	void deleteUser(@PathVariable Long id) {
		this.service.delete(id);
	}

}
