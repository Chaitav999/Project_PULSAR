package com.example.Project_PULSAR.Entities;

import java.math.BigDecimal;
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
@Table (name="purchase_request")
public class PurchaseRequest {
    
    @Id 
    @GeneratedValue (strategy=GenerationType.IDENTITY)
    @Column (name="request_id")
    private Long requestId;

    @ManyToOne 
    @JoinColumn (name="organization_id", nullable=false)
    private Organization organization;

    @ManyToOne 
    @JoinColumn (name="requester_id", referencedColumnName="user_id", insertable=false, updatable=false)
    private Membership requester;

    @Column (name="requester_id", nullable=false)
    private Long requesterId;

    @ManyToOne 
    @JoinColumn (name="department_id", referencedColumnName="department_id", insertable=false, updatable=false)
    private Department department;

    @Column(name = "department_id", nullable = false)
    private Long departmentId;

    @Column (name="request_status", nullable=false, length=50)
    private String requestStatus;

    @Column (name="item_name", nullable=false, length=100)
    private String itemName;

    @Column (name="item_quantity", nullable=false)
    private int itemQuantity;

    @Column (name="estimated_unit_price", nullable=false)
    private BigDecimal estimatedUnitPrice;

    @Column (name="justification", nullable=false, columnDefinition="TEXT")
    private String justification;

    @Column (name="created_at", insertable=false, updatable=false, nullable=false)
    private Timestamp createdAt;

    
    public PurchaseRequest(){}

    public PurchaseRequest(Membership requester, Organization organization, Department department, String itemName, int itemQuantity, BigDecimal estimatedUnitPrice, String justification){
        this.organization = organization;
        this.department = department;
        this.departmentId = department.getDepartmentId();
        this.requestStatus = "PENDING";
        this.itemName = itemName;
        this.itemQuantity = itemQuantity;
        this.estimatedUnitPrice = estimatedUnitPrice;
        this.requester = requester;
        this.requesterId = requester.getUser().getUserId();
        this.justification = justification;
    }

    public Long getRequestId(){
        return requestId;
    }
    public Membership getRequester(){
        return requester;
    }
    public Organization getOrganization(){
        return organization;
    }
    public Department getDepartment(){
        return department;
    }
    public String getRequestStatus(){
        return requestStatus;
    }
    public String getItemName(){
        return itemName;
    }
    public int getItemQuantity(){
        return itemQuantity;
    }
    public BigDecimal getEstimatedUnitPrice(){
        return estimatedUnitPrice;
    }
    public String getJustification(){
        return justification;
    }
    public Timestamp getCreatedAt(){
        return createdAt;
    }
    public Long getRequesterId() {
        return requesterId;
    }
    public Long getDepartmentId() {
        return departmentId;
    }

    public void setOrganization(Organization organization){
        this.organization = organization;
    }
    public void setDepartment(Department department){
        this.department = department;
    }
    public void setRequester(Membership requester){
        this.requester = requester;
    }
    public void setRequestStatus(String requestStatus){
        this.requestStatus = requestStatus;
    }
    public void setItemName(String itemName){
        this.itemName = itemName;
    }
    public void setItemQuantity(int itemQuantity){
        this.itemQuantity = itemQuantity;
    }
    public void setJustification(String justification){
        this.justification = justification;
    }
    public void setEstimatedUnitPrice(BigDecimal estimatedUnitPrice){
        this.estimatedUnitPrice = estimatedUnitPrice;
    }
}
