package com.cms.cm.service;

import com.cms.cm.request.PatientRegistrationRequest;
import com.cms.cm.response.PatientRegistrationResponse;

public interface PatientService {

    PatientRegistrationResponse register(PatientRegistrationRequest patientRegistrationRequest);

    PatientRegistrationResponse getPatientDetails(Long patientId);

}
