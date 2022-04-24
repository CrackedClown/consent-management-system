package com.cms.cm.controller;

import com.cms.cm.request.AddEHRHistoryRequest;
import com.cms.cm.response.AddEHRHistoryResponse;
import com.cms.cm.response.GetEHRResponse;
import com.cms.cm.service.EHRHistoryService;
import com.cms.cm.utility.CMConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(CMConstants.API_EHR)
@RequiredArgsConstructor
@CrossOrigin
public class EHRController {

    private final EHRHistoryService ehrHistoryService;

    @PostMapping(CMConstants.API_HISTORY)
    public ResponseEntity<AddEHRHistoryResponse> addEHRHistory(@RequestBody AddEHRHistoryRequest addEhrHistoryRequest){
        AddEHRHistoryResponse response = ehrHistoryService.addEHRHistory(addEhrHistoryRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<GetEHRResponse>> getEHR(@RequestHeader(CMConstants.FROM_DATE) @DateTimeFormat(pattern = CMConstants.DATE_TIME_FORMAT) LocalDate fromDate,
                                                       @RequestHeader(CMConstants.TO_DATE) @DateTimeFormat(pattern = CMConstants.DATE_TIME_FORMAT) LocalDate toDate,
                                                       @RequestHeader(CMConstants.PATIENT_ID) Long patientId){
        List<GetEHRResponse> response = ehrHistoryService.getEHR(patientId, fromDate, toDate);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
