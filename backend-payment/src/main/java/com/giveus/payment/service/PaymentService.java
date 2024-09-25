package com.giveus.payment.service;

import java.time.format.DateTimeFormatter;

public interface PaymentService {

    /**
     * 카카오페이 결제 완료 시 결제 DB 테이블에 데이터를 저장하기 위해 사용하는 메서드입니다.
     * @param createdAt 생성일자
     * @param method 결제수단
     * @param amount 금액
     * @param formatter 날짜 형식 포맷
     * @return 결제 PK
     */
    int save(String createdAt, String method, int amount, DateTimeFormatter formatter);
}
