package com.cms.cm.service.impl;

import com.cms.cm.entity.Patient;
import com.cms.cm.repository.PatientRepository;
import com.cms.cm.request.PatientRegistrationRequest;
import com.cms.cm.response.PatientRegistrationResponse;
import com.cms.cm.service.PatientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    private final PasswordEncoder passwordEncoder;

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
