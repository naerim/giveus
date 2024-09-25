package com.giveus.payment.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "payment")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode(of = {"paymentNo"}, callSuper = false)
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_no")
    private int paymentNo; // 결제 PK, NOT NULL

    @Column(name = "created_at")
    private LocalDateTime createdAt; // 생성일자, NOT NULL

    @Column(name = "method")
    private String method; // 결제수단, NOT NULL

    @Column(name = "amount")
    private int amount; // 금액, NOT NULL

}
