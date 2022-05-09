package com.cms.cm.request;

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
public class DelegateConsentRequest {

    @NotNull
    private Long id;

    @NotNull
    private Long healthProfessionalId;

    @NotNull
    private LocalDate validUpto;

    @NotNull
    private String consentStatus;

}