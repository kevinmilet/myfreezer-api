package com.kevinmilet.myfreezerapi.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kevinmilet.myfreezerapi.entity.ProductType;

/**
 * @author kevin
 *
 */
@Repository
public interface ProductTypeRepository extends CrudRepository<ProductType, Long> {

    @Transactional
    @Modifying
    @Query("update ProductType pt set pt.name = :name where pt.id = :id")
    void updateProductType(@Param(value = "id") Long id, @Param(value = "name") String name);
}
