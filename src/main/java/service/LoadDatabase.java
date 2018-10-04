package service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;


@Configuration
@Slf4j
public class LoadDatabase {
	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

	
	@Bean
	public CommandLineRunner initDatabase(EmployeeRepository repository) {	
		return (args) -> {
			// save a couple of employees
			repository.save(new Employee("Jack", "Bauer"));
			repository.save(new Employee("Chloe", "O'Brian"));
			repository.save(new Employee("Kim", "Bauer"));

			// fetch all employees
			log.info("Employers found with findAll():");
			log.info("-------------------------------");
			for (Employee employee : repository.findAll()) {
				log.info(employee.toString());
			}
			log.info("");

			// fetch an individual employee by ID
			repository.findById(1L)
				.ifPresent(employee -> {
					log.info("Employee found with findById(1L):");
					log.info("--------------------------------");
					log.info(employee.toString());
					log.info("");
				});

			// fetch customers by name
			log.info("Customer found with findByName('Jack'):");
			log.info("--------------------------------------------");
			repository.findByName("Jack").forEach(jack -> {
				log.info(jack.toString());
			});
			
			log.info("");
		};
		
	}

}
