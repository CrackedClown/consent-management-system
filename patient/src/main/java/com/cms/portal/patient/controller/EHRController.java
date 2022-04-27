package com.cms.portal.patient.controller;

import com.cms.portal.patient.response.inbound.GetEHRResponse;
import com.cms.portal.patient.service.EHRService;
import com.cms.portal.patient.util.PatientConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(PatientConstants.API_EHR)
@RequiredArgsConstructor
@CrossOrigin
public class EHRController {

    private final EHRService ehrService;

    @GetMapping
    public ResponseEntity<List<GetEHRResponse>> getEHR(@RequestHeader(PatientConstants.FROM_DATE) @DateTimeFormat(pattern = PatientConstants.DATE_TIME_FORMAT) LocalDate fromDate,
                                                       @RequestHeader(PatientConstants.TO_DATE) @DateTimeFormat(pattern = PatientConstants.DATE_TIME_FORMAT) LocalDate toDate,
                                                       @RequestHeader(PatientConstants.PATIENT_ID) Long patientId){
        return new ResponseEntity<>(ehrService.getEHR(patientId, fromDate, toDate), HttpStatus.OK);
    }
}

