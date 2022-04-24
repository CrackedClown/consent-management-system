package com.cms.portal.healthcare.repository;

import com.cms.portal.healthcare.entity.HealthcareProfessional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HealthcareProfessionalRepository extends JpaRepository<HealthcareProfessional, Long> {

    Optional<HealthcareProfessional> findById(Long id);
}
