package com.giveus.funding.domain.donation.api;

import com.giveus.funding.domain.donation.application.DonationService;
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

@Tag(name = "[v1] 마이페이지 API")
@RestController
@RequestMapping("/api/v1/funding/mypage")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MypageControllerV1 {

    private final DonationService donationService;

    @SwaggerApiSuccess(summary = "회원 펀딩 참여 내역 조회", implementation = FundingListRes.class)
    @GetMapping("/{memberNo}")
    public ResponseEntity<CommonResponseBody<List<MemberDonationListRes>>> getFundingListByMember(@PathVariable int memberNo) {
        return ResponseEntity
                .status(OK)
                .body(new CommonResponseBody<>(OK, donationService.getDonationListByMember(memberNo)));
    }

}
