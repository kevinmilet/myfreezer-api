package com.kevinmilet.myfreezerapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.kevinmilet.myfreezerapi.repository.ProductTypeRepository;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

/**
 * @author kevin
 *
 */
@RestController
@Slf4j
@SecurityRequirement(name = "bearerAuth")
public class ProductTypeController {

    @Autowired
    private ProductTypeRepository productTypeRepository;

    @GetMapping("/types_produits")
    public ResponseEntity<List<ProductType>> getAllProductsTypes() {

	List<ProductType> productTypeList = (List<ProductType>) productTypeRepository.findAll();

	return new ResponseEntity<>(productTypeList, HttpStatus.OK);
    }

    @PostMapping("/types_produits/create")
    public ResponseEntity<ProductType> createProductType(@Valid @RequestBody ProductType productType) {

	ProductType newProductType = new ProductType();
	newProductType.setName(productType.getName());

	productTypeRepository.save(newProductType);

	return new ResponseEntity<>(newProductType, HttpStatus.CREATED);
    }

    @DeleteMapping("/types_produits/delete/{id}")
    public ResponseEntity<Object> deleteProductType(@PathVariable("id") Long id) {

	if (id == null) {
	    log.error("type de produit inconnu");
	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	ProductType productType;

	try {
	    productType = productTypeRepository.findById(id).orElseThrow(Exception::new);
	    productTypeRepository.delete(productType);

	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	} catch (Exception e) {
	    log.error("Type de produit non trouvé: " + e.getMessage());
	}

	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/types_produits/update/{id}")
    public ResponseEntity<ProductType> updateProductType(@PathVariable("id") Long id,
	    @RequestBody ProductType productTypeReq) {

	if (id == null) {
	    log.error("L'ID du type de produit est null");
	    return null;
	}

	if (productTypeReq == null) {
	    log.error("Le type de produit est inconnu");
	    return null;
	}

	ProductType productType = productTypeRepository.findById(id).orElseThrow();

	productType.setName(productTypeReq.getName());

	productTypeRepository.save(productType);

	return new ResponseEntity<>(productType, HttpStatus.OK);
    }

}
