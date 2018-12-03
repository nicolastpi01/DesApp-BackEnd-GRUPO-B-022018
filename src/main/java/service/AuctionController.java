package service;


import java.util.List;
import org.springframework.web.bind.annotation.*;
import model.Auction;
import model.User;


@RestController
public class AuctionController {
	private AuctionService service;
	
	public AuctionController(AuctionService service) {
		this.service = service;
	}
		
	@GetMapping("/auctions")
	List<Auction> all() {
		return this.service.getAll();
	}
	
	@PutMapping("/auctions/{id}")
	Auction replaceAuction(@PathVariable Long id, @RequestBody Auction newAuction) {
		return this.service.update(newAuction, id);
	}
	
	@GetMapping("/auctions/{id}")
	Auction one(@PathVariable Long id) {
		return this.service.getOne(id);
	}
	
	@DeleteMapping("/auctions/{id}")
	void deleteAuction(@PathVariable Long id) {
		this.service.delete(id);
	}
	
	
	@PostMapping("/auctions")
	public Auction newAuction(@RequestBody Auction newAuction) {
		return this.service.create(newAuction);
	}
	
	
	@GetMapping("/auctions/populars")
	List<Auction> getPopulars() {
		return this.service.getPopulars();
	}
	
	
	@GetMapping("/auctions/recents")
	List<Auction> getRecents() {
		return this.service.getRecents();
	}
	
	@GetMapping("/auctions/tofinalize")
	List<Auction> getNextToFinish() {
		return this.service.getNextToFinish();
	}
	
	@GetMapping("/auctions/description")
	List<Auction> searchByDescription(@RequestParam("description") String description) {
		return this.service.findByDescription(description);
	}
	

	@GetMapping("/auctions/title")
	List<Auction> searchByTitle(@RequestParam("title") String title) {
		return this.service.findByTitle(title);
	}

	
	
	////////////////////////////////// BID //////////////////////////////////////////
	
	
	/*
	@PutMapping("/auctions/bid/{id}")
	// @RequestBody Auction auction
	Auction makeABid(@PathVariable Long id, @RequestBody Long userId) {
		return this.service.makeABid(id, userId);
	}
	
	*/
	
	@PutMapping("/auctions/offert/{id}")
	Auction makeAOffert(@PathVariable Long id, @RequestBody User user) {
		return this.service.makeOffert(id, user);
	}
	
	
}
