package service;


import java.util.List;
import org.springframework.web.bind.annotation.*;
import model.Auction;

//RPC (Remote Procedure Call) for simplicity (not restfull)

@RestController
public class AuctionController {
	private AuctionService service;
	
	public AuctionController(AuctionService service) {
		this.service = service;
	}
		
	// USAR Criteria
	// TODAS LAS SUBASTAS DE UN USUARIO (POR ID), TODAS LAS SUBASTAS EN LAS QUE PARTICIPO UN USUARIO (POR ID)
	// User controller
	
	//@GetMapping("/auctions/owner/{id}")
	//@CrossOrigin(origins = "http://localhost:4200")
	//List<Auction> getUserAuctionsById(@PathVariable Long id) {
	//	return this.service.searchUserAuctionsById(id);
	//}
	
	
	@PutMapping("/auctions/{id}")
	//@CrossOrigin(origins = "http://localhost:4200")
	Auction replaceAuction(@PathVariable Long id, @RequestBody Auction newAuction) {
		return this.service.update(newAuction, id);
	}
	
	@GetMapping("/auctions")
	//@CrossOrigin(origins = "http://localhost:4200")
	List<Auction> all() {
		return this.service.getAll();
	}
	
	
	@GetMapping("/auctions/{id}")
	//@CrossOrigin(origins = "http://localhost:4200")
	Auction one(@PathVariable Long id) {
		return this.service.getOne(id);
	}
	
	//@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("/auctions/{id}")
	void deleteAuction(@PathVariable Long id) {
		this.service.delete(id);
	}
	
	
	@PostMapping("/auctions")
	//@CrossOrigin(origins = "http://localhost:4200")
	public Auction newAuction(@RequestBody Auction newAuction) {
		return this.service.create(newAuction);
	}
	
	@GetMapping("/auctions/populars")
	//@CrossOrigin(origins = "http://localhost:4200")
	List<Auction> getPopulars() {
		return this.service.getPopulars();
	}
	
	
	@GetMapping("/auctions/recents")
	//@CrossOrigin(origins = "http://localhost:4200")
	List<Auction> getRecents() {
		return this.service.getRecents();
	}
	
	@GetMapping("/auctions/tofinalize")
	//@CrossOrigin(origins = "http://localhost:4200")
	List<Auction> getNextToFinish() {
		return this.service.getNextToFinish();
	}
	
	@GetMapping("/auctions/description")
	//@CrossOrigin(origins = "http://localhost:4200")
	List<Auction> searchByDescription(@RequestParam("description") String description) {
		return this.service.findByDescription(description);
	}
	

	@GetMapping("/auctions/title")
	//@CrossOrigin(origins = "http://localhost:4200")
	List<Auction> searchByTitle(@RequestParam("title") String title) {
		return this.service.findByTitle(title);
	}
	
	
}
