package com.sap.hana.cloud.samples.springboot.service;

import com.sap.hana.cloud.samples.springboot.model.Client;
import com.sap.hana.cloud.samples.springboot.model.Gender;
import com.sap.hana.cloud.samples.springboot.model.check.Check;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Shishkov A.V. on 06.07.18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DefaultClientServiceTests {
	@Autowired
	private ClientService clientService;

	@Autowired
	private CheckService checkService;

	@Test(expected = Exception.class)
	public void exceptionWhenSaveClientWithExistedCheckId() {
		Client client = new Client("Alex", "Dreamer", "Father", Gender.MALE, LocalDate.of(2000, 11, 23), "email@mail" +
				".com", "123232323", "Moscow");
		Check firstCheck = checkService.findAll().get(0);
		client.getChecks().add(firstCheck);
		clientService.save(client);
	}

	@Test(expected = Exception.class)
	public void exception_whenSaveExistedClient() {
		Client client = clientService.findAll().get(0);
		clientService.save(client);
	}

	@Test
	public void saveClientWithExistedCheck() {
		Client client = new Client("Alex", "Dreamer", "Father", Gender.MALE, LocalDate.of(2000, 11, 23), "email@mail" +
				".com", "123232323", "Moscow");

		client = clientService.save(client);

		Check firstCheck = checkService.findAll().get(0);
		client.addCheck(firstCheck);
		client = clientService.save(client);
		client = clientService.findById(client.getId());

		assertNotNull("Сохраненный клиент не найден", client);
	}
}
