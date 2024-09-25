package com.giveus.admin.domain.usage.controller;

import com.giveus.admin.global.common.response.CommonResponseBody;
import com.giveus.admin.global.common.response.CreateSuccessDto;
import com.giveus.admin.global.config.annotaion.SwaggerApiSuccess;
import com.giveus.admin.domain.usage.dto.FundingUsageCreateReq;
import com.giveus.admin.domain.funding.dto.FundingDetailsRes;
import com.giveus.admin.domain.member.application.AdminService;
import com.giveus.admin.domain.usage.application.UsageHistoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@Tag(name = "기부금 사용 API", description = "Funding Usage")
@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsageController {

    private final UsageHistoryService usageService;

    @SwaggerApiSuccess(summary = "펀딩 사용 내역 등록", implementation = FundingDetailsRes.class)
    @PostMapping("/usage")
    public ResponseEntity<CommonResponseBody<CreateSuccessDto>> createFundingUsage(
            @RequestBody List<FundingUsageCreateReq> req) {
        return ResponseEntity
                .status(CREATED)
                .body(new CommonResponseBody<>(CREATED, usageService.createFundingUsage(req)));
    }


}
