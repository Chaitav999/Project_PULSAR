package com.example.Project_PULSAR.Entities;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity 
@Table (name="users")
public class Users {
    
    @Id 
    @GeneratedValue (strategy=GenerationType.IDENTITY)
    @Column (name="user_id")
    private Long userId;

    @Column (name="first_name")
    private String firstName;

    @Column (name="last_name")
    private String lastName;

    @Column (name="email")
    private String email;

    @Column (name="password_hash")
    private String passwordHash;

    @Column (name="created_at", insertable=false, updatable=false)
    private Timestamp createdAt; //DEFAULT CURR_TIMESTAMP in mySQL

    public Users(){} //default constructor for JPA initialization

    public Users(String firstName, String lastName, String email, String passwordHash){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.passwordHash = passwordHash;
    }

    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public String getEmail(){
        return email;
    }
    public String getPasswordHash(){
        return passwordHash;
    }
    public Timestamp getCreatedAt(){
        return createdAt;
    }
    public Long getUserId(){
        return userId;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setPasswordHash(String passwordHash){
        this.passwordHash = passwordHash;
    }
    
}
