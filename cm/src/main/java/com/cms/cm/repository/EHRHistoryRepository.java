package com.cms.cm.repository;

import com.cms.cm.entity.EHRHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EHRHistoryRepository extends JpaRepository<EHRHistory, Long> {

    @Query("Select DISTINCT(e.hid) from EHRHistory e where e.patientId = ?1 and e.consultationTime BETWEEN ?2 and ?3")
    public List<Long> findDistinctHidByPatientIdAndConsultationTimeBetween(Long patientId, LocalDate fromDate, LocalDate toDate);
}
