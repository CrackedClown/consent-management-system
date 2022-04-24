package com.cms.cm.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class AddEHRHistoryResponse {

    private Long id;

    private Long hid;

    private Long patientId;

    private LocalDate consultationTime;

}
