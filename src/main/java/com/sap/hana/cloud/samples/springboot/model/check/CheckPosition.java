package com.sap.hana.cloud.samples.springboot.model.check;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sap.hana.cloud.samples.springboot.model.Product;

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

	@Column(name = "number", nullable = false)
	private String number;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	@Column(name = "amount")
	private double amount;

	@Column(name = "price")
	private double price;

	@ManyToOne
	@JoinColumn(name = "check_id", nullable = false)
	@JsonManagedReference
	private Check check;

	public CheckPosition() {
	}

	public CheckPosition(Check check, String number, Product product, double amount, double price) {
		this.number = number;
		this.product = product;
		this.amount = amount;
		this.price = price;
		this.check = check;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Check getCheck() {
		return check;
	}

	public void setCheck(Check check) {
		this.check = check;
	}
}