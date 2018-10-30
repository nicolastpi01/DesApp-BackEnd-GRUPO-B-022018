package service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@SpringBootApplication
@EntityScan( basePackages = {"model"} )
public class AuctionApplication {
	
    @SuppressWarnings("deprecation")
	private final class WebMvcConfigurerAdapterExtension extends WebMvcConfigurerAdapter {
		@Override
		public void addCorsMappings(CorsRegistry registry) {
		    registry.addMapping("/auctions").allowedOrigins("http://localhost:4200");
		    registry.addMapping("/auctions/{id}").allowedMethods("*").allowedOrigins("http://localhost:4200");
		    registry.addMapping("/auctions/populars").allowedOrigins("http://localhost:4200");
		    registry.addMapping("/auctions/recents").allowedOrigins("http://localhost:4200");
		    registry.addMapping("/auctions/toFinalize").allowedOrigins("http://localhost:4200");
		    registry.addMapping("/auctions/description").allowedOrigins("http://localhost:4200");
		    registry.addMapping("/auctions/title").allowedOrigins("http://localhost:4200");
		}
	}

	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapterExtension();
    }
	
	public static void main(String... args) {
		SpringApplication.run(AuctionApplication.class, args);
	}

}
