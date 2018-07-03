package com.sap.hana.cloud.samples.springboot.service;

import com.sap.hana.cloud.samples.springboot.model.Client;

import java.util.List;

/**
 * Created by Shishkov A.V. on 08.06.18.
 */
public interface ClientService {
	Client save(Client client);
	Client findById(Long id);
	List<Client> findAll();
	List<Client> findByPage(int page, int size);
	void delete(Long id);
	void deleteClientByIds(Long[] ids);
}
