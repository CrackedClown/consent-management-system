package com.cms.cm.enums;

import lombok.Getter;

@Getter
public enum ConsentStatus {
    PENDING(0L),
    APPROVED(1L),
    REJECTED(2L),
    DELEGATED(3L);

    private final Long consentStatus;

    ConsentStatus(Long consentStatus) {
        this.consentStatus = consentStatus;
    }
}
