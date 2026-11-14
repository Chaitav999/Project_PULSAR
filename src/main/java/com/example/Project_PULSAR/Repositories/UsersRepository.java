package com.example.Project_PULSAR.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Project_PULSAR.Entities.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {
    
}
