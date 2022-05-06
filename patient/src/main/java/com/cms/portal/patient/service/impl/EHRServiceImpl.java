package com.cms.portal.patient.service.impl;

import com.cms.portal.patient.request.JwtRequest;
import com.cms.portal.patient.response.inbound.GetEHRResponse;
import com.cms.portal.patient.service.EHRService;
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

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EHRServiceImpl implements EHRService {

    private final RestTemplate restTemplate;

    @Value("${url.ehr.get}")
    private String getEhrUrl;

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
    public List<GetEHRResponse> getEHR(Long patientId, LocalDate fromDate, LocalDate toDate) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(PatientConstants.AUTHORIZATION, getToken());
        headers.set(PatientConstants.PATIENT_ID, patientId.toString());
        headers.set(PatientConstants.FROM_DATE, fromDate.toString());
        headers.set(PatientConstants.TO_DATE, toDate.toString());
        HttpEntity httpEntity = new HttpEntity(headers);
        ResponseEntity<GetEHRResponse[]> responseEntity = restTemplate.exchange(getEhrUrl, HttpMethod.GET, httpEntity, GetEHRResponse[].class);
        return Arrays.asList(responseEntity.getBody());
    }

}
