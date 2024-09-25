package com.giveus.payment.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Schema(description = "카카오페이 포인트 충전 요청")
public class KakaoPayRechargeReq {
    @Schema(description = "회원 PK", example = "1")
    private int memberNo;
    @Schema(description = "충전 금액 (카카오페이 결제 준비 요청 시 후원 금액은 최소 1원 이상이어야 함)", example = "1000")
    private int amount;

}
