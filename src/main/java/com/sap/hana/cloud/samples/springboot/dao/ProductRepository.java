package com.sap.hana.cloud.samples.springboot.dao;

import com.sap.hana.cloud.samples.springboot.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Shishkov A.V. on 30.06.18.
 */
@Repository
public interface ProductRepository  extends JpaRepository<Product, Long> {

}
