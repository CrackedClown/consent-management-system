package com.cms.portal.patient.service.impl;

import com.cms.portal.patient.entity.Patient;
import com.cms.portal.patient.enums.RoleEnum;
import com.cms.portal.patient.repository.PatientRepository;
import com.cms.portal.patient.repository.RoleRepository;
import com.cms.portal.patient.request.AddPatientDetailsRequest;
import com.cms.portal.patient.request.JwtRequest;
import com.cms.portal.patient.response.inbound.PatientDetailsResponse;
import com.cms.portal.patient.service.PatientService;
import com.cms.portal.patient.util.PatientConstants;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    private final RestTemplate restTemplate;

    private final RoleRepository roleRepository;

    @Value("${url.patient.getDetails}")
    private String getPatientDetailsUrl;

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
        return (PatientConstants.BEARER.concat(response.get(PatientConstants.JWT_TOKEN).asText()));
    }

    @Override
    public PatientDetailsResponse getPatientDetails(Long patientId) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(PatientConstants.AUTHORIZATION, getToken());
        headers.set(PatientConstants.PATIENT_ID, patientId.toString());
        HttpEntity httpEntity = new HttpEntity(headers);
        ResponseEntity<PatientDetailsResponse> responseEntity = restTemplate.exchange(getPatientDetailsUrl, HttpMethod.GET, httpEntity, PatientDetailsResponse.class);
        return responseEntity.getBody();
    }

    @Override
    public void addPatientLoginDetails(AddPatientDetailsRequest request) {
        Patient patient = Patient.builder()
                .id(request.getId())
                .username(request.getUsername())
                .password(request.getPassword())
                .role(Set.of(roleRepository.findByName(RoleEnum.valueOf(RoleEnum.PATIENT.name())).get()))
                .build();

        patientRepository.save(patient);
    }
}
