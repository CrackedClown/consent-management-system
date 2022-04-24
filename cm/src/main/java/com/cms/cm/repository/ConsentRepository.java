package com.cms.cm.repository;

import com.cms.cm.entity.Consent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsentRepository extends JpaRepository<Consent, Long> {

    List<Consent> findByHealthProfessionalId(Long healthProfessionalId);

    List<Consent> findByPatientId(Long patientId);

}
