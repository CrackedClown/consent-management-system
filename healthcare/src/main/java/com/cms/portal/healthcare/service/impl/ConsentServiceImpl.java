package com.cms.portal.healthcare.service.impl;

import com.cms.portal.healthcare.request.outbound.CreateConsentRequest;
import com.cms.portal.healthcare.request.outbound.UpdateConsentRequest;
import com.cms.portal.healthcare.response.inbound.ConsentResponse;
import com.cms.portal.healthcare.service.ConsentService;
import com.cms.portal.healthcare.utility.HealthcareConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConsentServiceImpl implements ConsentService {

    private final RestTemplate restTemplate;

    @Value("${url.consent.create}")
    private String createConsentUrl;

    @Value("${url.consent.getConsentsByHealthProfessionalId}")
    private String getConsentsByHealthProfessionalIdUrl;

    @Value("${url.consent.getConsentsByPatientIdUrl}")
    private String getConsentsByPatientIdUrl;

    @Value("${url.consent.delegateConsentUrl}")
    private String delegateConsentUrl;

    @Override
    public ConsentResponse createConsent(CreateConsentRequest request){
        return restTemplate.postForObject(createConsentUrl, request, ConsentResponse.class);
    }

    @Override
    public List<ConsentResponse> getConsentsByHealthProfessionalId(Long healthProfessionalId) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HealthcareConstants.HEALTH_PROFESSIONAL_ID, healthProfessionalId.toString());
        HttpEntity httpEntity = new HttpEntity(headers);
        ResponseEntity<ConsentResponse[]> responseEntity = restTemplate.exchange(getConsentsByHealthProfessionalIdUrl, HttpMethod.GET, httpEntity, ConsentResponse[].class);
        return Arrays.asList(responseEntity.getBody());
    }

    @Override
    public List<ConsentResponse> getConsentsByPatientId(Long patientId) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HealthcareConstants.PATIENT_ID, patientId.toString());
        HttpEntity httpEntity = new HttpEntity(headers);
        ResponseEntity<ConsentResponse[]> responseEntity = restTemplate.exchange(getConsentsByPatientIdUrl, HttpMethod.GET, httpEntity, ConsentResponse[].class);
        return Arrays.asList(responseEntity.getBody());
    }

    @Override
    public ConsentResponse delegateConsent(UpdateConsentRequest updateConsentRequest) {
        HttpEntity httpEntity = new HttpEntity(updateConsentRequest);
        return restTemplate.exchange(delegateConsentUrl, HttpMethod.PUT, httpEntity, ConsentResponse.class).getBody();
    }

}
