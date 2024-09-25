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
@Schema(description = "카카오페이 결제 수단 승인 성공 응답")
public class KakaoPayApproveRes {
    @Schema(description = "요청 고유 번호 - 승인/취소가 구분된 결제번호", example = "A5678901234567890123")
    private String aid;
    @Schema(description = "결제 고유 번호 - 승인/취소가 동일한 결제번호", example = "T1234567890123456789")
    private String tid;
    @Schema(description = "가맹점 코드", example = "TC0ONETIME")
    private String cid;
    @Schema(description = "가맹점 주문번호, 최대 100자", example = "partner_order_id")
    private String partner_order_id;
    @Schema(description = "가맹점 회원 id, 최대 100자", example = "partner_user_id")
    private String partner_user_id;
    @Schema(description = "결제 수단, CARD 또는 MONEY 중 하나", example = "CARD")
    private String payment_method_type;
    @Schema(description = "결제 금액 정보", example = "")
    private Amount amount;
    @Schema(description = "결제 상세 정보, 결제 수단이 카드일 경우만 포함", example = "")
    private CardInfo card_info;
    @Schema(description = "상품명", example = "초코파이")
    private String item_name;
    @Schema(description = "상품 수량", example = "1")
    private int quantity;
    @Schema(description = "결제 준비 요청 시각", example = "2023-07-15T21:18:22")
    private String created_at;
    @Schema(description = "결제 승인 시각", example = "2023-07-15T21:18:22")
    private String approved_at;

}