package com.example.ingestion.connector;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GenericApiConnector {

    private final RestTemplate restTemplate = new RestTemplate();

    public String fetchData(String endpoint) {
        return restTemplate.getForObject(endpoint, String.class);
    }
}