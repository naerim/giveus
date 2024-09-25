package com.giveus.auth.dto.request;

import lombok.*;

@Getter
@AllArgsConstructor
@ToString
public class AuthJoinPostReq {

    private String email;

    private String provider;

    private String name;

    private String nickname;

}
