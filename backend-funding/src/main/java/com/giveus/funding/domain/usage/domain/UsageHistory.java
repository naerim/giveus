package com.giveus.funding.domain.usage.domain;

import com.giveus.funding.domain.funding.domain.Funding;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "usage_history")
@Getter
@Setter
@NoArgsConstructor
public class UsageHistory {

    @Id
    @Column(name = "usage_history_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int usageHistoryNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funding_no")
    private Funding funding;

    @Column(name = "category")
    private String category;

    @Column(name = "content")
    private String content;

    @Column(name = "amount")
    private int amount;

    @Column(name = "count")
    private int count;

}
