package com.giveus.payment.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Schema(description = "토스 페이 결제 승인 응답")
public class TossPayConfirmRes {

    @Schema(description = "상점아이디(MID)입니다. 토스페이먼츠에서 발급합니다. 최대 길이는 14자입니다.", example = "tosspayments")
    private String mId;
    @Schema(description = "마지막 거래의 키 값입니다. 한 결제 건의 승인 거래와 취소 거래를 구분하는 데 사용됩니다." +
            " 예를 들어 결제 승인 후 부분 취소를 두 번 했다면 마지막 부분 취소 거래의 키 값이 할당됩니다. 최대 길이는 64자입니다.", example = "9C62B18EEF0DE3EB7F4422EB6D14BC6E")
    private String lastTransactionKey;
    @Schema(description = "결제의 키 값입니다. 최대 길이는 200자입니다. 결제를 식별하는 역할로, 중복되지 않는 고유한 값입니다. "
            + "결제 데이터 관리를 위해 반드시 저장해야 합니다. 결제 상태가 변해도 값이 유지됩니다. 결제 승인, 결제 조회, 결제 취소 API에 사용합니다."
            , example = "5EnNZRJGvaBX7zk2yd8ydw26XvwXkLrx9POLqKQjmAw4b0e1")
    private String paymentKey;
    @Schema(description = "주문번호입니다. 최소 길이는 6자, 최대 길이는 64자입니다. 주문한 결제를 식별하는 역할로, 결제를 요청할 때 가맹점에서 만들어서 사용한 값입니다. "
            + "결제 데이터 관리를 위해 반드시 저장해야 합니다. 중복되지 않는 고유한 값을 발급해야 합니다. 결제 상태가 변해도 값이 유지됩니다."
            , example = "MC4wODU4ODQwMzg4NDk0")
    private String orderId;
    @Schema(description = "구매상품입니다. 예를 들면 생수 외 1건 같은 형식입니다. 최대 길이는 100자입니다.", example = "토스 티셔츠 외 2건")
    private String orderName;
    @Schema(description = "결제 처리 상태입니다. 아래와 같은 상태 값을 가질 수 있습니다. 상태 변화 흐름이 궁금하다면 흐름도를 살펴보세요.\n" +
            "- READY: 결제를 생성하면 가지게 되는 초기 상태입니다. 인증 전까지는 READY 상태를 유지합니다.\n" +
            "- IN_PROGRESS: 결제수단 정보와 해당 결제수단의 소유자가 맞는지 인증을 마친 상태입니다. 결제 승인 API를 호출하면 결제가 완료됩니다.\n" +
            "- WAITING_FOR_DEPOSIT: 가상계좌 결제 흐름에만 있는 상태로, 결제 고객이 발급된 가상계좌에 입금하는 것을 기다리고 있는 상태입니다.\n" +
            "- DONE: 인증된 결제수단 정보, 고객 정보로 요청한 결제가 승인된 상태입니다.\n" +
            "- CANCELED: 승인된 결제가 취소된 상태입니다.\n" +
            "- PARTIAL_CANCELED: 승인된 결제가 부분 취소된 상태입니다.\n" +
            "- ABORTED: 결제 승인이 실패한 상태입니다.\n" +
            "- EXPIRED: 결제 유효 시간 30분이 지나 거래가 취소된 상태입니다. IN_PROGRESS 상태에서 결제 승인 API를 호출하지 않으면 EXPIRED가 됩니다."
            , example = "DONE")
    private String status;
    @Schema(description = "결제가 일어난 날짜와 시간 정보입니다. yyyy-MM-dd'T'HH:mm:ss±hh:mm ISO 8601 형식입니다.\n" +
            "(e.g. 2022-01-01T00:00:00+09:00)", example = "2024-02-13T12:17:57+09:00")
    private String requestedAt;
    @Schema(description = "결제 승인이 일어난 날짜와 시간 정보입니다. yyyy-MM-dd'T'HH:mm:ss±hh:mm ISO 8601 형식입니다.\n" +
            "(e.g. 2022-01-01T00:00:00+09:00)", example = "2024-02-13T12:18:14+09:00")
    private String approvedAt;
    @Schema(description = "결제할 때 사용한 통화입니다.", example = "KRW")
    private String currency;
    @Schema(description = "총 결제 금액입니다. 결제가 취소되는 등 결제 상태가 변해도 최초에 결제된 결제 금액으로 유지됩니다.", example = "1000")
    private int totalAmount;
    @Schema(description = "결제수단입니다. 카드, 가상계좌, 간편결제, 휴대폰, 계좌이체, 문화상품권, 도서문화상품권, 게임문화상품권 중 하나입니다.", example = "카드")
    private String method;
}
