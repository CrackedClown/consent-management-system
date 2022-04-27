package com.cms.portal.healthcare.controller;

import com.cms.portal.healthcare.request.HealthcareProfessionalRegistrationRequest;
import com.cms.portal.healthcare.response.HealthcareProfessionalRegistrationResponse;
import com.cms.portal.healthcare.service.HealthcareProfessionalService;
import com.cms.portal.healthcare.utility.HealthcareConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(HealthcareConstants.API_HEALTHCARE)
@RequiredArgsConstructor
@CrossOrigin
public class HealthcareProfessionalController {

    private final HealthcareProfessionalService healthcareProfessionalService;

    @PostMapping(HealthcareConstants.API_REGISTER)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HealthcareProfessionalRegistrationResponse> register(@RequestBody HealthcareProfessionalRegistrationRequest request){
        HealthcareProfessionalRegistrationResponse response = healthcareProfessionalService.register(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
