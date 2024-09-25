package com.giveus.funding.domain.donation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class MemberDonationListRes {

    private String thumbnailUrl;

    private int fundingNo;

    private String title;

    private String status;

    private int targetAmount;

    private int totalAmount;

    private LocalDate startDate;

    private LocalDate endDate;


    private LocalDate birth;

    private int memberFundingNo;

    private int amount;

    private LocalDateTime createdAt;
}
