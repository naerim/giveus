package com.giveus.payment.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;

/**
 * getReadyRequest()를 카카오페이 API에 Request로 보내면 얻는 Response를 Java 객체로 변환하기 위한 Dto
 * tid는 필수로 얻어야하고, url도 하나는 무조건 얻어야함.
 */
@Getter
@ToString
@Schema(description = "카카오페이 결제 준비 응답")
public class KakaoPayReadyRes {
    @Schema(description = "결제 고유 번호, 20자", example = "T1234567890123456789")
    private String tid;
    @Schema(description = "요청한 클라이언트가 PC 웹일 경우, 카카오톡으로 결제 요청 메시지(TMS)를 보내기 위한 사용자 정보 입력 화면 Redirect URL", example = "https://mockup-pg-web.kakao.com/v1/xxxxxxxxxx/info")
    private String next_redirect_pc_url;
    @Schema(description = "요청한 클라이언트(Client)가 모바일 앱일 경우, 카카오톡 결제 페이지 Redirect URL", example = "https://mockup-pg-web.kakao.com/v1/xxxxxxxxxx/aInfo")
    private String next_redirect_app_url;
    @Schema(description = "요청한 클라이언트가 모바일 웹일 경우 카카오톡 결제 페이지 Redirect URL", example = "https://mockup-pg-web.kakao.com/v1/xxxxxxxxxx/mInfo")
    private String next_redirect_mobile_url;
    @Schema(description = "결제 준비 요청 시간", example = "2023-07-15T21:18:22")
    private String created_at;
}
