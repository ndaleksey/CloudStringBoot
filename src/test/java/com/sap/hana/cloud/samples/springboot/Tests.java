package com.sap.hana.cloud.samples.springboot;

import com.sap.hana.cloud.samples.springboot.model.check.Check;
import com.sap.hana.cloud.samples.springboot.model.check.CheckStatus;
import com.sap.hana.cloud.samples.springboot.service.CheckService;
import org.h2.jdbc.JdbcSQLException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.util.AssertionErrors.assertTrue;

/**
 * Created by Shishkov A.V. on 04.07.18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Tests {
	@Autowired
	private CheckService checkService;

	@Test
	public void checksListAreNotEmpty() {
		assertTrue("Список чеков пуст", !checkService.findAll().isEmpty());
	}

	@Test(expected = JdbcSQLException.class)
	public void deleteCheck_jdbcException() {
		List<Check> checks = checkService.findAll();

		Check firstCheck = checks.get(0);
		checkService.delete(firstCheck.getId());
	}

	@Test
	public void saveNewCheck() {
		Check check = new Check("10", LocalDateTime.now(), "1", CheckStatus.CREATED);
		Check savedCheck = checkService.save(check);
		Check result = checkService.findById(savedCheck.getId());
		assertNotNull("Ошибка сохранения чека", result);
	}

	@Test
	public void checkInDbIsTheSame() {
		Check check = new Check("10", LocalDateTime.now(), "1", CheckStatus.CREATED);
		Check savedCheck = checkService.save(check);
		Check result = checkService.findById(savedCheck.getId());

		assertEquals("Неверен ID", check.getId(), result.getId());
		assertEquals("Неверно время регистрации", check.getRegistrationTime(), result.getRegistrationTime());
		assertEquals("Неверен номер магазина", check.getShopNumber(), result.getShopNumber());
		assertEquals("Неверен статус", check.getStatus(), result.getStatus());
	}
}
