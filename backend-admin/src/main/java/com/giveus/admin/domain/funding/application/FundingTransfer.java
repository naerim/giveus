package com.giveus.admin.domain.funding.application;

import com.giveus.admin.domain.funding.dto.FundingCreateReq;
import com.giveus.admin.domain.funding.domain.Funding;

public class FundingTransfer {
    public static Funding dtoToEntity(FundingCreateReq req) {
        String phone = req.getPhone();
        phone = phone.replace("-", "");
        return Funding.builder()
                .issueNumber(req.getIssueNumber())
                .birth(req.getBirth())
                .diagnosisDate(req.getDiagnosisDate())
                .diseaseCode(req.getDiseaseCode())
                .phone(phone)
                .diseaseName(req.getDiseaseName())
                .endDate(req.getEndDate())
                .gender(req.getGender())
                .title(req.getTitle())
                .opinion(req.getOpinion())
                .patientName(req.getPatientName())
                .registrationNumber(req.getRegistrationNumber())
                .startDate(req.getStartDate())
                .targetAmount(req.getTargetAmount())
                .adminNo(req.getAdminNo())
                .build();

    }

}
