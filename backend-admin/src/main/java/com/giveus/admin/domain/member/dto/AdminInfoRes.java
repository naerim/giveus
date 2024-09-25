package com.giveus.admin.domain.member.dto;

import com.giveus.admin.domain.member.domain.Admin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
@ToString
public class AdminInfoRes {

    private int adminNo;

    private String email;

    private String name;

    private LocalDateTime createdAt;

    private String provider;

    private String snsKey;

    private String ethAddress;

    public static AdminInfoRes from(Admin admin) {
        return AdminInfoRes.builder()
                .adminNo(admin.getAdminNo())
                .email(admin.getEmail())
                .name(admin.getName())
                .createdAt(admin.getCreatedAt())
                .provider(admin.getProvider())
                .snsKey(admin.getKey())
                .ethAddress(admin.getEthAddress())
                .build();
    }

}
