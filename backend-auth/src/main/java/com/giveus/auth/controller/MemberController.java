package com.giveus.auth.controller;

import com.giveus.auth.common.dto.CommonResponseBody;
import com.giveus.auth.common.swagger.SwaggerApiSuccess;
import com.giveus.auth.dto.response.MemberSettingInfoRes;
import com.giveus.auth.service.MemberService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/member")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    // 알림 설정 허용 정보 가져오기
    @SwaggerApiSuccess(summary = "알림 설정 허용 정보 가져오기 (엑세스 토큰 이용)", implementation = MemberSettingInfoRes.class)
    @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(implementation = Error.class)))
    @GetMapping("/info")
    public ResponseEntity<CommonResponseBody<MemberSettingInfoRes>> getMemberSetting(HttpServletRequest httpServletRequest) {
        return ResponseEntity
                .status(OK)
                .body(new CommonResponseBody<>(OK, memberService.getMemberSetting(httpServletRequest)));
    }

    // 펀딩 후기 등록 알림 설정 변경
    @SwaggerApiSuccess(summary = "펀딩 후기 등록 알림 설정 변경 (엑세스 토큰 이용)", implementation = MemberSettingInfoRes.class)
    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = Error.class)))
    @PatchMapping("/change/fundingReview")
    public ResponseEntity<CommonResponseBody<MemberSettingInfoRes>> updateFundingReview(HttpServletRequest httpServletRequest) {
        return ResponseEntity
                .status(OK)
                .body(new CommonResponseBody<>(OK, memberService.updateFundingReview(httpServletRequest)));
    }

    // 진료비 세부 내역 등록 알림 설정 변경
    @SwaggerApiSuccess(summary = "진료비 세부 내역 등록 알림 설정 변경 (엑세스 토큰 이용)", implementation = MemberSettingInfoRes.class)
    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = Error.class)))
    @PatchMapping("/change/usageHistory")
    public ResponseEntity<CommonResponseBody<MemberSettingInfoRes>> updateUsageHistory(HttpServletRequest httpServletRequest) {
        return ResponseEntity
                .status(OK)
                .body(new CommonResponseBody<>(OK, memberService.updateUsageHistory(httpServletRequest)));
    }
}
