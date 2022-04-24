package com.cms.portal.healthcare.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "health_professional")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class HealthcareProfessional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "email", unique = true, nullable = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "government_id", nullable = false, unique = true)
    private String governmentId;

    @Column(name = "degree", nullable = false)
    private String degree;

    @Column(name = "department", nullable = false)
    private String department;

    @Column(name = "mobile_num", nullable = false, unique = true, length = 10)
    private String mobileNum;

    @ManyToOne
    @JoinColumn(name = "hid")
    private HospitalInformation hospitalInformation;

    @OneToMany(mappedBy = "healthcareProfessional", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EHR> ehrList = new ArrayList<>();

}