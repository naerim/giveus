package com.giveus.funding.domain.usage.api;

import com.giveus.funding.domain.usage.application.UsageHistoryService;
import com.giveus.funding.domain.usage.dto.FundingUsageListRes;
import com.giveus.funding.global.common.response.CommonResponseBody;
import com.giveus.funding.global.config.SwaggerApiSuccess;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@Tag(name = "[v2] 펀딩 후원금 사용 API")
@RestController
@RequestMapping("/api/v2/funding/usages")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsageControllerV2 {
    private final UsageHistoryService usageService;

    @SwaggerApiSuccess(summary = "펀딩 기금 사용 내역 조회", implementation = FundingUsageListRes.class)
    @GetMapping("/{fundingNo}/usage")
    public ResponseEntity<CommonResponseBody<List<FundingUsageListRes>>> getFundingUsage(@PathVariable int fundingNo) {
        return ResponseEntity
                .status(OK)
                .body(new CommonResponseBody<>(OK, usageService.getUsageList(fundingNo)));
    }

}
