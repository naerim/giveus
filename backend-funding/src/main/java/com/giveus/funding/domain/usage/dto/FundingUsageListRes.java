package com.giveus.funding.domain.usage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class FundingUsageListRes {

    private int usageHistoryNo;

    private String category;

    private String content;

    private int count;

    private int amount;
}
