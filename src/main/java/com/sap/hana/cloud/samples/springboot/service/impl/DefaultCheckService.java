package com.sap.hana.cloud.samples.springboot.service.impl;

import com.sap.hana.cloud.samples.springboot.dao.CheckRepository;
import com.sap.hana.cloud.samples.springboot.model.check.Check;
import com.sap.hana.cloud.samples.springboot.service.CheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

/**
 * Created by Shishkov A.V. on 29.06.18.
 */
//@Transactional
@Service
public class DefaultCheckService implements CheckService {
	@Autowired
	private CheckRepository repository;

	@Override
	public List<Check> findAll() {
		return repository.findAll();
	}

	@Override
	public Check findById(Long id) {
		Optional<Check> result = repository.findById(id);
		return result.isPresent() ? result.get() : null;
	}

	@Override
	public Check save(Check check) {
		return repository.save(check);
	}

	@Override
	public List<Check> findByPage(int page, int size) {
		List<Check> checks = new ArrayList<>();
		StreamSupport.stream(repository.findAll(PageRequest.of(page, size)).spliterator(), false).forEach(c -> checks.add(c));
		return checks;
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}

	@Override
	public void deleteByIds(Long[] ids) {
		for (Long id : ids) {
			repository.deleteById(id);
		}
	}
}