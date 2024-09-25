package com.giveus.admin.domain.usage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class FundingUsageCreateReq {

    private int fundingNo;

    private String category;

    private String content;

    private int amount;

    private int count;
}
