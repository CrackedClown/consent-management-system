package com.cms.portal.healthcare.request.outbound;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class CreateConsentRequest {

    @NotNull
    private Long hid;

    @NotNull
    private Long healthProfessionalId;

    @NotNull
    private Long patientId;

    @NotNull
    private LocalDate fromDate;

    @NotNull
    private LocalDate toDate;

    @NotNull
    private LocalDate validUpto;

}
