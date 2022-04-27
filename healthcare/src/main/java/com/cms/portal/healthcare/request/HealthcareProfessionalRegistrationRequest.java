package com.cms.portal.healthcare.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HealthcareProfessionalRegistrationRequest {

    @NotNull
    private Long hid;

    @NotNull
    private String name;

    @NotNull
    private Integer age;

    @NotNull
    private String gender;

    @NotNull
    private String email;

    @NotNull
    private String governmentId;

    @NotNull
    private String degree;

    @NotNull
    private String department;

    @NotNull
    private String mobileNum;

    @NotNull
    private String role;
}
