package com.example.ingestion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class GenericDataIngestionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GenericDataIngestionServiceApplication.class, args);
	}

}
