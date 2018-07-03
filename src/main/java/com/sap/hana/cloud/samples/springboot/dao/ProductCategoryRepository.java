package com.sap.hana.cloud.samples.springboot.dao;

import com.sap.hana.cloud.samples.springboot.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Shishkov A.V. on 02.07.18.
 */
@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
	ProductCategory findByName(String name);
}
