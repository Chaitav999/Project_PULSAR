package com.example.Project_PULSAR.Entities;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity 
@Table(name="organization")
public class Organization {
    
    @Id 
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column (name="organization_id")
    private Long organizationId;

    @Column (name="organization_name", nullable=false)
    private String organizationName;

    @Column (name="created_at", insertable=false, updatable=false)
    private Timestamp createdAt; //DEFAULT CURR_TIMESTAMP in mySQL

    public Organization(){} //default constructor for JPA initializaiton

    //for creating an organization with known values
    public Organization(String organizationName){
        this.organizationName = organizationName;
    }

    public Long getOrganizationId(){
        return organizationId;
    }
    public String getOrganizationName(){
        return organizationName;
    }
    public Timestamp getCreatedAt(){
        return createdAt;
    }

    public void setOrganizationName(String organizationName){
        this.organizationName = organizationName;
    }
}
