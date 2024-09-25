package com.giveus.payment.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "member_funding")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode(of = {"memberFundingNo"}, callSuper = false)
public class MemberFunding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_funding_no")
    private int memberFundingNo;

    @Column(name = "member_no")
    private int memberNo;

    @Column(name = "funding_no")
    private int fundingNo;

    @Column(name = "payment_no")
    private Integer paymentNo;

    @Column(name = "point_no")
    private Integer pointNo;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "amount")
    private int amount;

    @Column(name = "is_public")
    private boolean isPublic;
}
