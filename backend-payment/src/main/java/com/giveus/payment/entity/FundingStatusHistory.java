package com.giveus.payment.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDateTime;

@Entity
@Table(name = "funding_status_history")
@Getter
@Setter
@DynamicInsert
@NoArgsConstructor
public class FundingStatusHistory {

    @Id
    @Column(name = "funding_status_history_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fundingStatusHistoryNo;

    @ManyToOne
    @JoinColumn(name = "funding_no")
    private Funding funding;

    @Column(name = "status")
    private String status;

}