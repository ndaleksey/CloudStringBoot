package com.sap.hana.cloud.samples.springboot.service;

import com.sap.hana.cloud.samples.springboot.model.check.CheckPosition;

import java.util.List;

/**
 * Created by Shishkov A.V. on 05.07.18.
 */
public interface CheckPositionService {
	List<CheckPosition> findAll();

	CheckPosition findById(Long id);

	void delete(Long id);

	CheckPosition save(CheckPosition position);
}
