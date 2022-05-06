package com.cms.portal.healthcare.service.impl;

import com.cms.portal.healthcare.entity.Patient;
import com.cms.portal.healthcare.repository.PatientRepository;
import com.cms.portal.healthcare.request.JwtRequest;
import com.cms.portal.healthcare.request.PatientRegistrationRequest;
import com.cms.portal.healthcare.response.PatientRegistrationResponse;
import com.cms.portal.healthcare.response.inbound.ConsentResponse;
import com.cms.portal.healthcare.service.PatientService;
import com.cms.portal.healthcare.utility.HealthcareConstants;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    private final RestTemplate restTemplate;

    @Value("${url.patient.registration}")
    private String patientRegistrationUrl;

    @Value("${url.cm.get.token}")
    private String getTokenUrl;

    @Value("${cm.username}")
    private String consentManagerUsername;

    @Value("${cm.password}")
    private String consentManagerPassword;

    private String getToken(){
        JwtRequest request = new JwtRequest();
        request.setUsername(consentManagerUsername);
        request.setPassword(consentManagerPassword);
        JsonNode response = restTemplate.postForObject(getTokenUrl, request, JsonNode.class);
        return (HealthcareConstants.BEARER.concat(response.get(HealthcareConstants.JWT_TOKEN).asText()));
    }

    @Override
    @Transactional
    public PatientRegistrationResponse register(PatientRegistrationRequest request) {

        HttpHeaders headers = new HttpHeaders();
        headers.set(HealthcareConstants.AUTHORIZATION, getToken());
        HttpEntity httpEntity = new HttpEntity(request, headers);
        ResponseEntity<PatientRegistrationResponse> responseEntity = restTemplate.exchange(patientRegistrationUrl, HttpMethod.POST, httpEntity, PatientRegistrationResponse.class);

        PatientRegistrationResponse response = responseEntity.getBody();

        Patient newPatient = Patient.builder()
                .id(response.getId())
                .patientName(response.getPatientName())
                .age(response.getAge())
                .gender(response.getGender())
                .email(response.getEmail())
                .governmentId(response.getGovernmentId())
                .fathersName(response.getFathersName())
                .mothersName(response.getMothersName())
                .maritalStatus(response.getMaritalStatus())
                .mobileNum(response.getMobileNum())
                .build();

        patientRepository.save(newPatient);

        return response;
    }
}
