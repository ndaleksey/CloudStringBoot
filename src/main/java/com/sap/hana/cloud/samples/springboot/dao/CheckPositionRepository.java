package com.sap.hana.cloud.samples.springboot.dao;

import com.sap.hana.cloud.samples.springboot.model.check.CheckPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Shishkov A.V. on 03.07.18.
 */
@Repository
public interface CheckPositionRepository extends JpaRepository<CheckPosition, Long> {
}
