package com.cms.portal.healthcare.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetEHRResponse {

    private Long patientId;

    private String symptoms;

    private String prescription;

    private String remarks;

    private LocalDate consultationTime;

    private Long hospitalId;

    private Long healthcareProfessionalId;

}
