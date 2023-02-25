package com.kevinmilet.myfreezerapi.entity;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Size;

@Entity
public class Freezer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 1, message = "Le nom du congélateur ne peut pas être vide")
    private String name;

    @ManyToOne
    private User user;

    @CreatedDate
    @Column(name = "date_creation", nullable = false, updatable = false)
    private Instant creationDate;

    @LastModifiedDate
    @Column(name = "date_maj")
    private Instant updateDate;

    private String freezerId;

    @ManyToOne
    private FreezerType freezerType;

    public Freezer() {

    }

    public Freezer(String name) {
	this.name = name;
    }

    public Freezer(String name, User user, String freezerId) {
	this.name = name;
	this.user = user;
	this.freezerId = freezerId;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public User getUser() {
	return user;
    }

    public void setUser(User user) {
	this.user = user;
    }

    public Instant getCreationDate() {
	return creationDate;
    }

    public void setCreationDate(Instant creationDate) {
	this.creationDate = creationDate;
    }

    public Instant getUpdateDate() {
	return updateDate;
    }

    public void setUpdateDate(Instant updateDate) {
	this.updateDate = updateDate;
    }

    public String getFreezerId() {
	return freezerId;
    }

    public void setFreezerId(String freezerId) {
	this.freezerId = freezerId;
    }

    public FreezerType getFreezerType() {
	return freezerType;
    }

    public void setFreezerType(FreezerType freezerType) {
	this.freezerType = freezerType;
    }

}
