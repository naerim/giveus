package com.giveus.payment.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "funding")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode(of = {"fundingNo"}, callSuper = false)
public class Funding {

    @Id
    @Column(name = "funding_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fundingNo;

    @Column(name = "issue_number")
    private String issueNumber;

    @Column(name = "registration_number")
    private String registrationNumber;

    @Column(name = "patient_name")
    private String patientName;

    @Column(name = "birth")
    private LocalDate birth;

    @Column(name = "gender")
    private char gender;

    @Column(name = "disease_name")
    private String diseaseName;

    @Column(name = "disease_code")
    private String diseaseCode;

    @Column(name = "diagnosis_date")
    private LocalDate diagnosisDate;

    @Column(name="opinion")
    private String opinion;

    @Column(name="title")
    private String title;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "target_amount")
    private int targetAmount;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name="phone")
    private String phone;

    @Column(name = "reg_id")
    private String regId;

}
