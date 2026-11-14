package com.example.Project_PULSAR.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Project_PULSAR.Entities.PurchaseRequest;

public interface PurchaseRequestRepository extends JpaRepository<PurchaseRequest, Long> {
    
}
