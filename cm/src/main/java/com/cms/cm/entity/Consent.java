package com.cms.cm.entity;

import com.cms.cm.enums.ConsentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "consent")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Consent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "hospital_id", nullable = false)
    private Long hid;

    @Column(name = "health_professional_id", nullable = false)
    private Long healthProfessionalId;

    @Column(name = "patient_id", nullable = false)
    private Long patientId;

    @Column(name = "from_date", nullable = false)
    private LocalDate fromDate;

    @Column(name = "to_date", nullable = false)
    private LocalDate toDate;

    @Column(name = "valid_upto", nullable = false)
    private LocalDate validUpto;

    @Column(name = "remarks", nullable = false)
    private String remarks;

    @Enumerated(EnumType.ORDINAL)
    private ConsentStatus status;

}
