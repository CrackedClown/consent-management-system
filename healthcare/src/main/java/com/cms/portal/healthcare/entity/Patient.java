package com.cms.portal.healthcare.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "patient")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "patient_name", nullable = false)
    private String patientName;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "government_id", nullable = false, unique = true, length = 12)
    private String governmentId;

    @Column(name = "fathers_name")
    private String fathersName;

    @Column(name = "mothers_name")
    private String mothersName;

    @Column(name = "marital_status", nullable = false)
    private String maritalStatus;

    @Column(name = "mobile_num", nullable = false, unique = true, length = 10)
    private String mobileNum;

}