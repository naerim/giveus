package com.giveus.payment.service;

import com.giveus.payment.dto.request.KakaoPayDonateReq;
import com.giveus.payment.dto.request.KakaoPayRechargeReq;
import com.giveus.payment.dto.response.TossPayConfirmRes;
import com.giveus.payment.dto.response.TossPayReadyRes;
import com.giveus.payment.entity.Member;
import com.giveus.payment.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TossPayService {

    private final MemberRepository memberRepository;

    @Value("${pay.toss.secret-key}")
    private String secretKey;

    @Value("${pay.toss.donate.success_url}")
    private String donateSuccessUrl;

    @Value("${pay.toss.donate.fail_url}")
    private String donateFailUrl;

    @Value("${pay.toss.recharge.success_url}")
    private String rechargeSuccessUrl;

    @Value("${pay.toss.recharge.fail_url}")
    private String rechargeFailUrl;

    @Transactional
    public TossPayReadyRes requestDonatePayment(KakaoPayDonateReq req) {

        Member member = memberRepository.findById(req.getMemberNo());
        return TossPayReadyRes.builder()
                .amount(req.getAmount())
                .orderId("F" + req.getFundingNo() + "M" + req.getMemberNo() + "_" + UUID.randomUUID().toString())
                .orderName(req.getTitle())
                .customerEmail(member.getEmail())
                .customerName(member.getName())
                .successUrl(donateSuccessUrl
                        + "?memberNo=" + req.getMemberNo()
                        + "&fundingNo=" + req.getFundingNo()
                        + "&point=" + req.getPoint()
                        + "&opened=" + req.isOpened())
                .failUrl(donateFailUrl)
                .build();
    }

    @Transactional
    public TossPayConfirmRes paymentConfirm(String orderId, String paymentKey, int amount) {

        log.info("donateSuccessUrl: {}", donateSuccessUrl);
        HttpHeaders headers = getRequestHttpHeaders();
        Map<String, Object> params = new HashMap<>();
        params.put("orderId", orderId);
        params.put("amount", amount);
        params.put("paymentKey", paymentKey);
        HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<>(params, headers);
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject(
                "https://api.tosspayments.com/v1/payments/confirm",
                httpEntity,
                TossPayConfirmRes.class
        );
    }

    @Transactional
    public TossPayReadyRes requestRechargePayment(KakaoPayRechargeReq req) {
        Member member = memberRepository.findById(req.getMemberNo());
        return TossPayReadyRes.builder()
                .amount(req.getAmount())
                .orderId("TOSS_M" + req.getMemberNo() + "_" + UUID.randomUUID().toString())
                .orderName("일반 포인트 충전")
                .customerEmail(member.getEmail())
                .customerName(member.getName())
                .successUrl(rechargeSuccessUrl
                        + "?memberNo=" + req.getMemberNo())
                .failUrl(rechargeFailUrl)
                .build();
    }

    private HttpHeaders getRequestHttpHeaders() {
        String encodedSecretKey = Base64.getEncoder().encodeToString((secretKey + ":").getBytes());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setBasicAuth(encodedSecretKey);
        return httpHeaders;
    }
}
