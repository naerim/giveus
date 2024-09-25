package com.giveus.admin.domain.funding.controller;

import com.giveus.admin.global.common.response.CommonResponseBody;
import com.giveus.admin.global.common.response.CreateSuccessDto;
import com.giveus.admin.global.config.annotaion.SwaggerApiSuccess;
import com.giveus.admin.domain.member.dto.AdminJoinPostReq;
import com.giveus.admin.domain.funding.dto.FundingCreateReq;
import com.giveus.admin.domain.member.dto.AdminInfoRes;
import com.giveus.admin.domain.funding.dto.FundingDetailsRes;
import com.giveus.admin.domain.funding.dto.FundingListRes;
import com.giveus.admin.domain.member.application.AdminService;
import com.giveus.admin.domain.funding.application.FundingService;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Tag(name = "펀딩 API", description = "Funding")
@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FundingController {

    private final FundingService fundingService;


    @SwaggerApiSuccess(summary = "펀딩 전체 목록 조회", implementation = FundingListRes.class)
    @GetMapping("/{adminNo}/funding")
    public ResponseEntity<CommonResponseBody<List<FundingListRes>>> getFundingList(@PathVariable int adminNo) {
        return ResponseEntity
                .status(OK)
                .body(new CommonResponseBody<>(OK, fundingService.getFundingList(adminNo)));
    }

    @SwaggerApiSuccess(summary = "펀딩 상세 조회", implementation = FundingDetailsRes.class)
    @GetMapping("funding/{fundingNo}")
    public ResponseEntity<CommonResponseBody<FundingDetailsRes>> getFunding(@PathVariable int fundingNo) {
        return ResponseEntity
                .status(OK)
                .body(new CommonResponseBody<>(OK, fundingService.getFunding(fundingNo)));
    }

    @SwaggerApiSuccess(summary = "펀딩 1차 등록", implementation = FundingDetailsRes.class)
    @PostMapping
    public ResponseEntity<CommonResponseBody<CreateSuccessDto>> createFunding(@RequestBody FundingCreateReq req) {
        return ResponseEntity
                .status(CREATED)
                .body(new CommonResponseBody<>(CREATED, fundingService.createFunding(req)));
    }



}
