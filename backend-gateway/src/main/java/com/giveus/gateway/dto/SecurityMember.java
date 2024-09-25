package com.giveus.gateway.dto;

import lombok.*;

@NoArgsConstructor
@Getter
@ToString
@AllArgsConstructor
@Builder
public class SecurityMember {

    private String provider;
    private String snsKey;

}
