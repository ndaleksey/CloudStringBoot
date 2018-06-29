package com.sap.hana.cloud.samples.springboot.model.check;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * Created by Shishkov A.V. on 29.06.18.
 */
@Entity
@Table(name = "check")
public class Check {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "number")
	private String number;

	@Column(name = "registration_time")
	private LocalDateTime registrationTime;

	@Column(name = "shop_number")
	private String shopNumber;

	@Column(name = "total_price")
	private double totalPrice;


	@Column(name = "status", columnDefinition = "smallint")
	private CheckStatus status;

	@OneToMany(mappedBy = "check", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<CheckPosition> positions;
}
