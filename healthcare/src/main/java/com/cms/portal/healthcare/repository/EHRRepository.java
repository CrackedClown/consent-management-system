package com.cms.portal.healthcare.repository;

import com.cms.portal.healthcare.entity.EHR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EHRRepository extends JpaRepository<EHR, Long> {

    List<EHR> findAllByPatientIdAndConsultationTimeBetween(Long patientId, LocalDate fromDate, LocalDate toDate);

}
