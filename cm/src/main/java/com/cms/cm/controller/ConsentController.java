package com.cms.cm.controller;

import com.cms.cm.request.ConsentRequest;
import com.cms.cm.request.UpdateConsentRequest;
import com.cms.cm.response.ConsentResponse;
import com.cms.cm.service.ConsentService;
import com.cms.cm.utility.CMConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(CMConstants.API_CONSENT)
@RequiredArgsConstructor
@CrossOrigin
public class ConsentController {

    private final ConsentService consentService;

    @PostMapping
    public ResponseEntity<ConsentResponse> createConsentRequest(@RequestBody ConsentRequest consentRequest){
        ConsentResponse response = consentService.createConsent(consentRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping(CMConstants.API_HEALTHCARE)
    public ResponseEntity<List<ConsentResponse>> getConsentsByHealthProfessionalId(@RequestHeader Long healthProfessionalId){
        List<ConsentResponse> response = consentService.getConsentsByHealthProfessionalId(healthProfessionalId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ConsentResponse> updateConsent(@RequestBody UpdateConsentRequest updateConsentRequest){
        ConsentResponse response = consentService.updateConsentRequest(updateConsentRequest);
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    @GetMapping(CMConstants.API_PATIENT)
    public ResponseEntity<List<ConsentResponse>> getConsentsByPatientId(@RequestHeader Long patientId){
        List<ConsentResponse> response = consentService.getConsentsByPatientId(patientId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping (CMConstants.API_DELEGATE)
    public ResponseEntity<ConsentResponse> delegateConsent(@RequestBody UpdateConsentRequest updateConsentRequest){
        ConsentResponse response = consentService.delegateConsent(updateConsentRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
