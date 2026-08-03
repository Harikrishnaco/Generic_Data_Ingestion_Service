package com.example.ingestion.service;

import com.example.ingestion.connector.GenericApiConnector;
import com.example.ingestion.dto.IngestionRequest;
import com.example.ingestion.model.IngestedRecord;
import com.example.ingestion.repository.IngestedRecordRepository;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class IngestionService {

    private final IngestedRecordRepository repository;
    private final GenericApiConnector connector;
    private static final Logger log = LoggerFactory.getLogger(IngestionService.class);

    public IngestionService(IngestedRecordRepository repository,
                            GenericApiConnector connector) {
        this.repository = repository;
        this.connector = connector;
    }

    public List<IngestedRecord> ingest(List<IngestionRequest.EndpointConfig> endpoints) {

        List<IngestedRecord> records = new ArrayList<>();

        for (IngestionRequest.EndpointConfig endpoint : endpoints) {

            try {

                String payload = connector.fetchData(
                        endpoint.getEndpoint(),
                        endpoint.getPagination(),
                        endpoint.getAuthType(),
                        endpoint.getAuthValue()
                );

                IngestedRecord record = new IngestedRecord();
                record.setSource(endpoint.getSource());
                record.setEndpoint(endpoint.getEndpoint());
                record.setPayload(payload);
                record.setCreatedAt(LocalDateTime.now());

                records.add(repository.save(record));

            } catch (Exception e) {

                log.error("Failed to ingest endpoint: {}", endpoint.getEndpoint(), e);

            }
        }

        return records;
    }

    public List<IngestedRecord> getAllRecords() {
        return repository.findAll();
    }
}