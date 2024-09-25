package com.giveus.admin.domain.member.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicInsert
@DynamicUpdate
@Table(name = "admin")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_no")
    private int adminNo;

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "provider")
    private String provider;

    @Column(name = "sns_key")
    private String key;

    @Column(name = "eth_address")
    private String ethAddress;

    public void updateName(String name) {
        this.name = name;
    }

    public void updateEthAddress(String ethAddress) {
        this.ethAddress = ethAddress;
    }

}
