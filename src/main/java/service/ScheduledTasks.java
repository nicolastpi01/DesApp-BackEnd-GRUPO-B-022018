package service;


import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import model.Auction;
import model.State;

@Component
public class ScheduledTasks {
	
	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
	private final AuctionRepository repo;
    
    public ScheduledTasks(AuctionRepository repo) {
    	this.repo = repo;
    }
    
    
    /* 
    @Transactional
    @Scheduled(initialDelay=120000, fixedDelay=120000)
    public void updateNewAuctions() throws InterruptedException {
    	List<Auction> auctions = this.repo.findByState(State.NUEVA);
    	for (int i=0; i < auctions.size(); i++) {
    		if (auctions.get(i).beginToday()) {
    			repo.findById(auctions.get(i).getId())
    					.map(auction -> {
    						auction.setState(State.ENPROGRESO);
    						log.info("NEW AUCTION CHANGE STATE TO IN PROGRESS: " + auction.getId());
    		    			return repo.save(auction);
    					});
    		}
    	}
    }
   	*/
    
    @Transactional
    @Scheduled(initialDelay=180000, fixedDelay=180000)
    public void updateProgressAuctions() throws InterruptedException {
    	List<Auction> auctions = this.repo.findByState(State.ENPROGRESO);
    	for (int i=0; i < auctions.size(); i++) {
    		if (auctions.get(i).endingNow()) {
    			repo.findById(auctions.get(i).getId())
    					.map(auction -> {
    						auction.setState(State.TERMINADA);
    						log.info("IN PROGRESS AUCTION CHANGE STATE TO TERMINATED: " + auction.getId());
    		    			// notificar al ganador!!!
    		    			if(auction.getLastBidderId() != null) {
    		    				log.info("YOU WIN THE AUCTION !!!!!!! WITH ID :" + auction.getId() + "  YOUR USER ID:" + auction.getLastBidderId());
    		    			}
    		    			return repo.save(auction);
    					});
    		}
    	}
    }

}
