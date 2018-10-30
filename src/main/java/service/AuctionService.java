package service;

import java.util.List;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import model.Auction;
import model.AuctionSearcher;
import model.AuctionValidation;
import model.State;
import model.UserValidation;
import model.exceptions.AuctionNotFoundException;

@SuppressWarnings("deprecation")
@Service
public class AuctionService {
	// PERSISTENCIA + LOGICA DE DOM.
	private final AuctionRepository repository;
	private AuctionSearcher searcher;
	private final AuctionValidation validation;
	//private final AuctionResourceAssembler assembler;
	
	public AuctionService(AuctionRepository repository) {
		this.repository = repository;
		this.searcher = new AuctionSearcher();
		this.validation = new AuctionValidation();
		//this.assembler = assembler; 
	}
	
	// ese set id puede generar conflictos
	// No puede estar en progreso...o no puede tener pujantes
	public Auction update(Auction newAuction, Long id) {
		this.validation.validate(newAuction);
		return repository.findById(id)
				.map(auction -> {
					auction.setTitle(newAuction.getTitle());
					// more setters
					return repository.save(auction);
				})
				.orElseGet(() -> {
					newAuction.setId(id);
					return repository.save(newAuction);
				});
	}
	
	public List<Auction> getAll() {
		return repository.findAll();
	}
	
	public Auction getOne(Long id) {
		Auction auction = repository.findById(id)
			.orElseThrow(() -> new AuctionNotFoundException(id));

		return auction;
	}
	
	// No puede estar en progreso...o no puede tener pujantes
	public void delete(Long id) {
		repository.findById(id)
		.orElseThrow(() -> new AuctionNotFoundException(id));
			repository.deleteById(id);	
	}
	
	// el usuario creador no puede tener mas de cinco subastas en progreso
	public Auction create(Auction newAuction) { 
		this.validation.validate(newAuction);
		return repository.save(newAuction);
	}
	
	public List<Auction> getPopulars() {
		return this.searcher.getPopulars(this.repository.findByState(State.ENPROGRESO));
	}
	
	
	public List<Auction> getRecents() {
		return this.searcher.getRecents(this.repository.findByState(State.ENPROGRESO));
	}
	
	
	public List<Auction> getNextToFinish() {
		return this.searcher.getNextToFinish(this.repository.findByState(State.ENPROGRESO));
	}
	
	
	public List<Auction> searchUserAuctionsById(Long id) {
		return this.repository.findAll(AuctionSpecifications.auctionsByUserId(id));
	}
	
	
	public List<Auction> userProgressAuctionsById(Long id) {
		return this.repository.findAll(Specifications.where(AuctionSpecifications.auctionsByUserId(id)).
				and(AuctionSpecifications.auctionsByState(State.ENPROGRESO)));	
	}
	
	
	public List<Auction> findByTitle(String title) {
		return this.repository.findByTitle(title);
	}
	
	public List<Auction> findByDescription(String description) {
		return this.repository.findByDescription(description);
	}


}
