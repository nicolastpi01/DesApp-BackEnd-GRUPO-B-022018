package service;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.boot.autoconfigure.condition.SearchStrategy;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import model.Auction;
import model.State;
import model.User;
import model.exceptions.AuctionNotFoundException;
import model.exceptions.UserNotFoundException;

@RestController
public class AuctionController {
	private AuctionService service;
	//private final HomeSearcher searcher;
	
	public AuctionController(AuctionService service) {
		this.service = service;
		//this.searcher = searcher;
	}
	
	// CONTROLLER -> SERVICE -> PERSISTENCIA (MODELO)
	
	@PutMapping("/auctions/{id}")
	ResponseEntity<?> replaceAuction(@RequestBody Auction newAuction, @PathVariable Long id) throws URISyntaxException {
			return this.service.update(newAuction, id);
	}
	
	
	// // USAR Criteria
	// TODAS LAS SUBASTAS DE UN USUARIO (POR ID), TODAS LAS SUBASTAS EN LAS QUE PARTICIPO UN USUARIO (POR ID)
	// TODAS LAS SUBASTAS EN PROGRESO DE UN USUAIO (POR ID) (Se tiene que hacer por id)
	
	
	/*
	// buenas practicas rest full -> servicios
	@GetMapping("/auctions/owner/{id}")
	Resources<Resource<Auction>> findByUser(@PathVariable Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(id));
		return buildResourceWithLinks(repository.findByOwner(user));
	}
	*/
	
	@PostMapping("/auctions")
	// id usuario (para validar)
	ResponseEntity<?> newAuction(@RequestBody Auction newAuction) throws URISyntaxException {
		return this.service.create(newAuction);
		
	}
	
	
	@GetMapping("/auctions/{id}")
	Resource<Auction> one(@PathVariable Long id) {
		return this.service.getOne(id);
	}
	
	@DeleteMapping("/auctions/{id}")
	ResponseEntity<?> deleteAuction(@PathVariable Long id) {
		return this.service.delete(id);
	}
	
	
	@GetMapping("/auctions")
	Resources<Resource<Auction>> all() {
		return this.service.getAll();
	}
	
	
	/*
	
	@GetMapping("/auctions/populars")
	Resources<Resource<Auction>> getPopulars() {
		List<Auction> inProgress = repository.findByState(Estado.ENPROGRESO);
		return buildResourceWithLinks(searcher.searchPopulars(inProgress));
	}
	
	
	@GetMapping("/auctions/recents")
	Resources<Resource<Auction>> getRecents() {
		List<Auction> inProgress = repository.findByState(Estado.ENPROGRESO);
		return buildResourceWithLinks(searcher.searchRecents(inProgress));
	}
	
	@GetMapping("/auctions/toFinalize")
	Resources<Resource<Auction>> getNextToFinish() {
		List<Auction> inProgress = repository.findByState(Estado.ENPROGRESO);
		return buildResourceWithLinks(searcher.searchToFinalize(inProgress));
	}
	*/
	
	@GetMapping("/auctions/findByTitle")
	Resources<Resource<Auction>> searchByTitle(@RequestParam("title") String title) {
		return this.service.findByTitle(title);
		
	}
	
	@GetMapping("/auctions/findByDescription")
	Resources<Resource<Auction>> searchByDescription(@RequestParam("description") String description) {
		return this.service.findByDescription(description);
	}
	
}
