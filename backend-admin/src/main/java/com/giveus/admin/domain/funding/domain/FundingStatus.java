package com.giveus.admin.domain.funding.domain;

import lombok.Getter;

@Getter
public enum FundingStatus {
    ONGOING("진행중"),
    COMPLETED("진행완료");

    private final String name;

    FundingStatus(String name) {
        this.name = name;
    }
}
