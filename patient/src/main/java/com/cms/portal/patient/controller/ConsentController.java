package com.cms.portal.patient.controller;

import com.cms.portal.patient.request.outbound.UpdateConsentRequest;
import com.cms.portal.patient.response.inbound.ConsentResponse;
import com.cms.portal.patient.service.ConsentService;
import com.cms.portal.patient.util.PatientConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(PatientConstants.API_CONSENT)
@RequiredArgsConstructor
@CrossOrigin
public class ConsentController {

    private final ConsentService consentService;

    @GetMapping
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<List<ConsentResponse>> getConsentsByPatientId(@RequestHeader Long patientId){
        List<ConsentResponse> response = consentService.getConsentsByPatientId(patientId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<ConsentResponse> updateConsent(@RequestBody UpdateConsentRequest updateConsentRequest){
        ConsentResponse response = consentService.updateConsent(updateConsentRequest);
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}
