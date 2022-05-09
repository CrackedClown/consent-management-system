package com.cms.cm.controller;

import com.cms.cm.request.ConsentRequest;
import com.cms.cm.request.DelegateConsentRequest;
import com.cms.cm.request.UpdateConsentRequest;
import com.cms.cm.response.ConsentResponse;
import com.cms.cm.service.ConsentService;
import com.cms.cm.utility.CMConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(CMConstants.API_CONSENT)
@RequiredArgsConstructor
@CrossOrigin
public class ConsentController {

    private final ConsentService consentService;

    @PostMapping
    @PreAuthorize("hasRole('HEALTHCARE_PORTAL')")
    public ResponseEntity<ConsentResponse> createConsentRequest(@RequestBody ConsentRequest consentRequest){
        ConsentResponse response = consentService.createConsent(consentRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping(CMConstants.API_HEALTHCARE)
    @PreAuthorize("hasRole('HEALTHCARE_PORTAL')")
    public ResponseEntity<List<ConsentResponse>> getConsentsByHealthProfessionalId(@RequestHeader Long healthProfessionalId){
        List<ConsentResponse> response = consentService.getConsentsByHealthProfessionalId(healthProfessionalId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping (CMConstants.API_DELEGATE)
    @PreAuthorize("hasRole('HEALTHCARE_PORTAL')")
    public ResponseEntity<ConsentResponse> delegateConsent(@RequestBody DelegateConsentRequest delegateConsentRequest){
        ConsentResponse response = consentService.delegateConsent(delegateConsentRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasRole('PATIENT_PORTAL')")
    public ResponseEntity<ConsentResponse> updateConsent(@RequestBody UpdateConsentRequest updateConsentRequest){
        ConsentResponse response = consentService.updateConsentRequest(updateConsentRequest);
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    @GetMapping(CMConstants.API_PATIENT)
    @PreAuthorize("hasRole('PATIENT_PORTAL')")
    public ResponseEntity<List<ConsentResponse>> getConsentsByPatientId(@RequestHeader Long patientId){
        List<ConsentResponse> response = consentService.getConsentsByPatientId(patientId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
