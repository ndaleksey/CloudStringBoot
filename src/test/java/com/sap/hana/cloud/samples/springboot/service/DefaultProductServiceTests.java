package com.sap.hana.cloud.samples.springboot.service;

import com.sap.hana.cloud.samples.springboot.model.Product;
import com.sap.hana.cloud.samples.springboot.model.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Created by Shishkov A.V. on 06.07.18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DefaultProductServiceTests {
	@Autowired
	private ProductService service;

	@Test(expected = Exception.class)
	public void save_productWithNullCategory() {
		service.save(new Product("Свинина", null));
	}

	@Test(expected = Exception.class)
	public void save_productIsNul() {
		service.save(null);
	}

	@Test(expected = Exception.class)
	public void save_throwAnyException() {
		service.save(new Product("LG", new ProductCategory("Смартфон")));
	}

	@Test
	public void save_productSuccessfullySaved() {
		Product product = service.save(new Product("LG", new ProductCategory("Смартфон")));
		assertNotNull(product, "");
	}

	@Test
	public void findById() {
	}

	@Test
	public void findAll() {
	}

	@Test
	public void findByPage() {
	}

	@Test
	public void delete() {
	}

	@Test(expected = Exception.class)
	public void delete_throwsException() {
		service.delete(-1L);
	}

	@Test
	public void deleteByIds() {
	}
}
