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
    public ResponseEntity<List<IngestedRecord>> ingest(@RequestBody IngestionRequest request) {

        return ResponseEntity.ok(
                ingestionService.ingest(request.getEndpoints())
        );
    }

    @GetMapping("/records")
    public ResponseEntity<List<IngestedRecord>> getAllRecords() {
        return ResponseEntity.ok(ingestionService.getAllRecords());
    }
}