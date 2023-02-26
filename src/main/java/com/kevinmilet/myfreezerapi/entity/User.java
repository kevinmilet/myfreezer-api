package com.kevinmilet.myfreezerapi.entity;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

/**
 * @author kevin
 *
 */
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String firstname;
    @NotBlank
    private String lastname;
    @NotBlank
    private String email;
    @JsonIgnore
    private String password;

    @CreatedDate
    @Column(nullable = false, updatable = false, columnDefinition = "datetime default now")
    private Instant created_at;

    @LastModifiedDate
    private Instant updated_at;

    private String accountId;

    @Column(columnDefinition = "boolean default false")
    private Boolean isAdmin;
    @Column(columnDefinition = "boolean default false")
    private Boolean isActive;

    private String token;

    private Boolean password_request;

    public User() {

    }

    public User(String lastname, String firstname, String email, String accountId, Boolean isAdmin, Boolean isActive) {
	this.lastname = lastname;
	this.firstname = firstname;
	this.email = email;
	this.accountId = accountId;
	this.isAdmin = isActive;
	this.isActive = isActive;
    }

    public Long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    public String getFirstname() {
	return firstname;
    }

    public void setFirstname(String firstname) {
	this.firstname = firstname;
    }

    public String getLastname() {
	return lastname;
    }

    public void setLastname(String lastname) {
	this.lastname = lastname;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public Instant getCreated_at() {
	return created_at;
    }

    public void setCreated_at(Instant created_at) {
	this.created_at = created_at;
    }

    public Instant getUpdated_at() {
	return updated_at;
    }

    public void setUpdated_at(Instant updated_at) {
	this.updated_at = updated_at;
    }

    public String getAccountId() {
	return accountId;
    }

    public void setAccountId(String accountId) {
	this.accountId = accountId;
    }

    public Boolean getIsAdmin() {
	return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
	this.isAdmin = isAdmin;
    }

    public Boolean getIsActive() {
	return isActive;
    }

    public void setIsActive(boolean isActive) {
	this.isActive = isActive;
    }

    public String getToken() {
	return token;
    }

    public void setToken(String token) {
	this.token = token;
    }

    public Boolean getPassword_request() {
	return password_request;
    }

    public void setPassword_request(Boolean password_request) {
	this.password_request = password_request;
    }

}
