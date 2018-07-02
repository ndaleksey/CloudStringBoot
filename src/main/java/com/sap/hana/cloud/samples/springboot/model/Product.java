package com.sap.hana.cloud.samples.springboot.model;

import javax.persistence.*;

/**
 * Created by Shishkov A.V. on 30.06.18.
 */
@Entity
@Table
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "name", nullable = false, unique = true)
	private String name;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "category_id")
	private ProductCategory category;

	public Product(String name, ProductCategory category) {
		this.name = name;
		this.category = category;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ProductCategory getCategory() {
		return category;
	}

	public void setCategory(ProductCategory category) {
		this.category = category;
	}
}