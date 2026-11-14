package com.example.Project_PULSAR.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Project_PULSAR.Entities.Membership;

public interface MembershipRepository extends JpaRepository<Membership, Long> {
    
}
