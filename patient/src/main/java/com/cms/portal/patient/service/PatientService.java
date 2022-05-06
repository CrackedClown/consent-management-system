package com.cms.portal.patient.service;

import com.cms.portal.patient.response.inbound.PatientDetailsResponse;

public interface PatientService {

    PatientDetailsResponse getPatientDetails(Long patientId);
}
