package com.giveus.payment.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Schema(description = "결제 상세 정보, 결제 수단이 카드일 경우만 포함")
public class CardInfo {
    @Schema(description = "카카오페이 매입사명", example = "비씨카드")
    private String kakaopay_purchase_corp;
    @Schema(description = "카카오페이 매입사 코드", example = "104")
    private String kakaopay_purchase_corp_code;
    @Schema(description = "카카오페이 발급사명", example = "수협은행")
    private String kakaopay_issuer_corp;
    @Schema(description = "카카오페이 발급사 코드", example = "212")
    private String kakaopay_issuer_corp_code;
    @Schema(description = "카드 BIN", example = "621640")
    private String bin;
    @Schema(description = "카드 타입", example = "체크")
    private String card_type;
    @Schema(description = "할부 개월 수", example = "00")
    private String install_month;
    @Schema(description = "카드사 승인번호", example = "12345678")
    private String approved_id;
    @Schema(description = "카드사 가맹점 번호", example = "123456789")
    private String card_mid;
    @Schema(description = "무이자할부 여부(Y/N)", example = "N")
    private String interest_free_install;
    @Schema(description = "할부 유형. CARD_INSTALLMENT(업종 무이자),  SHARE_INSTALLMENT(분담 무이자)", example = "CARD_INSTALLMENT")
    private String installment_type;
    @Schema(description = "카드 상품 코드", example = "")
    private String card_item_code;
}
