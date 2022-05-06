package com.cms.portal.healthcare.controller;

import com.cms.portal.healthcare.request.outbound.CreateConsentRequest;
import com.cms.portal.healthcare.request.outbound.UpdateConsentRequest;
import com.cms.portal.healthcare.response.inbound.ConsentResponse;
import com.cms.portal.healthcare.service.ConsentService;
import com.cms.portal.healthcare.utility.HealthcareConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(HealthcareConstants.API_CONSENT)
@RequiredArgsConstructor
@CrossOrigin
public class ConsentController {

    private final ConsentService consentService;

    @PostMapping
    @PreAuthorize("hasAnyRole('DOCTOR','NURSE')")
    public ResponseEntity<ConsentResponse> createConsent(@RequestBody CreateConsentRequest createConsentRequest){
        return new ResponseEntity<>(consentService.createConsent(createConsentRequest), HttpStatus.CREATED);
    }

    @GetMapping(HealthcareConstants.API_HEALTHCARE)
    @PreAuthorize("hasAnyRole('DOCTOR','NURSE')")
    public ResponseEntity<List<ConsentResponse>> getConsentsByHealthProfessionalId(@RequestHeader(HealthcareConstants.HEALTH_PROFESSIONAL_ID) Long healthProfessionalId){
        List<ConsentResponse> response = consentService.getConsentsByHealthProfessionalId(healthProfessionalId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(HealthcareConstants.API_PATIENT)
    @PreAuthorize("hasAnyRole('DOCTOR','NURSE')")
    public ResponseEntity<List<ConsentResponse>> getConsentsByPatientId(@RequestHeader Long patientId){
        List<ConsentResponse> response = consentService.getConsentsByPatientId(patientId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(HealthcareConstants.API_DELEGATE)
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<ConsentResponse> delegateConsent(@RequestBody UpdateConsentRequest updateConsentRequest){
        ConsentResponse response = consentService.delegateConsent(updateConsentRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
