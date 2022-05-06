package com.cms.portal.patient.service.impl;

import com.cms.portal.patient.request.JwtRequest;
import com.cms.portal.patient.request.outbound.UpdateConsentRequest;
import com.cms.portal.patient.response.inbound.ConsentResponse;
import com.cms.portal.patient.service.ConsentService;
import com.cms.portal.patient.util.PatientConstants;
import com.fasterxml.jackson.databind.JsonNode;
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
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConsentServiceImpl implements ConsentService {

    private final RestTemplate restTemplate;

    @Value("${url.consent.getConsentsByPatientIdUrl}")
    private String getConsentsByPatientIdUrl;

    @Value("${url.consent.update}")
    private String updateConsentUrl;

    @Value("${url.cm.get.token}")
    private String getTokenUrl;

    @Value("${cm.username}")
    private String consentManagerUsername;

    @Value("${cm.password}")
    private String consentManagerPassword;

    private String getToken(){
        JwtRequest request = new JwtRequest();
        request.setUsername(consentManagerUsername);
        request.setPassword(consentManagerPassword);
        JsonNode response = restTemplate.postForObject(getTokenUrl, request, JsonNode.class);
        return (PatientConstants.BEARER.concat(response.get(PatientConstants.JWT_TOKEN).asText()));
    }

    @Override
    public List<ConsentResponse> getConsentsByPatientId(Long patientId) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(PatientConstants.AUTHORIZATION, getToken());
        headers.set(PatientConstants.PATIENT_ID, patientId.toString());
        HttpEntity httpEntity = new HttpEntity(headers);
        ResponseEntity<ConsentResponse[]> responseEntity = restTemplate.exchange(getConsentsByPatientIdUrl, HttpMethod.GET, httpEntity, ConsentResponse[].class);
        return Arrays.asList(Objects.requireNonNull(responseEntity.getBody()));
    }

    @Override
    public ConsentResponse updateConsent(UpdateConsentRequest updateConsentRequest) {
        HttpEntity httpEntity = new HttpEntity(updateConsentRequest);
        return restTemplate.exchange(updateConsentUrl, HttpMethod.PUT, httpEntity, ConsentResponse.class).getBody();
    }
}
