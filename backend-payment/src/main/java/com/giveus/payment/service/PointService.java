package com.giveus.payment.service;

import com.giveus.payment.dto.request.PointUsageReq;
import com.giveus.payment.dto.response.PointListRes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public interface PointService {
    /**
     * 카카오페이 결제 완료시 포인트 사용내역 DB 테이블에 데이터를 저장하기 위해 사용하는 메서드 입니다.
     * @param memberNo 회원 PK
     * @param point 사용 금액
     * @param createdAt 사용 일자
     * @return 포인트 사용내역 PK
     */
    int saveUsage(int memberNo, int point, String createdAt, DateTimeFormatter formatter);

    /**
     * 회원 포인트 내역을 조회하기 위해 사용하는 메서드 입니다.
     * @param memberNo 회원 PK
     * @return 회원의 포인트 사용내역 리스트 및 포인트 충전내역 리스트
     */
    PointListRes getPointList(int memberNo);

    /**
     * 포인트로만 결제 완료시 포인트 사용내역 DB 테이블에 데이터를 저장하기 위해 사용하는 메서드 입니다.
     * @param request 포인트 사용 요청 바디
     * @param now 사용 일자
     * @return 포인트 사용내역 PK
     */
    int usePoint(PointUsageReq request, LocalDateTime now);

    /**
     * 일반 포인트 충전 완료 시 포인트 충전내역 DB 테이블에 데이터를 저장하기 위해 사용하는 메서드 입니다.
     * @param memberNo 회원 PK
     * @param amount 충전 금액
     * @param paymentType 결제 수단
     * @param content 충전 내용
     * @param createdAt 충전 일자
     * @param formatter 날짜 포맷
     * @return 포인트 충전내역 PK
     */
    int saveRecharge(int memberNo, int amount, String paymentType, String content, String createdAt, DateTimeFormatter formatter);
}
