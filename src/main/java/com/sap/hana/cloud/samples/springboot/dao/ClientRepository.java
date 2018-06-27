package com.sap.hana.cloud.samples.springboot.dao;

import com.sap.hana.cloud.samples.springboot.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Shishkov A.V. on 08.06.18.
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
