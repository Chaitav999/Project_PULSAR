package com.example.Project_PULSAR.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Project_PULSAR.Entities.AuditLog;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long>{
    
}
