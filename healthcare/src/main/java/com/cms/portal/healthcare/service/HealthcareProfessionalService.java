package com.cms.portal.healthcare.service;

import com.cms.portal.healthcare.entity.HealthcareProfessional;
import com.cms.portal.healthcare.request.HealthcareProfessionalRegistrationRequest;
import com.cms.portal.healthcare.response.HealthcareProfessionalRegistrationResponse;

public interface HealthcareProfessionalService {

    HealthcareProfessionalRegistrationResponse register(HealthcareProfessionalRegistrationRequest request);

    HealthcareProfessional findById(Long id);

}
