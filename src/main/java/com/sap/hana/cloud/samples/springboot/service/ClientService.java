package com.sap.hana.cloud.samples.springboot.service;

import com.sap.hana.cloud.samples.springboot.model.Client;

import java.util.List;

/**
 * Created by Shishkov A.V. on 08.06.18.
 */
public interface ClientService {
	void saveClient(Client client);
	List<Client> getAllClients();
	void deleteClientById(Long id);
	void deleteClientByIds(Long[] ids);
}
