package com.cms.portal.healthcare.service;

import com.cms.portal.healthcare.request.outbound.CreateConsentRequest;
import com.cms.portal.healthcare.request.outbound.UpdateConsentRequest;
import com.cms.portal.healthcare.response.inbound.ConsentResponse;

import java.util.List;

public interface ConsentService {

    ConsentResponse createConsent(CreateConsentRequest createConsentRequest);

    List<ConsentResponse> getConsentsByHealthProfessionalId(Long healthProfessionalId);

    List<ConsentResponse> getConsentsByPatientId(Long patientId);

    ConsentResponse delegateConsent( UpdateConsentRequest updateConsentRequest);

}
