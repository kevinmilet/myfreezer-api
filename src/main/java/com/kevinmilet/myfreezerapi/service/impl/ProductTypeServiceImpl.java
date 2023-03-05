package com.kevinmilet.myfreezerapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kevinmilet.myfreezerapi.entity.ProductType;
import com.kevinmilet.myfreezerapi.repository.ProductTypeRepository;
import com.kevinmilet.myfreezerapi.service.ProductTypeService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author kevin
 *
 */
@Service
@Slf4j
public class ProductTypeServiceImpl implements ProductTypeService {

    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Override
    public List<ProductType> getAllProductTypes() {

	return (List<ProductType>) productTypeRepository.findAll();

    }

    @Override
    public ProductType createProductType(ProductType productType) {

	ProductType newProductType = new ProductType();
	newProductType.setName(productType.getName());

	return productTypeRepository.save(newProductType);
    }

    @Override
    public ProductType updateProductType(Long id, ProductType productType) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public ProductType getProductTypeProduit(Long id) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void deleteProductType(Long id) {
	// TODO Auto-generated method stub

    }

}
