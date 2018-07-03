package com.sap.hana.cloud.samples.springboot.service.impl;

import com.sap.hana.cloud.samples.springboot.dao.ProductRepository;
import com.sap.hana.cloud.samples.springboot.model.Product;
import com.sap.hana.cloud.samples.springboot.service.ProductService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

/**
 * Created by Shishkov A.V. on 30.06.18.
 */

@Service
public class DefaultProductService implements ProductService {
	private ProductRepository productRepository;

	public DefaultProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public Product save(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product findById(Long id) {
		return productRepository.findById(id).get();
	}

	@Override
	public List<Product> findAll() {
		List<Product> products = new ArrayList<>();
		StreamSupport.stream(productRepository.findAll().spliterator(), false).forEach(p -> products.add(p));
		return products;
	}

	@Override
	public List<Product> findByPage(int page, int size) {
		List<Product> products = new ArrayList<>();
		StreamSupport.stream(productRepository.findAll(PageRequest.of(page, size)).spliterator(), false).forEach(p -> products.add(p));
		return products;
	}

	@Override
	public void delete(Long id) {
		productRepository.deleteById(id);
	}

	@Override
	public void deleteByIds(Long[] ids) {
		for (Long id : ids) {
			productRepository.deleteById(id);
		}
	}
}
