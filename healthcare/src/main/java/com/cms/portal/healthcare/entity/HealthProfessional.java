package com.cms.portal.healthcare.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "health_professional")
public class HealthProfessional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "government_id", nullable = false, unique = true)
    private String governmentId;

    @Column(name = "degree", nullable = false)
    private String degree;

    @Column(name = "department")
    private String department;

    @Column(name = "mobile_num", nullable = false, unique = true, length = 10)
    private String mobileNum;

    @ManyToOne
    @JoinColumn(name = "hid")
    private HospitalInformation hospitalInformation;

    @OneToMany(mappedBy = "healthProfessional", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Consultation> consultations = new ArrayList<>();

}