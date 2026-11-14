package com.example.Project_PULSAR.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Project_PULSAR.Entities.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    
}
