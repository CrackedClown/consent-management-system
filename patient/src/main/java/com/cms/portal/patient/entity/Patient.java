package com.cms.portal.patient.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "patient")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Patient {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    @JsonIgnore
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "patient_role",
            joinColumns = {
                    @JoinColumn(name = "patient_id")
            }, inverseJoinColumns = {
            @JoinColumn(name = "role_id")
    }
    )
    private Set<Role> role;


}
