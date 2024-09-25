package com.giveus.payment.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
@Schema(description = "포인트 사용 요청")
public class PointUsageReq {
    @Schema(description = "회원 PK", example = "1")
    private int memberNo;
    @Schema(description = "펀딩 PK", example = "1")
    private int fundingNo;
    @Schema(description = "사용 금액(1포인트 이상)", example = "1000")
    private int amount;
    @Schema(description = "공개 여부", example = "true")
    private boolean opened;

}
