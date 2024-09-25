package com.giveus.admin.domain.usage.domain;

import com.giveus.admin.domain.funding.domain.Funding;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usage_history")
@Getter
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

    @Builder
    public UsageHistory(Funding funding, String category, String content, int amount, int count) {
        this.funding = funding;
        this.category = category;
        this.content = content;
        this.amount = amount;
        this.count = count;
    }
}
