package com.people.connector;

import com.people.connector.storageServices.StorageProperties;
import com.people.connector.storageServices.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class PeopleConnectorApplication {

	public static void main(String[] args) {
		SpringApplication.run(PeopleConnectorApplication.class, args);
	}

	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
		//	storageService.deleteAll();
			storageService.init();
		};
	}
}
