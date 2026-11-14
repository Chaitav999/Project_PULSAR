CREATE DATABASE pulsar;

USE pulsar;
SELECT DATABASE();

CREATE TABLE organization (
    organization_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    organization_name VARCHAR(100) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE users (
    user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(30) NOT NULL,
    last_name VARCHAR(30) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password_hash VARCHAR(400) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE membership (
    membership_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL UNIQUE,
    organization_id BIGINT NOT NULL,
    role VARCHAR(30) NULL,
    status VARCHAR(30) NOT NULL DEFAULT 'PENDING',
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_membership_user FOREIGN KEY (user_id)
        REFERENCES users(user_id) ON DELETE RESTRICT,

    CONSTRAINT fk_membership_organization FOREIGN KEY (organization_id)
        REFERENCES organization(organization_id) ON DELETE RESTRICT,

    CONSTRAINT unq_membership_user_org
        UNIQUE (user_id, organization_id),

    CONSTRAINT unq_membership_id_org
        UNIQUE (membership_id, organization_id),

    CONSTRAINT chk_membership_status
        CHECK (status IN ('PENDING', 'ACTIVE', 'REJECTED', 'REVOKED')),

    CONSTRAINT chk_membership_role
        CHECK (role IS NULL OR role IN ('EMPLOYEE', 'MANAGER', 'FINANCE_MANAGER', 'ORGANIZATION_ADMIN'))
);

CREATE TABLE department (
    department_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    organization_id BIGINT NOT NULL,
    manager_membership_id BIGINT NOT NULL,
    department_name VARCHAR(100) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_department_organization FOREIGN KEY (organization_id)
        REFERENCES organization(organization_id)
        ON DELETE RESTRICT,

    CONSTRAINT fk_department_manager_org FOREIGN KEY (manager_membership_id, organization_id)
        REFERENCES membership(membership_id, organization_id)
        ON DELETE RESTRICT,

    CONSTRAINT uq_department_name_org
        UNIQUE (organization_id, department_name),

    CONSTRAINT uq_department_id_org
        UNIQUE (department_id, organization_id)
);

CREATE TABLE purchase_request (
    request_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    organization_id BIGINT NOT NULL,
    requester_id BIGINT NOT NULL,
    department_id BIGINT NOT NULL,

    request_status VARCHAR(50) NOT NULL DEFAULT 'PENDING',
    item_name VARCHAR(100) NOT NULL,
    item_quantity INT NOT NULL,
    estimated_cost DECIMAL(10,2) NOT NULL,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_request_organization FOREIGN KEY (organization_id)
        REFERENCES organization(organization_id)
        ON DELETE RESTRICT,

    CONSTRAINT fk_request_requester_org FOREIGN KEY (requester_id, organization_id)
        REFERENCES membership(user_id, organization_id)
        ON DELETE RESTRICT,

    CONSTRAINT fk_request_department_org FOREIGN KEY (department_id, organization_id)
        REFERENCES department(department_id, organization_id)
        ON DELETE RESTRICT,

    CONSTRAINT unq_request_id_org
        UNIQUE (request_id, organization_id),

    CONSTRAINT chk_request_status
        CHECK (request_status IN ('PENDING', 'APPROVED', 'REJECTED', 'PENDING_FINANCE_REVIEW')),

    CONSTRAINT chk_request_quantity
        CHECK (item_quantity > 0),

    CONSTRAINT chk_request_cost
        CHECK (estimated_cost >= 0)
);

CREATE TABLE request_record (
    record_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    request_id BIGINT NOT NULL,
    organization_id BIGINT NOT NULL,
    responder_membership_id BIGINT NOT NULL,

    decision_stage VARCHAR(20) NOT NULL,
    decision VARCHAR(20) NOT NULL,
    comments VARCHAR(500) NULL,

    decided_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_record_request_org FOREIGN KEY (request_id, organization_id)
        REFERENCES purchase_request(request_id, organization_id)
        ON DELETE RESTRICT,

    CONSTRAINT fk_record_responder_org FOREIGN KEY (responder_membership_id, organization_id)
        REFERENCES membership(membership_id, organization_id)
        ON DELETE RESTRICT,

    CONSTRAINT chk_record_stage
        CHECK (decision_stage IN ('MANAGER', 'FINANCE')),

    CONSTRAINT chk_record_decision
        CHECK (decision IN ('APPROVED', 'REJECTED')),

    CONSTRAINT chk_rejection_comment
        CHECK (decision <> 'REJECTED' OR (comments IS NOT NULL AND CHAR_LENGTH(TRIM(comments)) > 0))
);

CREATE TABLE audit_log (
    audit_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    organization_id BIGINT NOT NULL,
    actor_membership_id BIGINT NULL,

    entity_type VARCHAR(30) NOT NULL,
    entity_id BIGINT NOT NULL,
    action VARCHAR(50) NOT NULL,
    details JSON NULL,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_audit_organization FOREIGN KEY (organization_id)
        REFERENCES organization(organization_id)
        ON DELETE RESTRICT,

    CONSTRAINT fk_audit_actor_org FOREIGN KEY (actor_membership_id, organization_id)
        REFERENCES membership(membership_id, organization_id)
        ON DELETE RESTRICT
);


describe purchase_request;

