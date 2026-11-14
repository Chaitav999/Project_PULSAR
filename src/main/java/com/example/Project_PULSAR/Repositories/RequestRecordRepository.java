package com.example.Project_PULSAR.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Project_PULSAR.Entities.RequestRecord;

public interface RequestRecordRepository extends JpaRepository<RequestRecord, Long> {
    
}
