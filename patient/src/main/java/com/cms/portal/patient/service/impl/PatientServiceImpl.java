package com.cms.portal.patient.service.impl;

import com.cms.portal.patient.response.inbound.PatientDetailsResponse;
import com.cms.portal.patient.service.PatientService;
import com.cms.portal.patient.util.PatientConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class PatientServiceImpl implements PatientService {

    private final RestTemplate restTemplate;

    @Value("${url.patient.getDetails}")
    private String getPatientDetailsUrl;

    @Override
    public PatientDetailsResponse getPatientDetails(Long patientId) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(PatientConstants.PATIENT_ID, patientId.toString());
        HttpEntity httpEntity = new HttpEntity(headers);
        ResponseEntity<PatientDetailsResponse> responseEntity = restTemplate.exchange(getPatientDetailsUrl, HttpMethod.GET, httpEntity, PatientDetailsResponse.class);
        return responseEntity.getBody();
    }
}
