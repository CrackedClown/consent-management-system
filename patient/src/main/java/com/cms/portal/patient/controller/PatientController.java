package com.cms.portal.patient.controller;

import com.cms.portal.patient.request.AddPatientDetailsRequest;
import com.cms.portal.patient.response.inbound.PatientDetailsResponse;
import com.cms.portal.patient.service.PatientService;
import com.cms.portal.patient.util.PatientConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(PatientConstants.API_PATIENT)
@RequiredArgsConstructor
@CrossOrigin
public class PatientController {

    private final PatientService patientService;

    @GetMapping
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<PatientDetailsResponse> getPatientDetails(@RequestHeader(PatientConstants.PATIENT_ID) Long patientId) {
        PatientDetailsResponse response = patientService.getPatientDetails(patientId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('CM','PATIENT')")
    public ResponseEntity<String> addPatientLoginDetails(@RequestBody AddPatientDetailsRequest request) {
        patientService.addPatientLoginDetails(request);
        return new ResponseEntity<>("Patient credentials created", HttpStatus.CREATED);
    }

}
