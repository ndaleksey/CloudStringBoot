package com.sap.hana.cloud.samples.springboot.service;

import com.sap.hana.cloud.samples.springboot.model.check.Check;

import java.util.List;

/**
 * Created by Shishkov A.V. on 29.06.18.
 */
public interface CheckService {
	Check save(Check client);
	Check findById(Long id);
	List<Check> findAll();
	List<Check> findByPage(int page, int size);
	void delete(Long id);
	void deleteByIds(Long[] ids);
}
