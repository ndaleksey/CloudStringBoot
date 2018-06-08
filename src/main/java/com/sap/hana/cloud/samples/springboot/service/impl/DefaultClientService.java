package com.sap.hana.cloud.samples.springboot.service.impl;

import com.sap.hana.cloud.samples.springboot.dao.ClientRepository;
import com.sap.hana.cloud.samples.springboot.model.Client;
import com.sap.hana.cloud.samples.springboot.model.Gender;
import com.sap.hana.cloud.samples.springboot.service.ClientService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.StreamSupport;

/**
 * Created by Shishkov A.V. on 08.06.18.
 */
@Service
public class DefaultClientService implements ClientService {

	private ClientRepository repository;

	public DefaultClientService(ClientRepository repository) {
		this.repository = repository;
	}

	@PostConstruct
	public void init() {
		Client client1 = new Client(
				"Владимир",
				"Семенов",
				"Иванович",
				Gender.MALE,
				new GregorianCalendar(1978, 1, 2),
				"semenov@mail.ru",
				"+7-908-555-55-55",
				"Москва");

		Client client2 = new Client(
				"Петр",
				"Волков",
				"Сергеевич",
				Gender.MALE,
				new GregorianCalendar(1989, 4, 22),
				"volkov@mail.ru",
				"+7-928-155-45-52",
				"Саратов");

		Client client3 = new Client(
				"Екатерина",
				"Лукина",
				"Эдуардовна",
				Gender.MALE,
				new GregorianCalendar(1978, 1, 2),
				"semenov@mail.ru",
				"+7-952-235-87-56",
				"Самара");

		repository.save(client1);
		repository.save(client2);
		repository.save(client3);
	}

	@Override
	public void saveClient(Client client) {
		repository.save(client);
	}

	@Override
	public List<Client> getAllClients() {
		List<Client> clients = new ArrayList<>();
		StreamSupport.stream(repository.findAll().spliterator(), false).forEach(c->clients.add(c));
		return clients;
	}
}
