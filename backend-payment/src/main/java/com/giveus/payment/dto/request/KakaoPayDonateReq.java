package com.giveus.payment.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;

@Schema(description = "카카오페이 펀딩 후원 요청")
@Getter
@ToString
public class KakaoPayDonateReq {
    @Schema(description = "회원 PK", example = "1")
    private int memberNo;
    @Schema(description = "펀딩 PK", example = "1")
    private int fundingNo;
    @Schema(description = "후원 금액 (카카오페이 결제 준비 요청 시 후원 금액은 최소 1원 이상이어야 함)", example = "1000")
    private int amount;
    @Schema(description = "사용 포인트", example = "0")
    private int point;
    @Schema(description = "펀딩 제목", example = "펀딩 123")
    private String title;
    @Schema(description = "공개 여부", example = "true")
    private boolean opened;
}
