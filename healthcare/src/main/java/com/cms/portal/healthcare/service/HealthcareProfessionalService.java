package com.cms.portal.healthcare.service;

import com.cms.portal.healthcare.entity.HealthcareProfessional;
import com.cms.portal.healthcare.request.HealthcareProfessionalRegistrationRequest;
import com.cms.portal.healthcare.response.HealthcareProfessionalRegistrationResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface HealthcareProfessionalService {

    HealthcareProfessionalRegistrationResponse register(HealthcareProfessionalRegistrationRequest request);

    HealthcareProfessional findById(Long id);

    void removeHealthcareProfessional(Long healthcareProfessionalId);

    List<HealthcareProfessionalRegistrationResponse> getHealthcareProfessionalList();

}
