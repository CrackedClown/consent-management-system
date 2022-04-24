package com.cms.portal.healthcare.service;

import com.cms.portal.healthcare.entity.EHR;
import com.cms.portal.healthcare.request.AddEHRRequest;
import com.cms.portal.healthcare.response.AddEHRResponse;
import com.cms.portal.healthcare.response.GetEHRResponse;

import java.time.LocalDate;
import java.util.List;

public interface EHRService {

    AddEHRResponse addEHR(AddEHRRequest addEHRRequest);

    void addEHRHistory(EHR ehr);

    List<GetEHRResponse> getEHR(Long patientId, LocalDate fromDate, LocalDate toDate);
}
