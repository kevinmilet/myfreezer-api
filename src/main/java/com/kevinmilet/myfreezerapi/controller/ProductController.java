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

import com.kevinmilet.myfreezerapi.entity.Freezer;
import com.kevinmilet.myfreezerapi.entity.Product;
import com.kevinmilet.myfreezerapi.entity.ProductType;

import jakarta.validation.Valid;

/**
 * @author kevin
 *
 */
@RestController
public class ProductController {

    @GetMapping("/produits")
    public ResponseEntity<List<Product>> getAllProducts() {

	Product product = new Product();
	product.setId(1L);
	product.setProductID("f1548rf1514v41f9bg885f14hb48hb");
	product.setName("Pizza");
	product.setFreezer(new Freezer("Mon congélo"));
	product.setProductType(new ProductType("Plats préparés"));
	product.setQuantity(3);

	return new ResponseEntity<>(Arrays.asList(product), HttpStatus.OK);
    }

    @PostMapping("/produit/create")
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {

	Product newProduct = new Product();
	newProduct.setId(1L);
	newProduct.setProductID("f1548rf1514v41f9bg885f14hb48hb");
	newProduct.setName("Pizza");
	newProduct.setFreezer(new Freezer("Mon congélo"));
	newProduct.setProductType(new ProductType("Plats préparés"));
	newProduct.setQuantity(3);

	return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    @DeleteMapping("/product/delete/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable("id") String id) {

	// TODO delete depuis freezerId

	return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/product/update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") String id, @RequestBody Product product) {

	// TODO update depuis freezerId

	return new ResponseEntity<>(HttpStatus.OK);
    }

}
