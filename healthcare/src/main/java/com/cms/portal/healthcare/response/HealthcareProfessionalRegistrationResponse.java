package com.cms.portal.healthcare.response;

import com.cms.portal.healthcare.entity.HospitalInformation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HealthcareProfessionalRegistrationResponse {

    private Long id;

    private String name;

    private Integer age;

    private String gender;

    private String email;

    private String username;

    private String governmentId;

    private String degree;

    private String department;

    private String mobileNum;

    private HospitalInformation hospitalInformation;

    private String role;
}
