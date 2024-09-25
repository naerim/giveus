package com.giveus.auth.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@ToString
public class MemberDevicePostReq {

    private int memberNo;

    private String deviceToken;

}
