package com.giveus.payment.service;

import com.giveus.payment.dto.request.PointUsageReq;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public interface MemberFundingService {
    /**
     * 카카오페이 결제 완료 시 회원_펀딩 DB 테이블에 회원의 후원 정보 데이터를 저장하기 위해 사용하는 메서드 입니다.
     * @param memberNo 회원 PK
     * @param fundingNo 펀딩 PK
     * @param method 결제 수단
     * @param createdAt 생성일자
     * @param amount 카카오페이 결제금액
     * @param point 포인트 사용금액
     * @param opened 공개 여부(후원 내역에 회원 프로필 공개할지 말지 선택여부)
     * @return 회원_펀딩 PK
     */
    int save(int memberNo, int fundingNo, String method, String createdAt, int amount, int point, boolean opened, DateTimeFormatter formatter) throws Exception;

    /**
     * 포인트로만 결제 완료 시 회원 펀딩 DB 테이블에 회원의 후원 정보 데이터를 저장하기 위해 사용하는 메서드 입니다.
     * @param request 포인트 사용 요청 바디
     * @param pointNo 포인트 사용내역 PK
     * @param now 사용일자
     * @return 회원_펀딩 PK
     */
    int save(PointUsageReq request, int pointNo, LocalDateTime now);
}
