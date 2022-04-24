package com.cms.portal.healthcare.service;

import com.cms.portal.healthcare.request.PatientRegistrationRequest;
import com.cms.portal.healthcare.response.PatientRegistrationResponse;

public interface PatientService {

    PatientRegistrationResponse register(PatientRegistrationRequest patientRegistrationRequest);
}
