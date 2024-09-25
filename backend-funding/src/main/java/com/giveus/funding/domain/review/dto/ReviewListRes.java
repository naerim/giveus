package com.giveus.funding.domain.review.dto;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ReviewListRes {

    private int reviewNo;

    private int fundingNo;

    private String title;

    private int targetAmount;

    private Long memberFundingCount;

    private boolean existUsageHistory;

    private String imageUrl;

}
