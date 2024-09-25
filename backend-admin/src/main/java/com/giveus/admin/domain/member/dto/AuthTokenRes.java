package com.giveus.admin.domain.member.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@Builder
@ToString
public class AuthTokenRes {

    private String accessToken;

    private String refreshToken;

}