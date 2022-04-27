package com.cms.portal.healthcare.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatientRegistrationResponse {

    private Long id;

    private String patientName;

    private Integer age;

    private String gender;

    private String email;

    private String username;

    private String governmentId;

    private String fathersName;

    private String mothersName;

    private String maritalStatus;

    private String mobileNum;

}
