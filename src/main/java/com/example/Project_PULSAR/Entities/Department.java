package com.example.Project_PULSAR.Entities;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity 
@Table (name="department")
public class Department {
    
    @Id 
    @GeneratedValue (strategy=GenerationType.IDENTITY)
    @Column(name="department_id")
    private Long departmentId;

    @ManyToOne 
    @JoinColumn (name="organization_id", nullable=false)
    private Organization organization;

    @ManyToOne 
    @JoinColumn (name="manager_membership_id", referencedColumnName="membership_id", insertable=false, updatable=false)
    private Membership manager;

    @Column (name="manager_membership_id")
    private Long managerMembershipId;

    @Column (name="department_name", nullable=false)
    private String departmentName;

    @Column (name="created_at", insertable=false, updatable=false)
    private Timestamp createdAt;

    
    public Department(){}

    public Department(Organization organization, Membership manager, String departmentName){
        this.organization = organization;
        this.manager = manager;
        this.departmentName = departmentName;
        this.managerMembershipId = manager.getMembershipId();
    }

    public Organization getOrganization(){
        return organization;
    }
    public Long getDepartmentId(){
        return departmentId;
    }
    public Membership getManager(){
        return manager;
    }
    public String getDepartmentName(){
        return departmentName;
    }
    public Timestamp getCreatedAt(){
        return createdAt;
    }
    public Long getManagerMembershipId(){
        return managerMembershipId;
    }

    public void setOrganization(Organization organization){
        this.organization = organization;
    }
    public void setManager(Membership manager){
        this.manager = manager;
    }
    public void setDepartmentName(String departmentName){
        this.departmentName = departmentName;
    }
    public void setManagerMembershipId(Long managerMembershipId){
        this.managerMembershipId = managerMembershipId;
    }

}
