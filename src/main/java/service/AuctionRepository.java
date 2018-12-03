package service;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import model.Auction;
import model.State;
import persistence.AuctionRepositoryCustom;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, Long>, JpaSpecificationExecutor<Auction>, AuctionRepositoryCustom {
	
	List<Auction> findByTitle(String title);
	List<Auction> findByState(State state);
	List<Auction> findByDescription(String description);

}
