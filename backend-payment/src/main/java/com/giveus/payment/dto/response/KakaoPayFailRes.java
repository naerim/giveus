package com.giveus.payment.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Schema(description = "카카오페이 결제 수단 승인 실패 응답")
public class KakaoPayFailRes {
    @Schema(description = "에러 코드", example = "-780")
    private int error_code;
    @Schema(description = "에러 메시지", example = "approval failure!")
    private String error_message;
    private Extras extras;
}
