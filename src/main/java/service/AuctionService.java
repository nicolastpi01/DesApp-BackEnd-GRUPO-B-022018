package service;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import model.Auction;
import model.exceptions.AuctionNotFoundException;

@Service
public class AuctionService {
	private final AuctionRepository repository;
	private final AuctionResourceAssembler assembler;
	
	public AuctionService(AuctionRepository repository, AuctionResourceAssembler assembler) {
		this.repository = repository; 
		this.assembler = assembler; 
	}

	// Revisar este metodo (por la excepción)
	public ResponseEntity<?> update(Auction newAuction, Long id) throws URISyntaxException {
		//Se puede modificar siempre y cuando el usuario sea el owner y la subasta no tenga pujantes
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

	public ResponseEntity<?> create(Auction newAuction) throws URISyntaxException {
		// Se puede generar siempre y cuando el usuario que invoca esto este registrado y autenticado
		// Se debe comprobar que la subasta tenga el formato requerido
		// Además, el usuario que llama al metodo no debe tener mas de cinco subastas en_progreso 
		Resource<Auction> resource = assembler.toResource(repository.save(newAuction));

			return ResponseEntity
				.created(new URI(resource.getId().expand().getHref()))
				.body(resource);
	}

	public Resource<Auction> getOne(Long id) {
		Auction auction = repository.findById(id)
			.orElseThrow(() -> new AuctionNotFoundException(id));

		return assembler.toResource(auction);
	}

	public ResponseEntity<?> delete(Long id) {
		// Se puede eliminar siempre y cuando el usuario sea el owner y la subasta no tenga pujantes
		// Debería recibir un parametro más indicando el usuario  que llama al método
			repository.findById(id)
			.orElseThrow(() -> new AuctionNotFoundException(id));
				repository.deleteById(id);	
				return ResponseEntity.noContent().build();
	}
	
	private Resources<Resource<Auction>> buildResourceWithLinks(List<Auction> filteredAuctions) {

		List<Resource<Auction>> auctions = filteredAuctions.stream()
			.map(assembler::toResource)
			.collect(Collectors.toList());

		return new Resources<>(auctions,
			linkTo(methodOn(AuctionController.class).all()).withSelfRel());
	}

	public Resources<Resource<Auction>> getAll() {
		return buildResourceWithLinks(repository.findAll());
	}

	public Resources<Resource<Auction>> findByTitle(String title) {
		return buildResourceWithLinks(repository.findByTitle(title));
	}

	public Resources<Resource<Auction>> findByDescription(String description) {
		return buildResourceWithLinks(repository.findByDescription(description));
	}

}
