package com.giveus.payment.controller;

import com.giveus.payment.common.dto.CommonResponseBody;
import com.giveus.payment.common.swagger.SwaggerApiSuccess;
import com.giveus.payment.dto.request.PointUsageReq;
import com.giveus.payment.dto.response.PointListRes;
import com.giveus.payment.service.MemberFundingService;
import com.giveus.payment.service.PointService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.EXPECTATION_FAILED;
import static org.springframework.http.HttpStatus.OK;

@Tag(name = "포인트 API", description = "Point")
@RestController
@RequestMapping("/api/v1/payment/point")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class PointController {

    private final PointService pointService;
    private final MemberFundingService memberFundingService;

    @Operation(summary = "회원 포인트 충전 및 사용 내역 조회", description = "회원의 포인트 충전 내역 및 사용 내역을 조회하는 API 메서드 입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공", useReturnTypeSchema = true)
    })
    @GetMapping("/{memberNo}")
    public ResponseEntity<CommonResponseBody<PointListRes>> getPointList(@PathVariable("memberNo") int memberNo) {
        return ResponseEntity.status(OK)
                .body(new CommonResponseBody<>(OK, pointService.getPointList(memberNo)));
    }

    @Operation(summary = "포인트 펀딩 후원", description = "포인트만 사용하여 펀딩에 후원하는 API 메서드 입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "417", description = "실패", useReturnTypeSchema = true)
    })
    @PostMapping
    public ResponseEntity<CommonResponseBody<String>> payPoint(@RequestBody PointUsageReq request) {
        try {
            LocalDateTime now = LocalDateTime.now().plusHours(9);
            int pointNo = pointService.usePoint(request, now);
            memberFundingService.save(request, pointNo, now);
            return ResponseEntity.status(OK)
                    .body(new CommonResponseBody<>(OK, "포인트 펀딩 후원에 성공했습니다."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new CommonResponseBody<>(EXPECTATION_FAILED, "포인트 펀딩 후원에 실패했습니다."));
        }
    }
}
