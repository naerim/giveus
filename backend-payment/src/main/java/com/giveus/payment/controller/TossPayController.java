package com.giveus.payment.controller;

import com.giveus.payment.common.dto.CommonResponseBody;
import com.giveus.payment.common.swagger.SwaggerApiSuccess;
import com.giveus.payment.dto.request.KakaoPayDonateReq;
import com.giveus.payment.dto.request.KakaoPayRechargeReq;
import com.giveus.payment.dto.response.TossPayConfirmRes;
import com.giveus.payment.service.MemberFundingService;
import com.giveus.payment.service.PointService;
import com.giveus.payment.service.TossPayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.*;

@Tag(name = "토스페이 API", description = "TossPay")
@RestController
@RequestMapping("/api/v1/payment/toss")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class TossPayController {

    private final TossPayService tossPayService;
    private final MemberFundingService memberFundingService;
    private final PointService pointService;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

    @Operation(summary = "토스페이 펀딩 후원 준비", description = "토스페이 펀딩 후원 결제를 하기 위해 요청해야 하는 API 메서드입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "500", description = "서버 오류", useReturnTypeSchema = true)
    })
    @PostMapping("/donate/ready")
    public ResponseEntity<?> donateReady(@RequestBody KakaoPayDonateReq donateReq) {
        try {
            return ResponseEntity.status(OK)
                    .body(new CommonResponseBody<>(OK, tossPayService.requestDonatePayment(donateReq)));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                    .body(new CommonResponseBody<>(INTERNAL_SERVER_ERROR, e.getMessage()));
        }
    }

    @Operation(summary = "토스페이 펀딩 후원 성공", description = "토스페이로 펀딩 후원 결제에 성공할 경우 요청해야 하는 API 메서드입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "500", description = "서버 오류", useReturnTypeSchema = true)
    })
    @GetMapping("/donate/success")
    public ResponseEntity<?> donateSuccess(@RequestParam("memberNo") int memberNo,
                                           @RequestParam("fundingNo") int fundingNo,
                                           @RequestParam("point") int point,
                                           @RequestParam("opened") boolean opened,
                                           @RequestParam("orderId") String orderId,
                                           @RequestParam("paymentKey") String paymentKey,
                                           @RequestParam("amount") int amount) {

        try {
            TossPayConfirmRes res = tossPayService.paymentConfirm(orderId, paymentKey, amount);
            memberFundingService.save(memberNo, fundingNo, "토스페이",
                    res.getApprovedAt(), res.getTotalAmount(), point, opened, FORMATTER);

            return ResponseEntity.status(OK)
                    .body(new CommonResponseBody<>(OK, res));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                    .body(new CommonResponseBody<>(INTERNAL_SERVER_ERROR, e.getMessage()));
        }
    }

    @Operation(summary = "토스페이 펀딩 후원 실패", description = "토스페이로 펀딩 후원 결제를 실패할 경우 요청해야 하는 API 메서드입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "417", description = "실패", useReturnTypeSchema = true),
    })
    @GetMapping("/donate/fail")
    public ResponseEntity<CommonResponseBody<Map<String, String>>> donateFail(@RequestParam("code") String errorCode,
                                                                              @RequestParam("message") String errorMessage,
                                                                              @RequestParam("orderId") String orderId) {
        Map<String, String> map = new HashMap<>();
        map.put("에러코드", errorCode);
        map.put("에러메시지", errorMessage);
        map.put("주문번호", orderId);
        return ResponseEntity.status(EXPECTATION_FAILED)
                .body(new CommonResponseBody<>(EXPECTATION_FAILED, map));
    }

    @Operation(summary = "토스페이 포인트 충전 준비", description = "토스페이로 포인트 충전 결제를 하기 위해 요청해야 하는 API 메서드입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "500", description = "서버 오류", useReturnTypeSchema = true)
    })
    @PostMapping("/recharge/ready")
    public ResponseEntity<?> rechargeReady(@RequestBody KakaoPayRechargeReq req) {
        log.info("rechargeReq: {}", req);
        return ResponseEntity.status(OK)
                .body(new CommonResponseBody<>(OK, tossPayService.requestRechargePayment(req)));
    }

    @Operation(summary = "토스페이 포인트 충전 성공", description = "토스페이로 포인트 충전 결제에 성공할 경우 요청해야 하는 API 메서드입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "500", description = "서버 오류", useReturnTypeSchema = true)
    })
    @GetMapping("/recharge/success")
    public ResponseEntity<?> rechargeSuccess(@RequestParam("memberNo") int memberNo,
                                             @RequestParam("orderId") String orderId,
                                             @RequestParam("paymentKey") String paymentKey,
                                             @RequestParam("amount") int amount) {
        try {
            TossPayConfirmRes res = tossPayService.paymentConfirm(orderId, paymentKey, amount);
            pointService.saveRecharge(memberNo, res.getTotalAmount(), "토스페이", res.getOrderName(), res.getApprovedAt(), FORMATTER);
            return ResponseEntity.status(OK)
                    .body(new CommonResponseBody<>(OK, res));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                    .body(new CommonResponseBody<>(INTERNAL_SERVER_ERROR, e.getMessage()));
        }
    }

    @Operation(summary = "토스페이 펀포인트 충전 실패", description = "토스페이로 포인트 충전 결제를 실패할 경우 요청해야 하는 API 메서드입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "417", description = "실패", useReturnTypeSchema = true),
    })
    @GetMapping("/recharge/fail")
    public ResponseEntity<CommonResponseBody<Map<String, String>>> rechargeFail(@RequestParam("code") String errorCode,
                                                                                @RequestParam("message") String errorMessage,
                                                                                @RequestParam("orderId") String orderId) {
        Map<String, String> map = new HashMap<>();
        map.put("에러코드", errorCode);
        map.put("에러메시지", errorMessage);
        map.put("주문번호", orderId);
        return ResponseEntity.status(EXPECTATION_FAILED)
                .body(new CommonResponseBody<>(EXPECTATION_FAILED, map));
    }

}
