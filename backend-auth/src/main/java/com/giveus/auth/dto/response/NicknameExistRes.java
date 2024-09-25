package com.giveus.auth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@Builder
@ToString
public class NicknameExistRes {

    private String nickname;

    private boolean exist;

}
