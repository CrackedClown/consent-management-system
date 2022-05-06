package com.cms.portal.healthcare.service.impl;

import com.cms.portal.healthcare.entity.Patient;
import com.cms.portal.healthcare.repository.PatientRepository;
import com.cms.portal.healthcare.request.PatientRegistrationRequest;
import com.cms.portal.healthcare.response.PatientRegistrationResponse;
import com.cms.portal.healthcare.service.PatientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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

    @Override
    @Transactional
    public PatientRegistrationResponse register(PatientRegistrationRequest request) {

        PatientRegistrationResponse response = restTemplate.postForObject(patientRegistrationUrl, request, PatientRegistrationResponse.class);

        Patient newPatient = Patient.builder()
                .patientName(response.getPatientName())
                .age(response.getAge())
                .gender(response.getGender())
                .governmentId(response.getGovernmentId())
                .maritalStatus(response.getMaritalStatus())
                .mobileNum(response.getMobileNum())
                .build();

        if (null != response.getEmail()) {
            newPatient.setEmail(response.getEmail());
        }
        if (null != response.getFathersName()) {
            newPatient.setFathersName(response.getFathersName());
        }
        if (null != response.getMothersName()) {
            newPatient.setMothersName(response.getMothersName());
        }

        patientRepository.save(newPatient);

        return response;
    }
}
