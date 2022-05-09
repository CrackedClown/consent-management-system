package com.cms.cm.service.impl;

import com.cms.cm.entity.Consent;
import com.cms.cm.enums.ConsentStatus;
import com.cms.cm.repository.ConsentRepository;
import com.cms.cm.request.ConsentRequest;
import com.cms.cm.request.DelegateConsentRequest;
import com.cms.cm.request.UpdateConsentRequest;
import com.cms.cm.response.ConsentResponse;
import com.cms.cm.service.ConsentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConsentServiceImpl implements ConsentService {

    private final ConsentRepository consentRepository;

    @Override
    public ConsentResponse createConsent(ConsentRequest consentRequest) {

        Consent consent = Consent.builder()
                .hid(consentRequest.getHid())
                .healthProfessionalId(consentRequest.getHealthProfessionalId())
                .patientId(consentRequest.getPatientId())
                .fromDate(consentRequest.getFromDate())
                .toDate(consentRequest.getToDate())
                .remarks(consentRequest.getRemarks())
                .validUpto(consentRequest.getValidUpto())
                .status(ConsentStatus.PENDING)
                .build();

        consent = consentRepository.save(consent);

        return buildConsentResponse(consent);
    }

    public ConsentResponse buildConsentResponse(Consent consent){

        return ConsentResponse.builder()
                .id(consent.getId())
                .hid(consent.getHid())
                .healthProfessionalId(consent.getHealthProfessionalId())
                .patientId(consent.getPatientId())
                .fromDate(consent.getFromDate())
                .toDate(consent.getToDate())
                .remarks(consent.getRemarks())
                .validUpto(consent.getValidUpto())
                .status(consent.getStatus().toString())
                .build();
    }

    @Override
    public List<ConsentResponse> getConsentsByHealthProfessionalId(Long healthProfessionalId) {
        List<Consent> consentList = consentRepository.findByHealthProfessionalId(healthProfessionalId);
        List<ConsentResponse> response = new ArrayList<>();
        for(Consent consent: consentList){
            response.add(buildConsentResponse(consent));
        }
        return response;
    }

    @Override
    public ConsentResponse updateConsentRequest(UpdateConsentRequest updateConsentRequest) {
        Consent consent = consentRepository.getById(updateConsentRequest.getId());
        consent.setValidUpto(updateConsentRequest.getValidUpto());
        consent.setStatus(ConsentStatus.valueOf(updateConsentRequest.getConsentStatus()));
        consent = consentRepository.save(consent);
        return buildConsentResponse(consent);
    }

    @Override
    public List<ConsentResponse> getConsentsByPatientId(Long patientId) {
        List<Consent> consentList = consentRepository.findByPatientId(patientId);
        List<ConsentResponse> response = new ArrayList<>();
        for(Consent consent: consentList){
            response.add(buildConsentResponse(consent));
        }
        return response;
    }

    @Override
    public ConsentResponse delegateConsent(DelegateConsentRequest delegateConsentRequest) {
        Consent consent = consentRepository.findById(delegateConsentRequest.getId()).get();
        Consent delegatedConsent = Consent.builder()
                .hid(consent.getHid())
                .healthProfessionalId(delegateConsentRequest.getHealthProfessionalId())
                .patientId(consent.getPatientId())
                .fromDate(consent.getFromDate())
                .toDate(consent.getToDate())
                .remarks(consent.getRemarks())
                .validUpto(consent.getValidUpto())
                .validUpto(delegateConsentRequest.getValidUpto())
                .status(ConsentStatus.DELEGATED)
                .build();
        delegatedConsent = consentRepository.save(delegatedConsent);
        return buildConsentResponse(delegatedConsent);
    }
}
