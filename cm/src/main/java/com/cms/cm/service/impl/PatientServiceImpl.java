package com.cms.cm.service.impl;

import com.cms.cm.entity.Patient;
import com.cms.cm.repository.PatientRepository;
import com.cms.cm.request.PatientRegistrationRequest;
import com.cms.cm.response.PatientRegistrationResponse;
import com.cms.cm.service.PatientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    @Override
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

        newPatient = patientRepository.save(newPatient);

        PatientRegistrationResponse response = PatientRegistrationResponse.builder()
                .id(newPatient.getId())
                .patientName(newPatient.getPatientName())
                .age(newPatient.getAge())
                .gender(newPatient.getGender())
                .email(newPatient.getEmail())
                .governmentId(newPatient.getGovernmentId())
                .fathersName(newPatient.getFathersName())
                .mothersName(newPatient.getMothersName())
                .maritalStatus(newPatient.getMaritalStatus())
                .mobileNum(newPatient.getMobileNum())
                .build();

        return response;
    }
}
