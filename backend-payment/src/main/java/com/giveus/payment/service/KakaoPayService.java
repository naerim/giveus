package com.giveus.payment.service;

import com.giveus.payment.common.repository.TidRepository;
import com.giveus.payment.dto.request.KakaoPayDonateReq;
import com.giveus.payment.dto.request.KakaoPayRechargeReq;
import com.giveus.payment.dto.request.PointUsageReq;
import com.giveus.payment.dto.response.KakaoPayApproveRes;
import com.giveus.payment.dto.response.KakaoPayReadyRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class KakaoPayService {

    private final TidRepository tidRepository;

    private final PointService pointService;

    @Value("${pay.kakao.cid}")
    private String cid;

    @Value("${pay.kakao.secret-key}")
    private String secretKey;

    @Value("${pay.kakao.donate.success_url}")
    private String donateSuccessUrl;

    @Value("${pay.kakao.donate.cancel_url}")
    private String donateCancelUrl;

    @Value("${pay.kakao.donate.fail_url}")
    private String donateFailUrl;

    @Value("${pay.kakao.recharge.success_url}")
    private String rechargeSuccessUrl;

    @Value("${pay.kakao.recharge.cancel_url}")
    private String rechargeCancelUrl;

    @Value("${pay.kakao.recharge.fail_url}")
    private String rechargeFailUrl;

    /**
     * 카카오페이 결제를 시작하기 위해 상세 정보를 카카오페이 서버에 전달하고 결제 고유 번호(TID)를 받는 단계입니다.
     * 어드민 키를 헤더에 담아 파라미터 값들과 함께 POST로 요청합니다.
     *
     * @param donateReq 카카오페이 결제 요청 바디
     * @return 카카오페이 결제 준비 응답 바디
     */
    public KakaoPayReadyRes getRedirectUrl(KakaoPayDonateReq donateReq) {
        /** 요청 헤더 */
        HttpHeaders headers = getHttpHeaders();

        /** 요청 바디 */
        Map<String, Object> params = new HashMap<>();
        // partner_user_id, partner_order_id 는 결제 승인 요청에서도 동일해야함
        String orderId = "KAKAO_F" + donateReq.getFundingNo() + "M" + donateReq.getMemberNo();
        // 가맹점 코드, 10자
        params.put("cid", cid);
        // 가맹점 주문번호, 최대 100자
        params.put("partner_order_id", orderId);
        // 가맹점 회원 ID, 최대 100자
        params.put("partner_user_id", "Giveus");
        // 상품명, 최대 100자
        params.put("item_name", donateReq.getTitle());
        // 상품 수량
        params.put("quantity", 1);
        // 상품 총액
        params.put("total_amount", donateReq.getAmount());
        // 상품 비과세 금액
        params.put("tax_free_amount", 0);
        // 결제 성공 시 redirect url, 최대 255자
        params.put("approval_url", donateSuccessUrl
                + "?member_no=" + donateReq.getMemberNo()
                + "&funding_no=" + donateReq.getFundingNo()
                + "&point=" + donateReq.getPoint()
                + "&opened=" + donateReq.isOpened());
        // 결제 취소 시 redirect url, 최대 255자
        params.put("cancel_url", donateCancelUrl);
        // 결제 실패 시 redirect url, 최대 255자
        params.put("fail_url", donateFailUrl);

        /** Header와 Body 합쳐서 RestTemplate로 보내기 위한 밑작업 */
        HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<>(params, headers);

        /** RestTemplate로 Response 받아와서 DTO로 변환후 return */
        RestTemplate restTemplate = new RestTemplate();
        KakaoPayReadyRes payReadyResDto = restTemplate.postForObject(
                "https://open-api.kakaopay.com/online/v1/payment/ready",
                httpEntity,
                KakaoPayReadyRes.class);

        /** Tid Redis에 10분간 저장 */
        tidRepository.save(orderId, payReadyResDto.getTid(), 10L);


        return payReadyResDto;
    }

    /**
     * 사용자가 결제 수단을 선택하고 비밀번호를 입력해 결제 인증을 완료한 뒤, 최종적으로 결제 완료 처리를 하는 단계입니다.
     * 인증완료시 응답받은 pg_token과 tid로 최종 승인요청합니다.
     * 결제 승인 API를 호출하면 결제 준비 단계에서 시작된 결제건이 승인으로 완료 처리됩니다.
     *
     * @param pgToken
     * @param memberNo  회원 PK
     * @param fundingNo 펀딩 PK
     * @return 카카오페이 결제 승인 응답 바디
     * @throws Exception TID가 존재하지 않는 경우, 결제
     */
    public KakaoPayApproveRes getApprove(String pgToken, int memberNo, int fundingNo) throws Exception {

        String orderId = "KAKAO_F" + fundingNo + "M" + memberNo;
        String tid = tidRepository.find(orderId)
                .orElseThrow(() -> new Exception("결제 고유 번호가 존재하지 않습니다."));

        /** 요청 헤더 */
        HttpHeaders headers = getHttpHeaders();

        /** 요청 Body */
        Map<String, Object> params = new HashMap<>();
        // 가맹점 코드, 10자
        params.put("cid", cid);
        // 결제 고유 번호, 결제 준비 API 응답에 포함
        params.put("tid", tid);
        // 가맹점 주문번호, 결제 준비 API 요청과 일치해야 함
        params.put("partner_order_id", orderId); // 주문명
        // 가맹점 회원 id, 결제 준비 API 요청과 일치해야 함
        params.put("partner_user_id", "Giveus");
        // 결제승인 요청을 인증하는 토큰
        // 사용자 결제 수단 선택 완료 시, approval_url로 redirection해줄 때 pg_token을 query string으로 전달
        params.put("pg_token", pgToken);

        /** Header와 Body 합쳐서 RestTemplate로 보내기 위한 밑작업 */
        HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<>(params, headers);

        /** 요청 보내기 */
        RestTemplate restTemplate = new RestTemplate();
        KakaoPayApproveRes payApproveResDto = restTemplate.postForObject(
                "https://open-api.kakaopay.com/online/v1/payment/approve",
                httpEntity,
                KakaoPayApproveRes.class);

        /** tid Redis에서 삭제 */
        tidRepository.delete(orderId);

        return payApproveResDto;
    }

    /**
     * @param rechargeReq
     * @return
     */
    public KakaoPayReadyRes getRedirectUrl(KakaoPayRechargeReq rechargeReq) {
        /** 요청 헤더 */
        HttpHeaders headers = getHttpHeaders();

        /** 요청 바디 */
        Map<String, Object> params = new HashMap<>();
        // partner_user_id, partner_order_id 는 결제 승인 요청에서도 동일해야함
        String orderId = "KAKAO_M" + rechargeReq.getMemberNo();
        // 가맹점 코드, 10자
        params.put("cid", cid);
        // 가맹점 주문번호, 최대 100자
        params.put("partner_order_id", orderId);
        // 가맹점 회원 ID, 최대 100자
        params.put("partner_user_id", "Giveus");
        // 상품명, 최대 100자
        params.put("item_name", "일반 포인트 충전");
        // 상품 수량
        params.put("quantity", 1);
        // 상품 총액
        params.put("total_amount", rechargeReq.getAmount());
        // 상품 비과세 금액
        params.put("tax_free_amount", 0);
        // 결제 성공 시 redirect url, 최대 255자
        params.put("approval_url", rechargeSuccessUrl
                + "?member_no=" + rechargeReq.getMemberNo());
        // 결제 취소 시 redirect url, 최대 255자
        params.put("cancel_url", rechargeCancelUrl);
        // 결제 실패 시 redirect url, 최대 255자
        params.put("fail_url", rechargeFailUrl);

        /** Header와 Body 합쳐서 RestTemplate로 보내기 위한 밑작업 */
        HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<>(params, headers);

        /** RestTemplate로 Response 받아와서 DTO로 변환후 return */
        RestTemplate restTemplate = new RestTemplate();
        KakaoPayReadyRes payReadyResDto = restTemplate.postForObject(
                "https://open-api.kakaopay.com/online/v1/payment/ready",
                httpEntity,
                KakaoPayReadyRes.class);

        /** Tid Redis에 10분간 저장 */
        tidRepository.save(orderId, payReadyResDto.getTid(), 10L);

        return payReadyResDto;
    }

    /**
     * @param pgToken
     * @param memberNo
     * @return
     * @throws Exception
     */
    public KakaoPayApproveRes getApprove(String pgToken, int memberNo) throws Exception {
        String orderId = "KAKAO_M" + memberNo;
        String tid = tidRepository.find(orderId)
                .orElseThrow(() -> new Exception("결제 고유 번호가 존재하지 않습니다."));

        /** 요청 헤더 */
        HttpHeaders headers = getHttpHeaders();

        /** 요청 Body */
        Map<String, Object> params = new HashMap<>();
        // 가맹점 코드, 10자
        params.put("cid", cid);
        // 결제 고유 번호, 결제 준비 API 응답에 포함
        params.put("tid", tid);
        // 가맹점 주문번호, 결제 준비 API 요청과 일치해야 함
        params.put("partner_order_id", orderId); // 주문명
        // 가맹점 회원 id, 결제 준비 API 요청과 일치해야 함
        params.put("partner_user_id", "Giveus");
        // 결제승인 요청을 인증하는 토큰
        // 사용자 결제 수단 선택 완료 시, approval_url로 redirection해줄 때 pg_token을 query string으로 전달
        params.put("pg_token", pgToken);

        /** Header와 Body 합쳐서 RestTemplate로 보내기 위한 밑작업 */
        HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<>(params, headers);

        /** 요청 보내기 */
        RestTemplate restTemplate = new RestTemplate();
        KakaoPayApproveRes payApproveResDto = restTemplate.postForObject(
                "https://open-api.kakaopay.com/online/v1/payment/approve",
                httpEntity,
                KakaoPayApproveRes.class);

        /** tid Redis에서 삭제 */
        tidRepository.delete(orderId);

        return payApproveResDto;
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "SECRET_KEY " + secretKey);
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
