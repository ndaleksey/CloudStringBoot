package com.sap.hana.cloud.samples.springboot.model.check;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Shishkov A.V. on 29.06.18.
 */
@Entity
@Table(name = "simple_check")
public class Check {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "number")
	private String number;

	@Column(name = "registration_time")
	@JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
	private LocalDateTime registrationTime;

	@Column(name = "shop_number")
	private String shopNumber;

	@Column(name = "status", columnDefinition = "smallint")
	private CheckStatus status;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonManagedReference
	private Set<CheckPosition> positions = new HashSet<>();

	public Check() {
	}

	public Check(String number, LocalDateTime registrationTime, String shopNumber, CheckStatus status) {
		this.number = number;
		this.registrationTime = registrationTime;
		this.shopNumber = shopNumber;
		this.status = status;
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

	public LocalDateTime getRegistrationTime() {
		return registrationTime;
	}

	public void setRegistrationTime(LocalDateTime registrationTime) {
		this.registrationTime = registrationTime;
	}

	public String getShopNumber() {
		return shopNumber;
	}

	public void setShopNumber(String shopNumber) {
		this.shopNumber = shopNumber;
	}

	@Transient
	public double getTotalPrice() {
		double totalPrice = 0;

		for (CheckPosition position : positions) {
			totalPrice += position.getPrice() * position.getAmount();
		}

		return totalPrice;
	}

	public CheckStatus getStatus() {
		return status;
	}

	public void setStatus(CheckStatus status) {
		this.status = status;
	}

	public Set<CheckPosition> getPositions() {
		return positions;
	}

	public void setPositions(Set<CheckPosition> positions) {
		this.positions = positions;
	}
}