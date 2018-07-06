package com.sap.hana.cloud.samples.springboot.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Shishkov A.V. on 06.07.18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DefaultCheckServiceTests {
	@Autowired
	private CheckService service;

	@Test(expected = EmptyResultDataAccessException.class)
	public void deleteById_throwsEmptyResultDataAccessException() {
		service.delete(-1L);
	}
}
