package com.cms.portal.healthcare.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Entity
@Table(name = "EHR")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class EHR {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "patient_id", nullable = false)
    private Long patientId;

    @Column(name = "symptoms", nullable = false)
    private String symptoms;

    @Column(name = "prescription", nullable = false)
    private String prescription;

    @Column(name = "remarks", nullable = false)
    private String remarks;

    @Column(name = "consultation_time", nullable = false)
    private LocalDate consultationTime;

    @ManyToOne
    @JoinColumn(name = "health_professional_id")
    private HealthcareProfessional healthcareProfessional;

}