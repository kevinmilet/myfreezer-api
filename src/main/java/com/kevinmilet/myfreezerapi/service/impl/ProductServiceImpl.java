/**
 * 
 */
package com.kevinmilet.myfreezerapi.service.impl;

import java.security.Principal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.kevinmilet.myfreezerapi.entity.Product;
import com.kevinmilet.myfreezerapi.service.ProductService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author kevin
 *
 */
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Override
    public List<Product> getAllProducts() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<Product> getProductsByUser(Principal principal) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<Product> getProductByFreezer(Long id) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public ResponseEntity<Product> createProduct(Product product, Principal principal) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void deleteProduct(Long id) {
	// TODO Auto-generated method stub

    }

    @Override
    public Product getProductById(Long id) {
	// TODO Auto-generated method stub
	return null;
    }

}
