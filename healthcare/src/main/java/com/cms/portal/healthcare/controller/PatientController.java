package com.cms.portal.healthcare.controller;

import com.cms.portal.healthcare.request.PatientRegistrationRequest;
import com.cms.portal.healthcare.response.PatientRegistrationResponse;
import com.cms.portal.healthcare.service.PatientService;
import com.cms.portal.healthcare.utility.HealthcareConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(HealthcareConstants.API_PATIENT)
@RequiredArgsConstructor
@CrossOrigin
public class PatientController {

    private final PatientService patientService;

    @PostMapping(HealthcareConstants.API_REGISTER)
    public ResponseEntity<PatientRegistrationResponse> register(@RequestBody PatientRegistrationRequest patientRegistrationRequest){
        PatientRegistrationResponse response = patientService.register(patientRegistrationRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
