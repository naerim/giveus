package com.giveus.funding.domain.funding.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class FundingListRes {

    private String thumbnailUrl;

    private int fundingNo;

    private String title;

    private String status;

    private int targetAmount;

    private int totalAmount;

    private LocalDate startDate;

    private LocalDate endDate;

    private LocalDate createdAt;

    private LocalDate birth;

    private long donationCnt;
}
