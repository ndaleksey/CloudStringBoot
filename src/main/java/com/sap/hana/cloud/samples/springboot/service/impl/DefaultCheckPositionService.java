package com.sap.hana.cloud.samples.springboot.service.impl;

import com.sap.hana.cloud.samples.springboot.dao.CheckPositionRepository;
import com.sap.hana.cloud.samples.springboot.model.check.CheckPosition;
import com.sap.hana.cloud.samples.springboot.service.CheckPositionService;
import org.springframework.stereotype.Service;

/**
 * Created by Shishkov A.V. on 05.07.18.
 */
@Service
public class DefaultCheckPositionService implements CheckPositionService {
	private CheckPositionRepository repository;

	public DefaultCheckPositionService(CheckPositionRepository repository) {
		this.repository = repository;
	}

	@Override
	public CheckPosition findById(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}

	@Override
	public CheckPosition save(CheckPosition position) {
		return repository.save(position);
	}
}
