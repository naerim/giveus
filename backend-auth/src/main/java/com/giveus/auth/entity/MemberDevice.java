package com.giveus.auth.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "member_device")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicInsert
public class MemberDevice {

    @Id
    @Column(name = "member_device_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int memberDeviceNo;

    @Column(name = "member_no")
    private int memberNo;

    @Column(name = "device_token")
    private String deviceToken;

    @Column(name = "last_login_at")
    private LocalDateTime lastLoginAt;


    public void updateLastLoginAt() {
        this.lastLoginAt = LocalDateTime.now();
    }
}
