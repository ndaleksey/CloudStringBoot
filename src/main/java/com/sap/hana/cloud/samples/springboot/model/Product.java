package com.sap.hana.cloud.samples.springboot.model;

import javax.persistence.*;

/**
 * Created by Shishkov A.V. on 30.06.18.
 */
@Entity
@Table(name = "product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name", nullable = false, unique = true)
	private String name;

	@ManyToOne
	private ProductCategory category;

	public Product() {
	}

	public Product(String name, ProductCategory category) {
		this.name = name;
		this.category = category;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public ProductCategory getCategory() {
		return category;
	}

	public void setCategory(ProductCategory category) {
		this.category = category;
	}
}