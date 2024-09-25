package com.giveus.payment.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;

/**
 * getApproveRequest() 를 카카오페이 API에 Request로 보냈을 때 받는 Response를 위한 객체 Dto 입니다.
 * 이것 역시 공식 문서에서 직접 Custom 하여 작성하시면 됩니다.
 */
@Getter
@ToString
@Schema(description = "결제 금액 정보")
public class Amount {
    @Schema(description = "전체 결제 금액", example = "2200")
    private int total;
    @Schema(description = "비과세 금액", example = "0")
    private int tax_free;
    @Schema(description = "부가세 금액", example = "200")
    private int vat;
    @Schema(description = "사용한 포인트 금액", example = "0")
    private int point;
    @Schema(description = "할인 금액", example = "0")
    private int discount;
    @Schema(description = "컵 보증금", example = "0")
    private int green_deposit;
}