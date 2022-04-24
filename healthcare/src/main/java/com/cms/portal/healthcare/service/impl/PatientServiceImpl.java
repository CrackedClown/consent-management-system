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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
@CrossOrigin
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    private final RestTemplate restTemplate;

    @Value("${url.patient.registration}")
    private String patientRegistrationUrl;

    @Override
    @Transactional
    public PatientRegistrationResponse register(PatientRegistrationRequest request) {

         Patient newPatient = Patient.builder()
                .patientName(request.getPatientName())
                .age(request.getAge())
                .gender(request.getGender())
                .governmentId(request.getGovernmentId())
                .maritalStatus(request.getMaritalStatus())
                .mobileNum(request.getMobileNum())
                .build();

        if(null != request.getEmail()){
            newPatient.setEmail(request.getEmail());
        }
        if(null != request.getFathersName()){
            newPatient.setFathersName(request.getFathersName());
        }
        if(null != request.getMothersName()){
            newPatient.setMothersName(request.getMothersName());
        }

        patientRepository.save(newPatient);

        return restTemplate.postForObject(patientRegistrationUrl, request, PatientRegistrationResponse.class);
    }
}
