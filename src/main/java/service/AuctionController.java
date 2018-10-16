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
	private final AuctionRepository repository;
	private final AuctionResourceAssembler assembler;
	private final HomeSearcher searcher;
	//private final UserRepository userRepository;
	
	public AuctionController(AuctionRepository repository, AuctionResourceAssembler assembler, HomeSearcher searcher) {
		this.repository = repository;
		//this.userRepository = userRepository;
		this.assembler = assembler;
		this.searcher = searcher;
	}
	
	
	@PutMapping("/auctions/{id}")
	ResponseEntity<?> replaceAuction(@RequestBody Auction newAuction, @PathVariable Long id) throws URISyntaxException {

		// Se puede modificar siempre y cuando el usuario sea el owner y la subasta no tenga pujantes
		// Debería recibir un parametro más indicando el usuario  que llama al método
		Auction updatedAuction = repository.findById(id)
			.map(auction -> {
				// Llevar este codigo a un objeto que se encargue de la actualización
				auction.setTitle(newAuction.getTitle());
				auction.setDescription(newAuction.getDescription());
				auction.setAddress(newAuction.getAddress());
				auction.setInitialPrice(newAuction.getInitialPrice());
				auction.setEndingDate(newAuction.getEndingDate());
				auction.setOpeningDate(newAuction.getOpeningDate());
				auction.setEndingTime(newAuction.getEndingTime());
				auction.setUrlPics(newAuction.getUrlPics());
				return repository.save(auction);
			})
			.orElseGet(() -> {
				newAuction.setId(id);
				return repository.save(newAuction);
			});

		Resource<Auction> resource = assembler.toResource(updatedAuction);

		return ResponseEntity
			.created(new URI(resource.getId().expand().getHref()))
			.body(resource);
	}
	
	// TODAS LAS SUBASTAS DE UN USUARIO (POR ID), TODAS LAS SUBASTAS EN LAS QUE PARTICIPO UN USUARIO (POR ID)
	// TODAS LAS SUBASTAS EN PROGRESO DE UN USUARIO (POR ID) (Se tiene que hacer por id)
	
	/*
	@GetMapping("/auctions/user/{id}")
	Resources<Resource<Auction>> findByUser(@PathVariable Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(id));
		return buildResourceWithLinks(repository.findByOwner(user));
	}
	*/
	
	
	@PostMapping("/auctions")
	ResponseEntity<?> newAuction(@RequestBody Auction newAuction) throws URISyntaxException {
		// Se puede generar siempre y cuando el usuario que invoca esto este registrado y autenticado
		// Se debe comprobar que la subasta tenga el formato requerido
		// Además, el usuario que llama al metodo no debe tener mas de cinco subastas en_progreso 
		Resource<Auction> resource = assembler.toResource(repository.save(newAuction));

		return ResponseEntity
			.created(new URI(resource.getId().expand().getHref()))
			.body(resource);
	}
	
	
	@GetMapping("/auctions/{id}")
	Resource<Auction> one(@PathVariable Long id) {

		Auction auction = repository.findById(id)
			.orElseThrow(() -> new AuctionNotFoundException(id));

		return assembler.toResource(auction);
	}
	
	@DeleteMapping("/auctions/{id}")
	ResponseEntity<?> deleteAuction(@PathVariable Long id) {
		// Se puede eliminar siempre y cuando el usuario sea el owner y la subasta no tenga pujantes
		// Debería recibir un parametro más indicando el usuario  que llama al método
			repository.findById(id)
			.orElseThrow(() -> new AuctionNotFoundException(id));

		repository.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}
	
	
	@GetMapping("/auctions")
	Resources<Resource<Auction>> all() {
		return buildResourceWithLinks(repository.findAll());
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
		return buildResourceWithLinks(repository.findByTitle(title));
	}
	
	@GetMapping("/auctions/findByDescription")
	Resources<Resource<Auction>> searchByDescription(@RequestParam("description") String description) {
		return buildResourceWithLinks(repository.findByDescription(description));
	}
		
	private Resources<Resource<Auction>> buildResourceWithLinks(List<Auction> filteredAuctions) {

		List<Resource<Auction>> auctions = filteredAuctions.stream()
			.map(assembler::toResource)
			.collect(Collectors.toList());

		return new Resources<>(auctions,
			linkTo(methodOn(AuctionController.class).all()).withSelfRel());
	}
	
}
