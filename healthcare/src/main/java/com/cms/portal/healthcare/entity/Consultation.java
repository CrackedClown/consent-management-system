package com.cms.portal.healthcare.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "consultation")
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "consultation_id", nullable = false)
    private Long consultationId;

    @Column(name = "patient_id", nullable = false)
    private Long patientId;

    @Column(name = "symptoms", nullable = false)
    private String symptoms;

    @Column(name = "prescription", nullable = false)
    private String prescription;

    @Column(name = "remarks", nullable = false)
    private String remarks;

    @Column(name = "consultation_time", nullable = false)
    private LocalDateTime consultationTime;

    @ManyToOne
    @JoinColumn(name = "health_professional_id")
    private HealthProfessional healthProfessional;

}