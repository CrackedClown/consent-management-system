package com.cms.cm.service.impl;

import com.cms.cm.entity.Patient;
import com.cms.cm.repository.PatientRepository;
import com.cms.cm.request.JwtRequest;
import com.cms.cm.request.PatientRegistrationRequest;
import com.cms.cm.request.outbound.AddPatientDetailsRequest;
import com.cms.cm.response.PatientRegistrationResponse;
import com.cms.cm.service.PatientService;
import com.cms.cm.utility.CMConstants;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    private final PasswordEncoder passwordEncoder;

    private final RestTemplate restTemplate;

    @Value("${url.patient.addPatientAsUser}")
    private String addPatientDetailsUrl;

    @Value("${url.patient.get.token}")
    private String getPatientTokenUrl;

    @Value("${services.username}")
    private String username;

    @Value("${services.password}")
    private String password;

    private String getToken(String url){
        JwtRequest request = new JwtRequest();
        request.setUsername(username);
        request.setPassword(password);
        JsonNode response = restTemplate.postForObject(url, request, JsonNode.class);
        return (CMConstants.BEARER.concat(response.get(CMConstants.JWT_TOKEN).asText()));
    }

    @Override
    public PatientRegistrationResponse register(PatientRegistrationRequest request) {

        Patient newPatient = Patient.builder()
                .patientName(request.getPatientName())
                .age(request.getAge())
                .gender(request.getGender())
                .email(request.getEmail())
                .username(request.getGovernmentId())
                .password(passwordEncoder.encode(request.getMobileNum()))
                .governmentId(request.getGovernmentId())
                .fathersName(request.getFathersName())
                .mothersName(request.getMothersName())
                .maritalStatus(request.getMaritalStatus())
                .mobileNum(request.getMobileNum())
                .build();

        newPatient = patientRepository.save(newPatient);

        AddPatientDetailsRequest addPatientDetailsRequest = new AddPatientDetailsRequest();
        addPatientDetailsRequest.setId(newPatient.getId());
        addPatientDetailsRequest.setUsername(newPatient.getUsername());
        addPatientDetailsRequest.setPassword(newPatient.getPassword());

        HttpHeaders headers = new HttpHeaders();
        headers.set(CMConstants.AUTHORIZATION, getToken(getPatientTokenUrl));
        HttpEntity httpEntity = new HttpEntity(addPatientDetailsRequest, headers);
        restTemplate.exchange(addPatientDetailsUrl, HttpMethod.POST, httpEntity, String.class);

        return buildRegistrationResponse(newPatient);

    }

    private PatientRegistrationResponse buildRegistrationResponse(Patient patient){
        return PatientRegistrationResponse.builder()
                .id(patient.getId())
                .patientName(patient.getPatientName())
                .age(patient.getAge())
                .gender(patient.getGender())
                .email(patient.getEmail())
                .username(patient.getUsername())
                .governmentId(patient.getGovernmentId())
                .fathersName(patient.getFathersName())
                .mothersName(patient.getMothersName())
                .maritalStatus(patient.getMaritalStatus())
                .mobileNum(patient.getMobileNum())
                .build();
    }

    @Override
    public PatientRegistrationResponse getPatientDetails(Long patientId) {
        Patient patient = patientRepository.findById(patientId).get();
        return buildRegistrationResponse(patient);
    }
}
