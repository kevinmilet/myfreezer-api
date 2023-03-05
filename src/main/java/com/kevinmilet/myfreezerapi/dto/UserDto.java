/**
 * 
 */
package com.kevinmilet.myfreezerapi.dto;

import java.time.Instant;

/**
 * @author kevin
 *
 */
public class UserDto {

    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private Instant created_at;
    private Instant updated_at;
    private String accountId;
    private Boolean isAdmin;
    private Boolean isActive;
    private String token;
    private Boolean password_request;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
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

    public void setIsAdmin(Boolean isAdmin) {
	this.isAdmin = isAdmin;
    }

    public Boolean getIsActive() {
	return isActive;
    }

    public void setIsActive(Boolean isActive) {
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
