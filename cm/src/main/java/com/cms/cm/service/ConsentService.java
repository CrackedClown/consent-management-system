package com.cms.cm.service;

import com.cms.cm.entity.Consent;
import com.cms.cm.request.ConsentRequest;
import com.cms.cm.request.DelegateConsentRequest;
import com.cms.cm.request.UpdateConsentRequest;
import com.cms.cm.response.ConsentResponse;

import java.util.List;

public interface ConsentService {

    ConsentResponse createConsent(ConsentRequest consentRequest);

    ConsentResponse buildConsentResponse(Consent consent);

    List<ConsentResponse> getConsentsByHealthProfessionalId(Long healthProfessionalId);

    ConsentResponse updateConsentRequest(UpdateConsentRequest updateConsentRequest);

    List<ConsentResponse> getConsentsByPatientId(Long patientId);

    ConsentResponse delegateConsent(DelegateConsentRequest delegateConsentRequest);

}
