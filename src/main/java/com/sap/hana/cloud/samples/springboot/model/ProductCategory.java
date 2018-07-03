package com.sap.hana.cloud.samples.springboot.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Shishkov A.V. on 30.06.18.
 */
@Entity
@Table(name = "product_category")
public class ProductCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name", nullable = false, unique = true)
	private String name;

	@OneToMany(mappedBy = "category")
	private Set<Product> products = new HashSet<>();

	public ProductCategory() {
	}

	public ProductCategory(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Set<Product> getProducts() {
		return products;
	}
}
