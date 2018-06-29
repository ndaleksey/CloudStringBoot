package com.sap.hana.cloud.samples.springboot.model.check;

import javax.persistence.*;

/**
 * Created by Shishkov A.V. on 29.06.18.
 */
@Entity
@Table(name = "check_position")
public class CheckPosition {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "number")
	private String number;

	@Column(name = "product_id")
	private Long productId;

	@Column(name = "product_category_id")
	private Long productCategoryId;

	@Column(name = "amount")
	private double amount;

	@Column(name = "price")
	private double price;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "check_id", nullable = false)
	private Check check;
}
