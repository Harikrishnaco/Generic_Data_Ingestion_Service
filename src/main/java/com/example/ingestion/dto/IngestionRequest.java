package com.example.ingestion.dto;

import java.util.List;

public class IngestionRequest {

    private List<EndpointConfig> endpoints;

    public List<EndpointConfig> getEndpoints() {
        return endpoints;
    }

    public void setEndpoints(List<EndpointConfig> endpoints) {
        this.endpoints = endpoints;
    }

    public static class EndpointConfig {

        private String source;
        private String endpoint;
        private PaginationConfig pagination;
        private String authType;
        private String authValue;

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

        public PaginationConfig getPagination() {
            return pagination;
        }

        public void setPagination(PaginationConfig pagination) {
            this.pagination = pagination;
        }

        public String getAuthType() {
            return authType;
        }

        public void setAuthType(String authType) {
            this.authType = authType;
        }

        public String getAuthValue() {
            return authValue;
        }

        public void setAuthValue(String authValue) {
            this.authValue = authValue;
        }
    }
}