package com.example.Project_PULSAR.Entities;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity 
@Table (name="membership")
public class Membership {
    
    @Id 
    @GeneratedValue (strategy=GenerationType.IDENTITY)
    @Column (name="membership_id")
    private Long membershipId;

    @OneToOne 
    @JoinColumn (name="user_id", unique=true, nullable=false)
    private Users user;

    @ManyToOne 
    @JoinColumn (name="organization_id", nullable=false)
    private Organization organization;

    @Column (name="role", length=30)
    private String role;

    @Column (name="status", nullable=false, length=30)
    private String status;

    @Column (name="created_at", insertable=false, updatable=false)
    private Timestamp createdAt; //DEFAULT CURR_TIMESTAMP in mySQL


    public Membership(){}

    public Membership(Users user, Organization org, String role, String status){
        this.user = user;
        this.organization = org;
        this.role = role;
        this.status = status;
    }

    public Long getMembershipId(){
        return membershipId;
    }
    public Users getUser(){
        return user;
    }
    public Organization getOrganization(){
        return organization;
    }
    public String getRole(){
        return role;
    }
    public Timestamp getCreatedAt(){
        return createdAt;
    }
    public String getStatus(){
        return status;
    }

    public void setUser(Users user){
        this.user = user;
    }
    public void setOrganization(Organization organization){
        this.organization = organization;
    }
    public void setRole(String role){
        this.role = role;
    }
    public void setStatus(String status){
        this.status = status;
    }
    
}
