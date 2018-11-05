package service;

import java.util.List;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import model.Auction;
import model.AuctionSearcher;
import model.AuctionValidation;
import model.State;
import model.exceptions.AuctionNotFoundException;
import model.exceptions.InvalidUpdateAuctionInProgressException;

@SuppressWarnings("deprecation")
@Service
public class AuctionService {
	private final AuctionRepository repository;
	private AuctionSearcher searcher;
	private final AuctionValidation validation;
	
	public AuctionService(AuctionRepository repository) {
		this.repository = repository;
		this.searcher = new AuctionSearcher();
		this.validation = new AuctionValidation(); 
	}
	
	// No puede estar en progreso...o no puede tener pujantes
	// Usuario debe ser owner de esta subasta y ademas estar registrado
	public Auction update(Auction newAuction, Long id) {
		this.validation.validate(newAuction);
		return repository.findById(id)
				.map(auction -> {
					if (auction.getState().equals(State.ENPROGRESO)) 
						//throw new InvalidUpdateAuctionInProgressException(); (esto esta bien pero lo comento para simplicidad)
					auction.setTitle(newAuction.getTitle());
					auction.setDescription(newAuction.getDescription());
					auction.setAddress(newAuction.getAddress());
					auction.setUrlPics(newAuction.getUrlPics());
					auction.setOpeningDate(newAuction.getOpeningDate());
					auction.setEndingDate(newAuction.getEndingDate());
					auction.setEndingTime(newAuction.getEndingTime());
					auction.setInitialPrice(newAuction.getInitialPrice());
					return repository.save(auction);
				})
				.orElseThrow(() -> new AuctionNotFoundException(id));
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
	// Usuario debe ser owner de esta subasta y ademas estar registrado
	public void delete(Long id) {
		repository.findById(id)
		.orElseThrow(() -> new AuctionNotFoundException(id));
		if (repository.findById(id).get().getState().equals(State.ENPROGRESO)) 
			//throw new InvalidUpdateAuctionInProgressException(); (comentado por simplicidad)
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
