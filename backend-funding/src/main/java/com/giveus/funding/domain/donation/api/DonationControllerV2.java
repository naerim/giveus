package com.giveus.funding.domain.donation.api;

import com.giveus.funding.domain.donation.application.DonationService;
import com.giveus.funding.domain.donation.dto.DonationAmountRes;
import com.giveus.funding.domain.donation.dto.DonationListRes;
import com.giveus.funding.domain.donation.dto.MemberDonationListRes;
import com.giveus.funding.domain.funding.dto.FundingListRes;
import com.giveus.funding.global.common.response.CommonResponseBody;
import com.giveus.funding.global.config.SwaggerApiSuccess;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@Tag(name = "[v2] 펀딩 참여 API")
@RestController
@RequestMapping("/api/v2/funding/donations")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DonationControllerV2 {
    private final DonationService donationService;

    @SwaggerApiSuccess(summary = "기부 내역 전체 조회",
            implementation = DonationListRes.class)
    @GetMapping
    public ResponseEntity<CommonResponseBody<List<DonationListRes>>> getRecentFundingParticipants(@RequestParam int limit) {
        return ResponseEntity
                .status(OK)
                .body(new CommonResponseBody<>(OK, donationService.getDonationList(limit)));
    }

    @SwaggerApiSuccess(summary = "펀딩별 기부 내역 조회", implementation = DonationListRes.class)
    @GetMapping("/fundings/{fundingNo}")
    public ResponseEntity<CommonResponseBody<List<DonationListRes>>> getFundingParticipants(@PathVariable int fundingNo) {
        return ResponseEntity
                .status(OK)
                .body(new CommonResponseBody<>(OK, donationService.getDonationListByFunding(fundingNo)));
    }

    @SwaggerApiSuccess(summary = "기부 누적 금액 조회", implementation = DonationAmountRes.class)
    @GetMapping("/total-amount")
    public ResponseEntity<CommonResponseBody<DonationAmountRes>> getDonationAmount() {
        return ResponseEntity
                .status(OK)
                .body(new CommonResponseBody<>(OK, donationService.getDonationAmount()));
    }

    @SwaggerApiSuccess(summary = "회원 기부 내역 조회", implementation = FundingListRes.class)
    @GetMapping("/members/{memberNo}")
    public ResponseEntity<CommonResponseBody<List<MemberDonationListRes>>> getFundingListByMember(@PathVariable int memberNo) {
        return ResponseEntity
                .status(OK)
                .body(new CommonResponseBody<>(OK, donationService.getDonationListByMember(memberNo)));
    }


}
