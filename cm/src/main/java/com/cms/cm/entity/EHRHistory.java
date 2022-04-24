package com.cms.cm.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "EHR_History")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class EHRHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "hospital_id", nullable = false)
    private Long hid;

    @Column(name = "patient_id", nullable = false)
    private Long patientId;

    @Column(name = "consultation_time", nullable = false)
    private LocalDate consultationTime;

}
