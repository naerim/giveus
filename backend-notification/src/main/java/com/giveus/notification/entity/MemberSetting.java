package com.giveus.notification.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@AllArgsConstructor
@Builder
@DynamicInsert
@DynamicUpdate
@Table(name = "member_setting")
public class MemberSetting {

    @Id
    @Column(name = "member_setting_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int memberSettingNo;

    @OneToOne
    @JoinColumn(name = "member_no")
    @Setter
    private Member member;

    @ColumnDefault("true")
    @Column(name = "funding_review")
    private boolean fundingReview;

    @ColumnDefault("true")
    @Column(name = "usage_history")
    private boolean usageHistory;

    @ColumnDefault("true")
    @Column(name = "regular_payment")
    private boolean regularPayment;

    @ColumnDefault("true")
    @Column(name = "recommend_funding")
    private boolean recommendFunding;

    public MemberSetting() {
        this.fundingReview = true;
        this.usageHistory = true;
        this.regularPayment = true;
        this.recommendFunding = true;
    }

    public void changeUsageHistory() {
        this.usageHistory = !this.isUsageHistory();
    }

    public void changeFundingReview() {
        this.fundingReview = !this.isFundingReview();
    }

}
