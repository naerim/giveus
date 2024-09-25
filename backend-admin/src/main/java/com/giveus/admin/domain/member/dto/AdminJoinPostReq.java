package com.giveus.admin.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class AdminJoinPostReq {

    private String email;

    private String provider;

    private String name;

    private String ethAddress;

}
