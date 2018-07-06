package com.sap.hana.cloud.samples.springboot.service.impl;

import com.sap.hana.cloud.samples.springboot.dao.ClientRepository;
import com.sap.hana.cloud.samples.springboot.model.Client;
import com.sap.hana.cloud.samples.springboot.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

/**
 * Created by Shishkov A.V. on 08.06.18.
 */
@Service
public class DefaultClientService implements ClientService {
	@Autowired
	private ClientRepository repository;

	@Override
	public List<Client> findAll() {
		return repository.findAll();
	}

	@Override
	public Client findById(Long id) {
		Optional<Client> result = repository.findById(id);
		return result.isPresent() ? result.get() : null;
	}

	@Override
	public List<Client> findByPage(int page, int size) {
		List<Client> clients = new ArrayList<>();
		StreamSupport.stream(repository.findAll(PageRequest.of(page, size)).spliterator(), false).forEach(c -> clients.add(c));
		return clients;
	}

	@Override
	public Client save(Client client) {
		return repository.save(client);
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