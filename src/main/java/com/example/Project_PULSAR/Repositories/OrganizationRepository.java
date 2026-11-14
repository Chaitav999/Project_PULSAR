package com.example.Project_PULSAR.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Project_PULSAR.Entities.Organization;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    
}
