package com.cms.portal.healthcare.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddEHRResponse {

    @NotNull
    private Long id;

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
