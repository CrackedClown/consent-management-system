package com.cms.portal.healthcare.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "hospital_information")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class HospitalInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long hid;

    @Column(name = "hospital_name", nullable = false)
    private String hospitalName;

    @Column(name = "hospital_address", nullable = false)
    private String hospitalAddress;

    @Column(name = "hospital_db", nullable = false, unique = true)
    private String hospitalDatabaseAddress;

    @Column(name = "hospital_db_username", nullable = false)
    private String hospitalDatabaseUsername;

    @Column(name = "hospital_db_password", nullable = false)
    private String hospitalDatabasePassword;

    @OneToMany(mappedBy = "hospitalInformation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HealthcareProfessional> healthcareProfessionals = new ArrayList<>();

}