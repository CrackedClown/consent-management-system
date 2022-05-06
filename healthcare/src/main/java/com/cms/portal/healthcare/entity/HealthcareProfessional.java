package com.cms.portal.healthcare.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "healthcare_professional")
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

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = true)
    @JsonIgnore
    private String password;

    @Column(name = "government_id", nullable = false, unique = true, length = 12)
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

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "healthcare_professional_role",
            joinColumns = {
                @JoinColumn(name = "healthcare_professional_id")
            }, inverseJoinColumns = {
                @JoinColumn(name = "role_id")
            }
    )
    private Set<Role> role;
}