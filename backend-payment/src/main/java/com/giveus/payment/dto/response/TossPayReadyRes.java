package com.giveus.payment.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "토스페이 결제 준비 응답. 토스페이먼츠 결제창 띄우는 요청시 결제 정보로 사용")
public class TossPayReadyRes {

    @Schema(description = "결제 금액", example = "10000")
    private int amount;
    @Schema(description = "주문번호입니다. 주문을 구분하는 ID입니다. 충분히 무작위한 값을 생성해서 각 주문마다 고유한 값을 넣어주세요." +
            " 영문 대소문자, 숫자, 특수문자 -, _, =로 이루어진 6자 이상 64자 이하의 문자열이어야 합니다.", example = "AD8aZDpbzXs4EQa-UkIX6")
    private String orderId;
    @Schema(description = "구매상품입니다. 예를 들면 생수 외 1건 같은 형식입니다. 최대 길이는 100자입니다.", example = "토스 티셔츠 외 2건")
    private String orderName;
    @Schema(description = "구매자의 이메일 주소입니다. 결제 상태가 바뀌면 이메일 주소로 결제내역이 전송됩니다. 최대 길이는 100자입니다.", example = "customer123@gmail.com")
    private String customerEmail;
    @Schema(description = "구매자명입니다. 최대 길이는 100자입니다.", example = "김토스")
    private String customerName;
    @Schema(description = "결제가 성공하면 리다이렉트되는 URL입니다. 결제 승인 처리에 필요한 값들이 쿼리 파라미터로 함께 전달됩니다. " +
            "반드시 오리진을 포함해야 합니다. 예를 들면 https://www.example.com/success와 같은 형태입니다.", example = "http://localhost:8080/success")
    private String successUrl;
    @Schema(description = "결제가 실패하면 리다이렉트되는 URL입니다. 에러 코드 및 에러 메시지가 쿼리 파라미터로 함께 전송됩니다. 반드시 오리진을 포함해야 합니다.", example = "http://localhost:8080/fail")
    private String failUrl;
}
