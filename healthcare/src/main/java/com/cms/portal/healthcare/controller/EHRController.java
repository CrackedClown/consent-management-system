package com.cms.portal.healthcare.controller;

import com.cms.portal.healthcare.request.AddEHRRequest;
import com.cms.portal.healthcare.response.AddEHRResponse;
import com.cms.portal.healthcare.response.GetEHRResponse;
import com.cms.portal.healthcare.service.EHRService;
import com.cms.portal.healthcare.utility.HealthcareConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(HealthcareConstants.API_EHR)
@RequiredArgsConstructor
@CrossOrigin
public class EHRController {

    private final EHRService ehrService;

    @PostMapping
    @PreAuthorize("hasAnyRole('DOCTOR','NURSE')")
    public ResponseEntity<AddEHRResponse> addEHR(@RequestBody AddEHRRequest addEHRRequest){
        return new ResponseEntity<>(ehrService.addEHR(addEHRRequest), HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('DOCTOR','NURSE')")
    public ResponseEntity<List<GetEHRResponse>> getEHR(@RequestHeader(HealthcareConstants.FROM_DATE) @DateTimeFormat(pattern = HealthcareConstants.DATE_TIME_FORMAT) LocalDate fromDate,
                                                       @RequestHeader(HealthcareConstants.TO_DATE) @DateTimeFormat(pattern = HealthcareConstants.DATE_TIME_FORMAT) LocalDate toDate,
                                                       @RequestHeader(HealthcareConstants.PATIENT_ID) Long patientId){
        return new ResponseEntity<>(ehrService.getEHR(patientId, fromDate, toDate), HttpStatus.OK);
    }
}
