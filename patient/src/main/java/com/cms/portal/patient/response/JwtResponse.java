package com.cms.portal.patient.response;

import com.cms.portal.patient.entity.Patient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtResponse {

    private Patient user;

    private String jwtToken;

}
