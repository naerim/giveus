package com.giveus.payment.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(description = "포인트 충전 내역 응답")
public class PointRechargeRes {

    @Schema(description = "포인트 충전 PK", example = "1")
    private int pointNo;
    @Schema(description = "충전 금액", example = "10000")
    private int amount;
    @Schema(description = "충전 일자", example = "2024-03-22T18:01:43")
    private LocalDateTime createdAt;
    @Schema(description = "충전 내용", example = "일반 포인트 충전")
    private String content;
    @Schema(description = "결제 수단", example = "토스페이")
    private String paymentType;

}
