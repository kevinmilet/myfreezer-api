package com.kevinmilet.myfreezerapi.entity;

import java.time.Instant;
import java.time.LocalDate;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;
    private int quantity;

    @ManyToOne
    private ProductType productType;

    private LocalDate addingDate;
    private LocalDate removingDate;

    @OneToOne
    private Freezer freezer;

    @ManyToOne
    private User user;

    @CreatedDate
    @Column(name = "date_creation", nullable = false, updatable = false)
    private Instant creationDate;

    @LastModifiedDate
    @Column(name = "date_maj")
    private Instant updateDate;

    private String productID;

    public Product() {

    }

    public Product(String name, int quantity, ProductType productType, LocalDate addingDate, LocalDate removingDate,
	    Freezer freezer, User user, String productID) {
	this.name = name;
	this.quantity = quantity;
	this.productType = productType;
	this.addingDate = addingDate;
	this.removingDate = removingDate;
	this.freezer = freezer;
	this.user = user;
	this.productID = productID;
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

    public int getQuantity() {
	return quantity;
    }

    public void setQuantity(int quantity) {
	this.quantity = quantity;
    }

    public ProductType getProductType() {
	return productType;
    }

    public void setProductType(ProductType productType) {
	this.productType = productType;
    }

    public LocalDate getAddingDate() {
	return addingDate;
    }

    public void setAddingDate(LocalDate addingDate) {
	this.addingDate = addingDate;
    }

    public LocalDate getRemovingDate() {
	return removingDate;
    }

    public void setRemovingDate(LocalDate removingDate) {
	this.removingDate = removingDate;
    }

    public Freezer getFreezer() {
	return freezer;
    }

    public void setFreezer(Freezer freezer) {
	this.freezer = freezer;
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

    public String getProductID() {
	return productID;
    }

    public void setProductID(String productID) {
	this.productID = productID;
    }

}
