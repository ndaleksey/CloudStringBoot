package com.sap.hana.cloud.samples.springboot.dao;

import com.sap.hana.cloud.samples.springboot.model.check.AnotherCheck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Shishkov A.V. on 29.06.18.
 */
@Repository
public interface CheckRepository extends JpaRepository<AnotherCheck, Long> {
}
