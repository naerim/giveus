package com.giveus.funding.domain.funding.api;

import com.giveus.funding.domain.funding.application.FundingService;
import com.giveus.funding.domain.funding.dto.FundingCreateReq;
import com.giveus.funding.domain.funding.dto.FundingDetailRes;
import com.giveus.funding.domain.funding.dto.FundingListRes;
import com.giveus.funding.global.common.response.CommonResponseBody;
import com.giveus.funding.global.common.response.CreateSuccessDto;
import com.giveus.funding.global.config.SwaggerApiSuccess;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@Tag(name = "[v2] 펀딩 API")
@RestController
@RequestMapping("/api/v2/funding/fundings")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FundingControllerV2 {

    private final FundingService fundingService;

    @SwaggerApiSuccess(summary = "펀딩 전체 목록 조회", implementation = FundingListRes.class)
    @GetMapping
    public ResponseEntity<CommonResponseBody<List<FundingListRes>>> getFundingList(
            @RequestParam(required = false) String sort, @RequestParam(required = false) Integer size) {
        return ResponseEntity
                .status(OK)
                .body(new CommonResponseBody<>(OK, fundingService.getFundingList(sort, size)));
    }


    @SwaggerApiSuccess(summary = "펀딩 상세(소개) 조회", implementation = FundingDetailRes.class)
    @GetMapping("/{fundingNo}")
    public ResponseEntity<CommonResponseBody<FundingDetailRes>> getFunding(@PathVariable int fundingNo) {
        return ResponseEntity
                .status(OK)
                .body(new CommonResponseBody<>(OK, fundingService.getFunding(fundingNo)));
    }

    @SwaggerApiSuccess(summary = "펀딩명 검색", implementation = FundingListRes.class)
    @GetMapping("/search")
    public ResponseEntity<CommonResponseBody<List<FundingListRes>>> getFundingQuery(@RequestParam String query) {
        return ResponseEntity
                .status(OK)
                .body(new CommonResponseBody<>(OK, fundingService.getFundingSearchList(query)));
    }

    @SwaggerApiSuccess(summary = "펀딩 2차 등록", implementation = CreateSuccessDto.class)
    @PostMapping
    public ResponseEntity<CommonResponseBody<CreateSuccessDto>> createFunding(
            @Valid @RequestPart FundingCreateReq fundingCreateReq, @RequestPart(required = false) MultipartFile file) {
        return ResponseEntity
                .status(OK)
                .body(new CommonResponseBody<>(OK, fundingService.createFunding(fundingCreateReq, file)));
    }


}
