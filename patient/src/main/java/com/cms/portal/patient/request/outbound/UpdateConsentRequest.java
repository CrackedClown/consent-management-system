package com.cms.portal.patient.request.outbound;

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
public class UpdateConsentRequest {
    
    @NotNull
    private Long id;

    @NotNull
    private LocalDate validUpto;

    @NotNull
    private String consentStatus;

}
