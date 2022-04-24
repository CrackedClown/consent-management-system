package com.cms.cm.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class AddEHRHistoryRequest {

    private Long hid;

    private Long patientId;

    private LocalDate consultationTime;

}
