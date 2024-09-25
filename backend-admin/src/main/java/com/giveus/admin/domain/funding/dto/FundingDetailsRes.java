package com.giveus.admin.domain.funding.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class FundingDetailsRes {

    private int fundingNo;

    private String issueNumber;

    private String registrationNumber;

    private String patientName;

    private LocalDate birth;

    private String status;

    private char gender;

    private String diseaseName;

    private String diseaseCode;

    private LocalDate diagnosisDate;

    private String opinion;

    private String title;

    private LocalDate startDate;

    private LocalDate endDate;

    private int totalAmount;

    private int targetAmount;

    private LocalDate createdAt;

    private String phone;

    private Boolean isRegDetail;

    private Boolean isRegReview;

    private Boolean isRegUsage;

    private String content;

    private String thumbnailUrl;
}
