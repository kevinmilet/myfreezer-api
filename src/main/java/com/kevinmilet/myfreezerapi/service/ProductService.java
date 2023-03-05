/**
 * 
 */
package com.kevinmilet.myfreezerapi.service;

import java.security.Principal;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.kevinmilet.myfreezerapi.entity.Product;

/**
 * @author kevin
 *
 */
public interface ProductService {

    List<Product> getAllProducts();

    List<Product> getProductsByUser(Principal principal);

    List<Product> getProductByFreezer(Long id);

    ResponseEntity<Product> createProduct(Product product, Principal principal);

    Product updateProduct(Long id, Product product);

    void deleteProduct(Long id);

    Product getProductById(Long id);
}
