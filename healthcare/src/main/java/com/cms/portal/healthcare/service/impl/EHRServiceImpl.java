package com.cms.portal.healthcare.service.impl;

import com.cms.portal.healthcare.entity.EHR;
import com.cms.portal.healthcare.repository.EHRRepository;
import com.cms.portal.healthcare.request.AddEHRRequest;
import com.cms.portal.healthcare.request.outbound.EHRHistoryRequest;
import com.cms.portal.healthcare.response.AddEHRResponse;
import com.cms.portal.healthcare.response.GetEHRResponse;
import com.cms.portal.healthcare.response.inbound.EHRHistoryResponse;
import com.cms.portal.healthcare.service.EHRService;
import com.cms.portal.healthcare.service.HealthcareProfessionalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EHRServiceImpl implements EHRService {

    private final EHRRepository ehrRepository;

    private final HealthcareProfessionalService healthcareProfessionalService;

    private final RestTemplate restTemplate;

    @Value("${url.ehr.add_history}")
    private String addEHRHistoryUrl;

    @Override
    @Transactional
    public AddEHRResponse addEHR(AddEHRRequest addEHRRequest) {
        EHR newEHR = EHR.builder()
                .patientId(addEHRRequest.getPatientId())
                .symptoms(addEHRRequest.getSymptoms())
                .prescription(addEHRRequest.getPrescription())
                .remarks(addEHRRequest.getRemarks())
                .consultationTime(addEHRRequest.getConsultationTime())
                .healthcareProfessional(healthcareProfessionalService.findById(addEHRRequest.getHealthProfessionalId()))
                .build();

        newEHR = ehrRepository.save(newEHR);
        addEHRHistory(newEHR);

        return AddEHRResponse.builder()
                .id(newEHR.getId())
                .patientId(newEHR.getPatientId())
                .symptoms(newEHR.getSymptoms())
                .prescription(newEHR.getPrescription())
                .remarks(newEHR.getRemarks())
                .consultationTime(newEHR.getConsultationTime())
                .healthProfessionalId(newEHR.getHealthcareProfessional().getId())
                .build();
    }

    @Override
    public void addEHRHistory(EHR ehr) {
        EHRHistoryRequest request = EHRHistoryRequest.builder()
                .hid(ehr.getHealthcareProfessional().getHospitalInformation().getHid())
                .patientId(ehr.getPatientId())
                .consultationTime(ehr.getConsultationTime())
                .build();

        restTemplate.postForObject(addEHRHistoryUrl, request, EHRHistoryResponse.class);
    }

    @Override
    public List<GetEHRResponse> getEHR(Long patientId, LocalDate fromDate, LocalDate toDate) {
        List<EHR> ehrList = ehrRepository.findAllByPatientIdAndConsultationTimeBetween(patientId, fromDate, toDate);
        List<GetEHRResponse> response = new ArrayList<>();
        for(EHR ehr : ehrList){
            GetEHRResponse tempResponse = GetEHRResponse.builder()
                    .patientId(ehr.getPatientId())
                    .symptoms(ehr.getSymptoms())
                    .prescription(ehr.getPrescription())
                    .remarks(ehr.getRemarks())
                    .consultationTime(ehr.getConsultationTime())
                    .hospitalId(ehr.getHealthcareProfessional().getHospitalInformation().getHid())
                    .healthcareProfessionalId(ehr.getHealthcareProfessional().getId())
                    .build();
            response.add(tempResponse);
        }
        return response;
    }
}
