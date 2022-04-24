package com.cms.portal.healthcare.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientRegistrationRequest {

    @NotEmpty
    private Long hid;

    @NotEmpty
    private String patientName;

    @NotEmpty
    private Integer age;

    @NotEmpty
    private String gender;

    @Email
    private String email;

    @NotEmpty
    @Size(min = 12, max = 12)
    private String governmentId;

    private String fathersName;

    private String mothersName;

    @NotEmpty
    private String maritalStatus;

    @NotEmpty
    @Size(min = 10, max = 10)
    private String mobileNum;

}
