package com.example.Project_PULSAR.Entities;

import java.sql.Timestamp;
import java.util.Map;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity 
@Table (name="audit_log")
public class AuditLog {
    
    @Id 
    @GeneratedValue (strategy=GenerationType.IDENTITY)
    @Column (name="audit_id")
    private Long auditId;

    @ManyToOne 
    @JoinColumn (name="organization_id", nullable=false)
    private Organization organization;

    @ManyToOne 
    @JoinColumn (name="actor_membership_id", referencedColumnName="membership_id", insertable=false, updatable=false)
    private Membership actor;

    @Column (name="actor_membership_id")
    private Long actorMembershipId;

    @Column (name="entity_type", nullable=false, length=30)
    private String entityType;

    @Column (name="entity_id", nullable=false)
    private Long entityId;

    @Column (name="action", nullable=false, length=30)
    private String action;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column (name="details", columnDefinition="JSON")
    private Map<String, Object> details;

    @Column (name="created_at", insertable=false, updatable=false, nullable=false)
    private Timestamp createdAt;

    public AuditLog(){}

    public AuditLog(Organization organization, Membership actor, String entityType, Long entityId, String action, Map<String, Object> details){
        this.organization = organization;
        this.actor = actor;
        this.entityType = entityType;
        this.entityId = entityId;
        this.action = action;
        this.details = details;
        this.actorMembershipId = actor.getMembershipId();
    }

    public Long getAuditId(){
        return auditId;
    }
    public Organization getOrganization(){
        return organization;
    }
    public Membership getActor(){
        return actor;
    }
    public String getEntityType(){
        return entityType;
    }
    public Long getEntityId(){
        return entityId;
    }
    public String getAction(){
        return action;
    }
    public Map<String, Object> getDetails(){
        return details;
    }
    public Timestamp getCreatedAt(){
        return createdAt;
    }
    public Long getActorMembershipId(){
        return actorMembershipId;
    }

    public void setOrganization(Organization organization){
        this.organization = organization;
    }
    public void setActor(Membership actor){
        this.actor = actor;
    }
    public void setEntityType(String entityType){
        this.entityType = entityType;
    }
    public void setEntityId(Long entityId){
        this.entityId = entityId;
    }
    public void setAction(String action){
        this.action = action;
    }
    public void setDetails(Map<String, Object> details){
        this.details = details;
    }
    public void setActorMembershipId(Long actorMembershipId){
        this.actorMembershipId = actorMembershipId;
    }

}
