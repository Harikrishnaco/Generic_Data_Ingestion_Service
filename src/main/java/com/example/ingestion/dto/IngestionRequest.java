package com.example.ingestion.dto;

public class IngestionRequest {

    private String source;
    private String endpoint;

    public IngestionRequest() {
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }
}