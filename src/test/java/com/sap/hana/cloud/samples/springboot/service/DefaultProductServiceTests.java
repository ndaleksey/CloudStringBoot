package com.sap.hana.cloud.samples.springboot.service;

import com.sap.hana.cloud.samples.springboot.model.Product;
import com.sap.hana.cloud.samples.springboot.model.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Created by Shishkov A.V. on 06.07.18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DefaultProductServiceTests {
	@Autowired
	private ProductService service;

	@Autowired
	private ProductCategoryService categoryService;

	@Test(expected = NullPointerException.class)
	public void save_productWithNullCategory() {
		service.save(new Product("Свинина", null));
	}

	@Test(expected = NullPointerException.class)
	public void save_productIsNul() {
		service.save(null);
	}

	@Test(expected = Exception.class)
	public void save_throwAnyException() {
		service.save(new Product("LG", new ProductCategory("Смартфон")));
	}

	@Test
	public void save() {
		Product product = service.save(new Product("LG", new ProductCategory("Смартфон")));
		assertNotNull(product, "Неудача при сохранении продукта");
	}

	@Test
	public void findById() {
		Product product = service.save(new Product("LG", new ProductCategory("Смартфон")));
		product = service.findById(product.getId());
		assertNotNull(product, "Ошибка при поиске продукта по Id");
	}

	@Test
	public void find_byNonExistentId() {
		Product product = service.findById(-1L);
		assertNull(product, "Найден несуществующий продукт");
	}

	@Test
	public void findByName() {
		Product product = service.findAll().get(0);
		Product dbProduct = service.findByName(product.getName());
		assertEquals(product.getName(), dbProduct.getName(), "В базе не найден продукт по имени: " + product
				.getName());
	}

	@Test
	public void findAll() {
	}

	@Test
	public void findByPage() {
	}

	@Test
	public void delete() {
		/*Product product = service.save(new Product("Утка", new ProductCategory("Мясо")));
		service.delete(product.getId());
		assertNull(service.findById(product.getId()));*/
		Product product = service.findAll().get(0);
		service.delete(product.getId());
		assertNull(service.findById(product.getId()), "Объект продукта не удалился из БД");
	}

	@Test(expected = Exception.class)
	public void delete_throwsExceptionWhenIdIsNonExisted() {
		service.delete(-1L);
	}

	@Test
	public void delete_deleteOnlyProductWithoutCategory() {
		Product product = service.save(new Product("Утка", new ProductCategory("Мясо")));
		service.delete(product.getId());
		ProductCategory category = categoryService.findByName("Мясо");
		assertNotNull(category, "Категория удалилась вместе с удалением продукта");
	}

	@Test
	public void deleteByIds() {
	}
}
