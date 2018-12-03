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

    //private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    
    private final AuctionRepository repo;
    
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
    
    
    
    // 300000 ml --> 5 minutos
    @Scheduled(fixedRate = 300000) // cada 5 seg 50000 (time in milis)
    public void updateInProgressAuctions() {
    	List<Auction> auctions = this.repo.findByState(State.ENPROGRESO);
    	for (int i=0; i < auctions.size(); i++) {
    		if (auctions.get(i).endingNow()) {
    			auctions.get(i).setState(State.TERMINADA);
    			this.repo.save(auctions.get(i));
    			// notificar al ganador!!!
    			log.info("ganadorrrr");
    			log.info("auction finished: " + auctions.get(i).getId());
    			log.info("state: " + auctions.get(i).getState());
    		}
    	}
    }
    */

}
