package com.kevinmilet.myfreezerapi.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kevinmilet.myfreezerapi.entity.ProductType;

import jakarta.validation.Valid;

/**
 * @author kevin
 *
 */
@RestController
public class ProductTypeController {

    @GetMapping("/types_produits")
    public ResponseEntity<List<ProductType>> getAllProductsTypes() {

	ProductType productType = new ProductType();
	productType.setId(1L);
	productType.setName("Plats préparés");

	return new ResponseEntity<>(Arrays.asList(productType), HttpStatus.OK);
    }

    @PostMapping("/types_produits/create")
    public ResponseEntity<ProductType> createProductType(@Valid @RequestBody ProductType productType) {

	ProductType newProductType = new ProductType();
	newProductType.setId(1L);
	newProductType.setName("Plats préparés");

	return new ResponseEntity<>(newProductType, HttpStatus.CREATED);
    }

    @DeleteMapping("/types_produits/delete/{id}")
    public ResponseEntity<Object> deleteProductType(@PathVariable("id") String id) {

	// TODO delete depuis freezerId

	return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/types_produits/update/{id}")
    public ResponseEntity<ProductType> updateProductType(@PathVariable("id") String id,
	    @RequestBody ProductType productType) {

	// TODO update depuis freezerId

	return new ResponseEntity<>(HttpStatus.OK);
    }

}
