package com.cms.cm.request.outbound;

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
