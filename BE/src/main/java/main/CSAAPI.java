package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication()
@EnableJpaRepositories("repository")
@EntityScan("entity")
@ComponentScan({ "config", "service", "filter", "mappers", "exception", "dto", "controller", "utils" })
public class CSAAPI {

	public static void main(String[] args) {
		SpringApplication.run(CSAAPI.class, args);
	}

}
