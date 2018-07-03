package com.sap.hana.cloud.samples.springboot.service.impl;

import com.sap.hana.cloud.samples.springboot.dao.ClientRepository;
import com.sap.hana.cloud.samples.springboot.model.Client;
import com.sap.hana.cloud.samples.springboot.model.Gender;
import com.sap.hana.cloud.samples.springboot.service.ClientService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

/**
 * Created by Shishkov A.V. on 08.06.18.
 */
@Transactional
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
				LocalDate.of(1978, 1, 2),
				"semenov@mail.ru",
				"+7-908-555-55-55",
				"Москва");

		Client client2 = new Client(
				"Петр",
				"Волков",
				"Сергеевич",
				Gender.MALE,
				LocalDate.of(1989, 4, 22),
				"volkov@mail.ru",
				"+7-928-155-45-52",
				"Саратов");

		Client client3 = new Client(
				"Екатерина",
				"Лукина",
				"Эдуардовна",
				Gender.FEMALE,
				LocalDate.of(1978, 1, 2),
				"semenov@mail.ru",
				"+7-952-235-87-56",
				"Самара");

		repository.save(client1);
		repository.save(client2);
		repository.save(client3);
	}

	@Override
	public Client save(Client client) {
		return repository.save(client);
	}

	@Override
	public Client findById(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public List<Client> findAll() {
		List<Client> clients = new ArrayList<>();
		StreamSupport.stream(repository.findAll().spliterator(), false).forEach(c -> clients.add(c));
		return clients;
	}

	@Override
	public List<Client> findByPage(int page, int size) {
		List<Client> clients = new ArrayList<>();
		StreamSupport.stream(repository.findAll(PageRequest.of(page, size)).spliterator(), false).forEach(c -> clients.add(c));
		return clients;
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}

	@Override
	public void deleteClientByIds(Long[] ids) {
		for (Long id : ids) {
			repository.deleteById(id);
		}
	}
}
