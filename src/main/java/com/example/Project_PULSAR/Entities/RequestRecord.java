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
@Table (name="request_record")
public class RequestRecord {
    
    @Id
    @GeneratedValue (strategy=GenerationType.IDENTITY)
    @Column (name="record_id")
    private Long recordId;

    @ManyToOne 
    @JoinColumn (name="request_id", referencedColumnName="request_id", updatable=false, insertable=false)
    private PurchaseRequest request;

    @Column(name = "request_id", nullable = false)
    private Long requestId;

    @Column(name = "responder_membership_id", nullable = false)
    private Long responderMembershipId;

    @ManyToOne 
    @JoinColumn (name="responder_membership_id", referencedColumnName="membership_id", updatable=false, insertable=false)
    private Membership responder;

    @Column(name = "organization_id", nullable = false)
    private Long organizationId;

    @Column (name="decision_stage", nullable=false, length=20)
    private String decisionStage;

    @Column (name="decision", nullable=false, length=20)
    private String decision;

    @Column (name="comments", length=500)
    private String comments;

    @Column (name="decided_at", insertable=false, updatable=false, nullable=false)
    private Timestamp decidedAt;

    public RequestRecord(){}

    public RequestRecord(PurchaseRequest request, Membership responder, String decisionStage, String decision, String comments){
        this.request = request;
        this.requestId = request.getRequestId();
        this.responder = responder;
        this.responderMembershipId = responder.getMembershipId();
        this.organizationId = request.getOrganization().getOrganizationId();
        this.decisionStage = decisionStage;
        this.decision = decision;
        this.comments = comments;
    }

    public Long getRecordId(){
        return recordId;
    }
    public Long getRequestId() {
        return requestId;
    }
    public PurchaseRequest getRequest(){
        return request;
    }
    public Membership getResponder(){
        return responder;
    }
    public Long getResponderMembershipId() {
        return responderMembershipId;
    }
     public Long getOrganizationId() {
        return organizationId;
    }
    public String getDecisionStage(){
        return decisionStage;
    }
    public String getDecision(){
        return decision;
    }
    public String getComments(){
        return comments;
    }
    public Timestamp getDecidedAt(){
        return decidedAt;
    }

    public void setRequest(PurchaseRequest request){
        this.request = request;
    }
    public void setResponder(Membership responder){
        this.responder = responder;
    }
    public void setDecisionStage(String decisionStage){
        this.decisionStage = decisionStage;
    }
    public void setDecision(String decision){
        this.decision = decision;
    }
    public void setComments(String comments){
        this.comments = comments;
    }
}
