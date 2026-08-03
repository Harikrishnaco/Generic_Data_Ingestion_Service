package com.example.ingestion.controller;

import com.example.ingestion.dto.IngestionRequest;
import com.example.ingestion.model.IngestedRecord;
import com.example.ingestion.service.IngestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class IngestionController {

    private final IngestionService ingestionService;

    public IngestionController(IngestionService ingestionService) {
        this.ingestionService = ingestionService;
    }

    @PostMapping("/ingest")
    public ResponseEntity<IngestedRecord> ingest(@RequestBody IngestionRequest request) {

        IngestedRecord record = ingestionService.ingest(
                request.getSource(),
                request.getEndpoint()
        );

        return ResponseEntity.ok(record);
    }

    @GetMapping("/records")
    public ResponseEntity<List<IngestedRecord>> getAllRecords() {
        return ResponseEntity.ok(ingestionService.getAllRecords());
    }
}