package com.kevinmilet.myfreezerapi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kevinmilet.myfreezerapi.entity.Product;

/**
 * @author kevin
 *
 */
@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product> findProductByUserId(Long userId);

    List<Product> findProductByFreezerId(Long id);
}
