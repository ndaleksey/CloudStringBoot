package com.sap.hana.cloud.samples.springboot;

import com.sap.hana.cloud.samples.springboot.service.CheckPositionService;
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
public class CheckPositionRepositoryTests {
	@Autowired
	private CheckPositionService service;

	@Test(expected = EmptyResultDataAccessException.class)
	public void deleteCheckPosition_throwsException() {
		service.delete(0L);
	}
}
