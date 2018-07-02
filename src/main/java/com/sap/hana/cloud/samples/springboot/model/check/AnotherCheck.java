package com.sap.hana.cloud.samples.springboot.model.check;

import javax.persistence.*;

/**
 * Created by Shishkov A.V. on 30.06.18.
 */
@Entity
@Table(name = "another_check")
public class AnotherCheck {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "number", nullable = false)
	private String number;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}
}
