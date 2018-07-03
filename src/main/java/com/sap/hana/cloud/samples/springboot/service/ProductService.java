package com.sap.hana.cloud.samples.springboot.service;

import com.sap.hana.cloud.samples.springboot.model.Product;

import java.util.List;

/**
 * Created by Shishkov A.V. on 30.06.18.
 */
public interface ProductService {
	Product save(Product product);
	Product findById(Long id);
	List<Product> findAll();
	List<Product> findByPage(int page, int size);
	void delete(Long id);
	void deleteByIds(Long[] ids);
}
