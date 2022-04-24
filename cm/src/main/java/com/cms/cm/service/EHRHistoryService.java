package com.cms.cm.service;

import com.cms.cm.request.AddEHRHistoryRequest;
import com.cms.cm.response.AddEHRHistoryResponse;
import com.cms.cm.response.GetEHRResponse;

import java.time.LocalDate;
import java.util.List;

public interface EHRHistoryService {

    AddEHRHistoryResponse addEHRHistory(AddEHRHistoryRequest addEhrHistoryRequest);

    List<GetEHRResponse> getEHR(Long patientId, LocalDate fromDate, LocalDate toDate);

}
