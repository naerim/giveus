package com.giveus.payment.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Schema(description = "결제 수단 승인 실패")
public class Extras {
    @Schema(description = "결제수단 결과 코드?", example = "USER_LOCKED")
    private String method_result_code;
    @Schema(description = "결제수단 결과 메시지?", example = "진행중인 거래가 있습니다. 잠시 후 다시 시도해 주세요.")
    private String method_result_message;
}
