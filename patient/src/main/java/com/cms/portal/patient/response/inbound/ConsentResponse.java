package com.cms.portal.patient.response.inbound;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConsentResponse {

    @NotNull
    private Long id;

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
    private String remarks;

    @NotNull
    private LocalDate validUpto;

    @NotNull
    private String status;

}
