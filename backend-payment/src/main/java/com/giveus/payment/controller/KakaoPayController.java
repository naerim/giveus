package com.giveus.payment.controller;

import com.giveus.payment.common.dto.CommonResponseBody;
import com.giveus.payment.dto.request.KakaoPayDonateReq;
import com.giveus.payment.dto.request.KakaoPayRechargeReq;
import com.giveus.payment.dto.response.KakaoPayApproveRes;
import com.giveus.payment.dto.response.KakaoPayReadyRes;
import com.giveus.payment.service.KakaoPayService;
import com.giveus.payment.service.MemberFundingService;
import com.giveus.payment.service.PointService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;

import static org.springframework.http.HttpStatus.*;

@Tag(name = "카카오페이 API", description = "KakaoPay")
@RestController
@RequestMapping("/api/v1/payment/kakao")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class KakaoPayController {

    private final KakaoPayService kakaoPayService;
    private final MemberFundingService memberFundingService;
    private final PointService pointService;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    @Operation(summary = "카카오페이 포인트 충전 준비", description = "카카오페이로 포인트 충전 결제를 하기 위해 요청해야 하는 API 메서드입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = KakaoPayReadyRes.class))),
            @ApiResponse(responseCode = "500", description = "서버 오류", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PostMapping("/recharge/ready")
    public ResponseEntity<CommonResponseBody<?>> readyRecharge(@RequestBody KakaoPayRechargeReq req) {
        try {
            return ResponseEntity.status(OK)
                    .body(new CommonResponseBody<>(OK, kakaoPayService.getRedirectUrl(req)));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                    .body(new CommonResponseBody<>(INTERNAL_SERVER_ERROR, e.getMessage()));
        }
    }

    @Operation(summary = "카카오페이 펀딩 후원 준비", description = "카카오페이로 펀딩 후원 결제를 하기 위해 요청해야 하는 API 메서드입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = KakaoPayReadyRes.class))),
            @ApiResponse(responseCode = "500", description = "서버 오류", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PostMapping("/donate/ready")
    public ResponseEntity<CommonResponseBody<?>> readyDonate(@RequestBody KakaoPayDonateReq req) {
        try {
            return ResponseEntity.status(OK)
                    .body(new CommonResponseBody<>(OK, kakaoPayService.getRedirectUrl(req)));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                    .body(new CommonResponseBody<>(INTERNAL_SERVER_ERROR, e.getMessage()));
        }
    }

    @Operation(summary = "카카오페이 포인트 충전 성공", description = "카카오페이로 포인트 충전 결제에 성공할 경우 요청해야 하는 API 메서드입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = KakaoPayApproveRes.class))),
            @ApiResponse(responseCode = "500", description = "서버 오류", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @GetMapping("/recharge/success")
    public ResponseEntity<CommonResponseBody<?>> approveRecharge(@RequestParam("member_no") int memberNo,
                                                                 @RequestParam("pg_token") String pgToken) {
        try {
            KakaoPayApproveRes res = kakaoPayService.getApprove(pgToken, memberNo);
            pointService.saveRecharge(memberNo, res.getAmount().getTotal(), "카카오페이", res.getItem_name(), res.getApproved_at(), FORMATTER);
            return ResponseEntity.status(OK)
                    .body(new CommonResponseBody<>(OK, res));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                    .body(new CommonResponseBody<>(INTERNAL_SERVER_ERROR, e.getMessage()));
        }
    }

    @Operation(summary = "카카오페이 펀딩 후원 성공", description = "카카오페이로 펀딩 후원 결제에 성공할 경우 요청해야 하는 API 메서드입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = KakaoPayApproveRes.class))),
            @ApiResponse(responseCode = "500", description = "서버 오류", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @GetMapping("/donate/success")
    public ResponseEntity<CommonResponseBody<?>> approveDonate(@RequestParam("member_no") int memberNo,
                                                               @RequestParam("funding_no") int fundingNo,
                                                               @RequestParam("point") int point,
                                                               @RequestParam("opened") boolean opened,
                                                               @RequestParam("pg_token") String pgToken) {
        try {
            KakaoPayApproveRes res = kakaoPayService.getApprove(pgToken, memberNo, fundingNo);
            memberFundingService.save(memberNo, fundingNo, "카카오페이",
                    res.getApproved_at(), res.getAmount().getTotal(), point, opened, FORMATTER);

            return ResponseEntity.status(OK)
                    .body(new CommonResponseBody<>(OK, res));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                    .body(new CommonResponseBody<>(INTERNAL_SERVER_ERROR, e.getMessage()));
        }
    }

    @Operation(summary = "카카오페이 펀딩 후원 취소", description = "카카오페이로 펀딩 후원 결제를 취소할 경우 요청해야 하는 API 메서드입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "417", description = "취소", useReturnTypeSchema = true),
    })
    @GetMapping("/donate/cancel")
    public ResponseEntity<CommonResponseBody<String>> cancelDonate() {
        return ResponseEntity.status(EXPECTATION_FAILED)
                .body(new CommonResponseBody<>(EXPECTATION_FAILED, "사용자가 결제를 취소했습니다."));
    }

    @Operation(summary = "카카오페이 펀딩 후원 실패", description = "카카오페이로 펀딩 후원 결제를 실패할 경우 요청해야 하는 API 메서드입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "417", description = "실패", useReturnTypeSchema = true),
    })
    @GetMapping("/donate/fail")
    public ResponseEntity<CommonResponseBody<String>> failDonate() {
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                .body(new CommonResponseBody<>(EXPECTATION_FAILED, "결제에 실패했습니다."));
    }

}
