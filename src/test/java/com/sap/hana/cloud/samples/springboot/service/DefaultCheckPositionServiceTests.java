package com.sap.hana.cloud.samples.springboot.service;

import com.sap.hana.cloud.samples.springboot.model.check.CheckPosition;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Created by Shishkov A.V. on 06.07.18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DefaultCheckPositionServiceTests {
	@Autowired
	private CheckPositionService service;

	@Test(expected = EmptyResultDataAccessException.class)
	public void deleteById_throwsEmptyResultDataAccessException() {
		service.delete(-1L);
	}

	@Test
	public void deletePosition() {
		CheckPosition position = service.findAll().get(0);
		service.delete(position.getId());
		position = service.findById(position.getId());
		assertNull(position, "Чек не был удален");
	}
}
