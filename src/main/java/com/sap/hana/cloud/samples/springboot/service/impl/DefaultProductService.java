package com.sap.hana.cloud.samples.springboot.service.impl;

import com.sap.hana.cloud.samples.springboot.dao.ProductCategoryRepository;
import com.sap.hana.cloud.samples.springboot.dao.ProductRepository;
import com.sap.hana.cloud.samples.springboot.model.Product;
import com.sap.hana.cloud.samples.springboot.model.ProductCategory;
import com.sap.hana.cloud.samples.springboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;


/**
 * Created by Shishkov A.V. on 30.06.18.
 */

@Service
public class DefaultProductService implements ProductService {
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	ProductCategoryRepository categoryRepository;

	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	public Product findById(Long id) {
		Optional<Product> result = productRepository.findById(id);
		return result.isPresent() ? result.get() : null;
	}

	@Override
	public Product findByName(String name) {
		return productRepository.findByName(name);
	}

	@Override
	public List<Product> findByPage(int page, int size) {
		List<Product> products = new ArrayList<>();
		StreamSupport.stream(productRepository.findAll(PageRequest.of(page, size)).spliterator(), false).forEach(p -> products.add(p));
		return products;
	}

	@Transient
	@Override
	public void delete(Long id) {
		productRepository.deleteById(id);
	}

	@Override
	public void deleteByIds(Long[] ids) {
		for (Long id : ids) productRepository.deleteById(id);
	}

	@Override
	public Product save(Product product) {


		if (product == null) throw new NullPointerException("Продукт равен null");

		ProductCategory category = product.getCategory();

		if (category == null) throw new NullPointerException("У продукта не заполнена категория (категория равна " +
				"null)");


		if (category.getId() == null) {
			product.setCategory(categoryRepository.save(category));
		}

		return productRepository.save(product);
	}
}