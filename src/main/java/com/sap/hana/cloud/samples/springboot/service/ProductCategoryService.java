package com.sap.hana.cloud.samples.springboot.service;

import com.sap.hana.cloud.samples.springboot.model.ProductCategory;

import java.util.List;

/**
 * Created by Shishkov A.V. on 06.07.18.
 */
public interface ProductCategoryService {
	List<ProductCategory> findAll();

	ProductCategory find(Long id);

	ProductCategory findByName(String name);

	ProductCategory save(ProductCategory category);

	void delete(Long id);
}
