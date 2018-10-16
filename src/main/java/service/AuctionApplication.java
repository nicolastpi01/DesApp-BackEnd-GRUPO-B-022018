package service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@SpringBootApplication
@EntityScan( basePackages = {"model"} )
public class AuctionApplication {
	
	public static void main(String... args) {
		SpringApplication.run(AuctionApplication.class, args);
	}

}
