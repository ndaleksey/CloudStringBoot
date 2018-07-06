package com.sap.hana.cloud.samples.springboot.service.impl;

import com.sap.hana.cloud.samples.springboot.dao.ProductCategoryRepository;
import com.sap.hana.cloud.samples.springboot.model.ProductCategory;
import com.sap.hana.cloud.samples.springboot.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by Shishkov A.V. on 06.07.18.
 */
@Service
public class DefaultProductCategoryService implements ProductCategoryService {
	@Autowired
	ProductCategoryRepository repository;

	@Override
	public List<ProductCategory> findAll() {
		return repository.findAll();
	}

	@Override
	public ProductCategory find(Long id) {
		Optional<ProductCategory> result = repository.findById(id);
		return result.isPresent() ? result.get() : null;
	}

	@Override
	public ProductCategory save(ProductCategory category) {
		return repository.save(category);
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}
}