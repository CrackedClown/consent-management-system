package com.cms.cm.service.impl;

import com.cms.cm.entity.EHRHistory;
import com.cms.cm.repository.EHRHistoryRepository;
import com.cms.cm.request.AddEHRHistoryRequest;
import com.cms.cm.request.JwtRequest;
import com.cms.cm.response.AddEHRHistoryResponse;
import com.cms.cm.response.GetEHRResponse;
import com.cms.cm.service.EHRHistoryService;
import com.cms.cm.utility.CMConstants;
import com.cms.cm.utility.FileUtils;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EHRHistoryServiceImpl implements EHRHistoryService {

    private final EHRHistoryRepository ehrHistoryRepository;

    private final FileUtils fileUtils;

    private final RestTemplate restTemplate;

    @Value("${url.healthcare.get.token}")
    private String getHealthcareTokenUrl;

    @Value("${services.username}")
    private String username;

    @Value("${services.password}")
    private String password;

    private String getToken(String url){
        JwtRequest request = new JwtRequest();
        request.setUsername(username);
        request.setPassword(password);
        JsonNode response = restTemplate.postForObject(url, request, JsonNode.class);
        return (CMConstants.BEARER.concat(response.get(CMConstants.JWT_TOKEN).asText()));
    }

    @Override
    public AddEHRHistoryResponse addEHRHistory(AddEHRHistoryRequest addEhrHistoryRequest){
        EHRHistory newHistory = EHRHistory.builder()
                .hid(addEhrHistoryRequest.getHid())
                .patientId(addEhrHistoryRequest.getPatientId())
                .consultationTime(addEhrHistoryRequest.getConsultationTime())
                .build();

        newHistory = ehrHistoryRepository.save(newHistory);

        AddEHRHistoryResponse response = AddEHRHistoryResponse.builder()
                .id(newHistory.getId())
                .hid(newHistory.getHid())
                .patientId(newHistory.getPatientId())
                .consultationTime(newHistory.getConsultationTime())
                .build();

        return response;
    }

    @Override
    public List<GetEHRResponse> getEHR(Long patientId, LocalDate fromDate, LocalDate toDate) {
        List<Long> hospitalIds = ehrHistoryRepository.findDistinctHidByPatientIdAndConsultationTimeBetween(patientId, fromDate, toDate);
        try {
            List<String> apiDataList = fileUtils.getApiData(hospitalIds, CMConstants.API_OUTBOUND_GET_EHR);
            List<GetEHRResponse> response = new ArrayList<>();
            for(String uri: apiDataList){
                HttpHeaders headers = new HttpHeaders();
                headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
                headers.setContentType(MediaType.APPLICATION_JSON);
                headers.set(CMConstants.AUTHORIZATION, getToken(getHealthcareTokenUrl));
                headers.set(CMConstants.PATIENT_ID, patientId.toString());
                headers.set(CMConstants.FROM_DATE, fromDate.toString());
                headers.set(CMConstants.TO_DATE, toDate.toString());
                HttpEntity entity = new HttpEntity(headers);
                ResponseEntity<List<GetEHRResponse>> ehr = restTemplate.exchange(uri, HttpMethod.GET, entity, new ParameterizedTypeReference<List<GetEHRResponse>>(){});
                response.addAll(ehr.getBody());
            }
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
