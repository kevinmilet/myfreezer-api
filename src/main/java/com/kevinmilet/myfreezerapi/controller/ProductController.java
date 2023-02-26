package com.kevinmilet.myfreezerapi.controller;

import java.security.Principal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

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

import com.kevinmilet.myfreezerapi.common.Utils;
import com.kevinmilet.myfreezerapi.entity.Product;
import com.kevinmilet.myfreezerapi.entity.ProductType;
import com.kevinmilet.myfreezerapi.entity.User;
import com.kevinmilet.myfreezerapi.repository.ProductRepository;
import com.kevinmilet.myfreezerapi.repository.ProductTypeRepository;
import com.kevinmilet.myfreezerapi.repository.UserRepository;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

/**
 * @author kevin
 *
 */
@RestController
@Slf4j
public class ProductController {

    private static final Long USER_ID = 1L;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductTypeRepository productTypeRepository;
    @Autowired
    private UserController userController;

    @GetMapping("/produits")
    public ResponseEntity<List<Product>> getAllProducts() {

	List<Product> products = (List<Product>) productRepository.findAll();

	return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/produit/{id}")
    public Optional<Product> getProductById(@PathVariable(name = "id") Long id) {
	if (id == null) {
	    log.error("L'ID du produit est null");
	    return null;
	}

	return productRepository.findById(id);
    }

    @GetMapping("/produits/congelateur/{id}")
    public ResponseEntity<List<Product>> getProductByFreezerId(@PathVariable("id") Long id) {

	if (id != null) {
	    List<Product> productList = productRepository.findProductByFreezerId(id);

	    return new ResponseEntity<>(productList, HttpStatus.OK);
	}
	return null;

    }

    @GetMapping("/mes_produits/")
    public ResponseEntity<List<Product>> getProductByUser(Principal principal) {

	Long userId = userController.getUserConnectedId(principal);
	List<Product> productList = productRepository.findProductByUserId(userId);

	return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @PostMapping("/produit/create")
    public ResponseEntity<?> createProduct(@Valid @RequestBody Product product, Principal principal) {

	Long userId = userController.getUserConnectedId(principal);
	Optional<User> user = userRepository.findById(userId);
	Optional<ProductType> productType = productTypeRepository.findById(product.getProductType().getId());
	String uuid = Utils.generateUUID();

	if (productType.isPresent()) {
	    product.setProductType(productType.get());
	} else {
	    return new ResponseEntity<>("Vous devez indiquer le type de produit", HttpStatus.BAD_REQUEST);
	}

	if (user.isPresent()) {
	    product.setUser(user.get());
	} else {
	    return new ResponseEntity<>("L'utilisateur n'a pas été trouvé", HttpStatus.BAD_REQUEST);
	}

	product.setCreationDate(Instant.now());
	product.setAddingDate(product.getAddingDate());
	product.setQuantity(product.getQuantity());
	product.setProductID(uuid);

	productRepository.save(product);

	return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @DeleteMapping("/produit/delete/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable("id") Long id) {

	if (id == null) {
	    log.error("produit inconnu");
	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	Product product;

	try {
	    product = productRepository.findById(id).orElseThrow(Exception::new);
	    productRepository.delete(product);

	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	} catch (Exception e) {
	    log.error("Produit non trouvé: " + e.getMessage());
	}

	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/produit/update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable(name = "id") Long id, @RequestBody Product produitReq) {

	if (id == null) {
	    log.error("L'ID du produit est null");
	    return null;
	}

	if (produitReq == null) {
	    log.error("Le produit est inconnu");
	    return null;
	}

	Product product = productRepository.findById(id).orElseThrow();

	product.setName(produitReq.getName());
	product.setQuantity(produitReq.getQuantity());
	product.setFreezer(produitReq.getFreezer());
	product.setProductType(produitReq.getProductType());
	product.setRemovingDate(produitReq.getRemovingDate());
	product.setAddingDate(produitReq.getAddingDate());
	product.setUpdateDate(Instant.now());

	productRepository.save(product);

	return new ResponseEntity<>(product, HttpStatus.OK);
    }

}
