package com.cms.portal.healthcare.repository;

import com.cms.portal.healthcare.entity.HospitalInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalInformationRepository extends JpaRepository<HospitalInformation, Long> {
}
