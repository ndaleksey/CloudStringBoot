package com.sap.hana.cloud.samples.springboot.service;

import com.sap.hana.cloud.samples.springboot.model.check.CheckPosition;

/**
 * Created by Shishkov A.V. on 05.07.18.
 */
public interface CheckPositionService {
	CheckPosition findById(Long id);

	void delete(Long id);

	CheckPosition save(CheckPosition position);
}
