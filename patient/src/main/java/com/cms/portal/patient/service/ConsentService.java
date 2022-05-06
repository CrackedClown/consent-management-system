package com.cms.portal.patient.service;

import com.cms.portal.patient.request.outbound.UpdateConsentRequest;
import com.cms.portal.patient.response.inbound.ConsentResponse;

import java.util.List;

public interface ConsentService {

    List<ConsentResponse> getConsentsByPatientId(Long patientId);

    ConsentResponse updateConsent(UpdateConsentRequest updateConsentRequest);
}
