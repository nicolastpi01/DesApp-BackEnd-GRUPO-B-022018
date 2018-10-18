package service;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.Auction;
import model.State;
import model.User;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, Long> {
	List<Auction> findByTitle(String title);
	List<Auction> findByState(State state);
	List<Auction> findByDescription(String description);

}
