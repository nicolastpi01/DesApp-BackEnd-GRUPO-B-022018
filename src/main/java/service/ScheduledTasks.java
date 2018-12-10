package service;


import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import model.Auction;
import model.State;
import model.exceptions.AuctionNotFoundException;

@Component
public class ScheduledTasks {
	
	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
	private final AuctionRepository repo;
    //private static int i = 0;
    
    public ScheduledTasks(AuctionRepository repo) {
    	this.repo = repo;
    }
    
    
    /*
    //86400000 ml --> 24 horas
    //@Scheduled(fixedRate = 5000) // cada 5 seg (time in milis)
    @Scheduled(initialDelay=120000, fixedDelay=120000)
    public void updateNewAuctions() throws InterruptedException {
    	List<Auction> auctions = this.repo.findByState(State.NUEVA);
    	for (int i=0; i < auctions.size(); i++) {
    		if (auctions.get(i).beginToday()) {
    			auctions.get(i).setState(State.ENPROGRESO);
    			this.repo.save(auctions.get(i));
    			log.info("NEW AUCTION CHANGE STATE TO IN PROGRESS: " + auctions.get(i).getId());
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
    		    				log.info("YOU WIN THE AUCTION !!!!!!! WITH ID : " + auction.getId());
    		    			}
    		    			return repo.save(auction);
    					});
    			//.orElseThrow(() -> new AuctionNotFoundException(auctions.get(i).getId()));
    		}
    	}
    }
    
    // cada tres minutos con un delay de tres minutos
    // espera hasta que termina cada una de las operaciones
    /*
    @Scheduled(initialDelay=180000, fixedDelay=180000)
    public void testScheduling() throws InterruptedException {
        System.out.println("Started : "+ ++i);
        Thread.sleep(4000);
        System.out.println("Finished : "+ i);
    } */

}
