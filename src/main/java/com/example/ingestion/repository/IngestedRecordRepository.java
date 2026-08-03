package com.example.ingestion.repository;


import com.example.ingestion.model.IngestedRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngestedRecordRepository extends JpaRepository<IngestedRecord, Long> {
}
