package com.sap.hana.cloud.samples.springboot.listener;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * Created by Shishkov A.V. on 17.07.18.
 */
public class ClientEntityListener {
	@Value("${percent}")
	private String percent;

	@PrePersist
	@PreUpdate
	private void operation(Object object) {

	}
}
