package com.sap.hana.cloud.samples.springboot.service;

import com.sap.hana.cloud.samples.springboot.model.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Shishkov A.V. on 06.07.18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DefaultProductCategoryServiceTests {
	@Autowired
	private ProductCategoryService service;

	@Test(expected = Exception.class)
	public void save_throwAnyException() {
		service.save(new ProductCategory("Мясо"));
	}

	@Test(expected = Exception.class)
	public void delete_whenIdIsNotExisted() {
		service.delete(-1L);
	}
}
