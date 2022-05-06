package com.cms.portal.healthcare.response;

import com.cms.portal.healthcare.entity.HealthcareProfessional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtResponse {

    private HealthcareProfessional user;

    private String jwtToken;

}
