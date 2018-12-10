package service;

import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import lombok.extern.slf4j.Slf4j;
import model.Auction;
import model.AutoBid;
import model.State;
import model.User;

@Configuration
@Slf4j
public class LoadDatabase {
	//private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

	@Bean
	public CommandLineRunner initDatabase(AuctionRepository repository, UserRepository userRepository) {	
		return (args) -> {
			
			// AGREGO DOS USUARIOS --> TONY & PAULI
			User user0 = new User("Tony", "Soprano", "tonyS", "nicolastpi10@gmail.com", "pass", new java.util.Date(), 1000);
			User user1 = new User("Paulie", "Gualtieri", "GuantieriP", "PaulieG@gmail.com", "pass", new java.util.Date(), 2000);
			
			
			////////////////////////////// TONY'S AUCTIONS  ///////////////////////////////////////////////////////////
			//* No importa la fecha de comienzo y fin porque son de Tony y no va a poder pujar por ellas
			// * No se van a mostrar en recientes, populares, ni proximas a finalizar
			Auction auction0 = new Auction("Mascara de Corrado.S", "la usa para no roncar y aliviar su viejo corazón", "address", 1000, 
					new java.util.Date(), new java.util.Date(), 12);
			Auction auction1 = new Auction("Mate Tony. S", "un mate normal", "address", 5000, 
					new java.util.Date(), new java.util.Date(), 12);
			
			////////////////////////////////// SUBASTAS PARA QUE TONY PUJE POR ELLAS ////////////////////////////
			// Terminan hoy, la idea es que se levante un thread que corra dentro de 5' y determine que estas subastas esten
			// terminadasm, en ese tiempo Tony puja y gana las subastas
			Auction auction2 = new Auction("Chaqueta de Richie Aprile", "De gran valor sentimental para este. Tony la desecho", "address", 200, 
					new java.util.Date(), new java.util.Date(), 12);
			Auction auction3 = new Auction("Gorro Corrado Jr", "Lo usa para cubrir su prominente calvicie", "address", 5000, 
					new java.util.Date(), new java.util.Date(), 12);
			
			/////////////////////////////////////////////////////////////////////////////////////////////////////
			
			// Agrego imagenes
			auction0.addAPic("https://upload.wikimedia.org/wikipedia/en/6/64/JuniorSoprano.jpg");
			auction1.addAPic("https://i.pinimg.com/originals/3b/14/c8/3b14c8243204b71217eb78d9fcd8b457.jpg");
			auction2.addAPic("https://upload.wikimedia.org/wikipedia/en/thumb/4/4f/Silvio_Dante-_Sopranos.png/220px-Silvio_Dante-_Sopranos.png");
			auction3.addAPic("https://upload.wikimedia.org/wikipedia/en/thumb/c/c2/Tony_Soprano.jpg/270px-Tony_Soprano.jpg");
			
			////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
			/*
			Auction auction4 = new Auction("Nintendo64 consola", "Consola videojuegos de Anthony Soprano. Jr", "address", 5000, 
					LocalDate.now().minusDays(10).toDate(), LocalDate.now().toDate(), 12);
			Auction auction5 = new Auction("Restaurant El Vesubio", "Restaurante el Vesubio, propiedad de Artie Bucco", "address", 5000, 
					LocalDate.now().minusDays(10).toDate(), LocalDate.now().toDate(), 12);
			*/
			
			
			
			// Las paso a En_Progreso (Para no tener conflicto con las fechas, y poder mostrar algo rapido)
			auction0.setState(State.ENPROGRESO);
			auction1.setState(State.ENPROGRESO);
			auction2.setState(State.ENPROGRESO);
			auction3.setState(State.ENPROGRESO);
			
			
			////////////////////////// AGREGO SUBASTAS DE TONY ///////////////////////////////////////
			
			userRepository.save(user0);
			
			user0.addAuction(auction0);
			user0.addAuction(auction1);
			
			repository.save(auction0);
			repository.save(auction1);
			
			
			//////////////////// SUBASTAS PARA PUJAR CON USUARIO 0 --> TONY ///////////////////////////
			
			repository.save(auction2);
			repository.save(auction3);
			
			
			
			
			
			
			
			
			
			
			
			
			// fetch all auctions
			/* 
			log.info("Employers found with findAll():");
			log.info("-------------------------------");
			for (Auction auction : repository.findAll()) {
				log.info(auction.toString());
			}
			log.info("");
			
			// fetch an individual auction by ID
			repository.findById(1L)
				.ifPresent(auction -> {
					log.info("Auction found with findById(1L):");
					log.info("--------------------------------");
					log.info(auction.toString());
					log.info("");
				});
			
			// fetch customers by name
			log.info("Customer found with findByName('Jack'):");
			log.info("--------------------------------------------");
			repository.findByTitle("Guantelete del Infinito").forEach(guantelete -> {
				log.info(guantelete.toString());
			});
			*/
			//log.info("");
		}; 
		
	}
	
}
