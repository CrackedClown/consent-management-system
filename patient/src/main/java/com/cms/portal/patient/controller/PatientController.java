package com.cms.portal.patient.controller;

import com.cms.portal.patient.response.inbound.PatientDetailsResponse;
import com.cms.portal.patient.service.PatientService;
import com.cms.portal.patient.util.PatientConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(PatientConstants.API_PATIENT)
@RequiredArgsConstructor
@CrossOrigin
public class PatientController {

    private final PatientService patientService;

    @GetMapping
    public ResponseEntity<PatientDetailsResponse> getPatientDetails(@RequestHeader Long patientId) {
        PatientDetailsResponse response = patientService.getPatientDetails(patientId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
