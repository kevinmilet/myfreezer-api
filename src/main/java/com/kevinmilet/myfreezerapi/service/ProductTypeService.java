/**
 * 
 */
package com.kevinmilet.myfreezerapi.service;

import java.util.List;

import com.kevinmilet.myfreezerapi.entity.ProductType;

/**
 * @author kevin
 *
 */
public interface ProductTypeService {

    List<ProductType> getAllProductTypes();

    ProductType createProductType(ProductType productType);

    ProductType updateProductType(Long id, ProductType productType);

    ProductType getProductTypeProduit(Long id);

    void deleteProductType(Long id);
}
