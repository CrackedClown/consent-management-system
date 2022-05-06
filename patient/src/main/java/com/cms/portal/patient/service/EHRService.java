package com.cms.portal.patient.service;

import com.cms.portal.patient.response.inbound.GetEHRResponse;

import java.time.LocalDate;
import java.util.List;

public interface EHRService {

    List<GetEHRResponse> getEHR(Long patientId, LocalDate fromDate, LocalDate toDate);
}
