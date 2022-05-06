package com.cms.portal.patient.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddPatientDetailsRequest {

    Long id;

    String username;

    String password;

}
