package com.cms.portal.healthcare.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddEHRRequest {

    @NotNull
    private Long patientId;

    @NotEmpty
    private String symptoms;

    @NotEmpty
    private String prescription;

    @NotBlank
    private String remarks;

    @NotNull
    private LocalDate consultationTime;

    @NotNull
    private Long healthProfessionalId;
}
