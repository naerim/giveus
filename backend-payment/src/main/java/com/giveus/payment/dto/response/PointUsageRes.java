package com.giveus.payment.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(description = "포인트 사용 내역 응답")
public class PointUsageRes {

    @Schema(description = "포인트 사용 PK", example = "1")
    private int pointNo;
    @Schema(description = "사용 금액", example = "10000")
    private int amount;
    @Schema(description = "사용 일자", example = "2024-03-29T11:01:05")
    private LocalDateTime createdAt;
    @Schema(description = "펀딩 제목", example = "펀딩 1234")
    private String title;

}
