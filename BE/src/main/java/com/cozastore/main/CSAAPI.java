package com.cozastore.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EnableJpaRepositories("com.cozastore.repository")
@EntityScan("com.cozastore.entity")
@ComponentScan({ "com.cozastore.config", "com.cozastore.service", "com.cozastore.filter", "com.cozastore.mappers",
		"com.cozastore.exception", "com.cozastore.dto", "com.cozastore.controller", "com.cozastore.utils" })
public class CSAAPI {

	public static void main(String[] args) {
		SpringApplication.run(CSAAPI.class, args);
	}

}
