package com.cms.portal.patient.service.impl;

import com.cms.portal.patient.response.inbound.GetEHRResponse;
import com.cms.portal.patient.service.EHRService;
import com.cms.portal.patient.util.PatientConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EHRServiceImpl implements EHRService {

    private final RestTemplate restTemplate;

    @Value("${url.ehr.get}")
    private String getEhrUrl;

    @Override
    public List<GetEHRResponse> getEHR(Long patientId, LocalDate fromDate, LocalDate toDate) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(PatientConstants.PATIENT_ID, patientId.toString());
        headers.set(PatientConstants.FROM_DATE, fromDate.toString());
        headers.set(PatientConstants.TO_DATE, toDate.toString());
        HttpEntity httpEntity = new HttpEntity(headers);
        ResponseEntity<GetEHRResponse[]> responseEntity = restTemplate.exchange(getEhrUrl, HttpMethod.GET, httpEntity, GetEHRResponse[].class);
        return Arrays.asList(responseEntity.getBody());
    }

}
