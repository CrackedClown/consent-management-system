package com.cms.cm.controller;

import com.cms.cm.request.PatientRegistrationRequest;
import com.cms.cm.response.PatientRegistrationResponse;
import com.cms.cm.service.PatientService;
import com.cms.cm.utility.CMConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CMConstants.API_PATIENT)
@RequiredArgsConstructor
@CrossOrigin
public class PatientController {

    private final PatientService patientService;

    @PostMapping(CMConstants.API_REGISTER)
    public ResponseEntity<PatientRegistrationResponse> register(@RequestBody PatientRegistrationRequest patientRegistrationRequest){
        PatientRegistrationResponse response = patientService.register(patientRegistrationRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<PatientRegistrationResponse> getPatientDetails(@RequestHeader Long patientId){
        PatientRegistrationResponse response = patientService.getPatientDetails(patientId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
