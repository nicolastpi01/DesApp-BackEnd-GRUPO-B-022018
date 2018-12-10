package service;


import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import model.Auction;
import model.State;

@Component
public class ScheduledTasks {
	
	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
	private final AuctionRepository repo;
    //private static int i = 0;
    
    public ScheduledTasks(AuctionRepository repo) {
    	this.repo = repo;
    }
    
    
    /*
    // 86400000 ml --> 24 horas
    @Scheduled(fixedRate = 5000) // cada 5 seg (time in milis)
    //@Scheduled(cron = "0 0 0 * * *",zone = "America/Sao_Paulo")
    public void updateNewAuctions() {
    	List<Auction> auctions = this.repo.findByState(State.NUEVA);
    	for (int i=0; i < auctions.size(); i++) {
    		if (auctions.get(i).beginToday()) {
    			auctions.get(i).setState(State.ENPROGRESO);
    			this.repo.save(auctions.get(i));
    			log.info("auction in progress: " + auctions.get(i).getId());
    			log.info("difference in milis: " + Math.abs(auctions.get(i).getOpeningDate().getTime() - (new java.util.Date().getTime())));
    		}
    	}
    }
    */
    
    @Scheduled(initialDelay=180000, fixedDelay=180000)
    public void updateProgressAuctions() throws InterruptedException {
    	List<Auction> auctions = this.repo.findByState(State.ENPROGRESO);
    	for (int i=0; i < auctions.size(); i++) {
    		if (auctions.get(i).endingNow()) {
    			auctions.get(i).setState(State.TERMINADA);
    			log.info("IN PROGRESS AUCTION CHANGE STATE TO TERMINATED: " + auctions.get(i).getId());
    			// notificar al ganador!!!
    			if(auctions.get(i).getLastBidderId() != null) {
    				System.out.println("YOU WIN THE AUCTION !!!!!!! WITH ID : "+ auctions.get(i).getId());
    			}
    			this.repo.save(auctions.get(i));
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
