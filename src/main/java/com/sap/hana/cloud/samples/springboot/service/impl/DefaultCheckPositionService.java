package com.sap.hana.cloud.samples.springboot.service.impl;

import com.sap.hana.cloud.samples.springboot.dao.CheckPositionRepository;
import com.sap.hana.cloud.samples.springboot.dao.CheckRepository;
import com.sap.hana.cloud.samples.springboot.model.check.Check;
import com.sap.hana.cloud.samples.springboot.model.check.CheckPosition;
import com.sap.hana.cloud.samples.springboot.service.CheckPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by Shishkov A.V. on 05.07.18.
 */
@Service
public class DefaultCheckPositionService implements CheckPositionService {
	@Autowired
	private CheckPositionRepository checkPositionRepository;

	@Autowired
	private CheckRepository checkRepository;

	@Override
	public List<CheckPosition> findAll() {
		return checkPositionRepository.findAll();
	}

	@Override
	public CheckPosition findById(Long id) {
		Optional<CheckPosition> result = checkPositionRepository.findById(id);
		return result.isPresent() ? result.get() : null;
	}

	@Override
	public void delete(Long id) {
		CheckPosition position = findById(id);

		if (position != null) {
			Check check = position.getCheck();

			if (check != null){
				check.getPositions().remove(position);
				checkRepository.save(check);
			}

//			checkPositionRepository.deleteById(id);
		}
	}

	@Override
	public CheckPosition save(CheckPosition position) {
		return checkPositionRepository.save(position);
	}
}
