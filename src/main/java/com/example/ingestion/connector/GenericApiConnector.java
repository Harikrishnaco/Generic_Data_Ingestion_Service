package com.example.ingestion.connector;

import com.example.ingestion.dto.PaginationConfig;
import org.springframework.http.*;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import java.time.Duration;

@Component
public class GenericApiConnector {

    private final RestTemplate restTemplate;

    public GenericApiConnector() {
        this.restTemplate = new RestTemplateBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .readTimeout(Duration.ofSeconds(30))
                .build();
    }

    @Retryable(
            retryFor = Exception.class,
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000)
    )
    public String fetchData(String endpoint,
                            PaginationConfig pagination,
                            String authType,
                            String authValue) {

        if (pagination == null || !"limit-skip".equalsIgnoreCase(pagination.getType())) {
            return executeRequest(endpoint, authType, authValue);
        }

        StringBuilder allData = new StringBuilder();
        int skip = 0;

        while (true) {

            String url = endpoint + "?"
                    + pagination.getLimitParam() + "=" + pagination.getPageSize()
                    + "&"
                    + pagination.getOffsetParam() + "=" + skip;

            String response = executeRequest(url, authType, authValue);

            allData.append(response).append("\n");

            if (!response.contains("\"total\":")) {
                break;
            }

            int total = Integer.parseInt(
                    response.split("\"total\":")[1]
                            .split(",")[0]
                            .trim()
            );

            skip += pagination.getPageSize();

            if (skip >= total) {
                break;
            }
        }

        return allData.toString();
    }

    private String executeRequest(String url,
                                  String authType,
                                  String authValue) {

        HttpHeaders headers = new HttpHeaders();

        if ("BEARER".equalsIgnoreCase(authType)) {
            headers.setBearerAuth(authValue);
        } else if ("API_KEY".equalsIgnoreCase(authType)) {
            headers.set("X-API-Key", authValue);
        }

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                String.class
        );

        return response.getBody();
    }
}