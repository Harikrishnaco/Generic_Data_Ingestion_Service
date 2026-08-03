package com.example.ingestion.service;

import com.example.ingestion.model.IngestedRecord;
import com.example.ingestion.repository.IngestedRecordRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class IngestionService {

    private final IngestedRecordRepository repository;

    public IngestionService(IngestedRecordRepository repository) {
        this.repository = repository;
    }

    public IngestedRecord saveRecord(String source, String endpoint, String payload) {
        IngestedRecord record = new IngestedRecord();
        record.setSource(source);
        record.setEndpoint(endpoint);
        record.setPayload(payload);
        record.setCreatedAt(LocalDateTime.now());

        return repository.save(record);
    }

    public List<IngestedRecord> getAllRecords() {
        return repository.findAll();
    }
}
