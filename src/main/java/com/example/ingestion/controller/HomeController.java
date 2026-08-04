package com.example.ingestion.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return """
                Generic Data Ingestion Service is running.

                Endpoints:
                GET  /api/records
                POST /api/ingest
                """;
    }
}