package com.giveus.payment.service.impl;

import com.giveus.payment.dto.request.PointUsageReq;
import com.giveus.payment.entity.Funding;
import com.giveus.payment.entity.MemberFunding;
import com.giveus.payment.entity.Payment;
import com.giveus.payment.entity.PointUsage;
import com.giveus.payment.repository.*;
import com.giveus.payment.service.MemberFundingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberFundingServiceImpl implements MemberFundingService {

    private final MemberFundingRepository memberFundingRepository;
    private final PaymentRepository paymentRepository;
    private final PointUsageRepository pointUsageRepository;
    private final FundingStatusHistoryRepository fundingStatusHistoryRepository;
    private final FundingRepository fundingRepository;

    /**
     * @inheritDoc
     */
    @Override
    @Transactional
    @CacheEvict(value = "fundingList", key = "'fundingNo:'+#fundingNo")
    public int save(int memberNo, int fundingNo, String method,
                    String createdAt, int amount, int point, boolean opened, DateTimeFormatter formatter) throws Exception {

        Integer pointUsageNo = null;
        if (point > 0) {
            PointUsage pointUsage = PointUsage.builder()
                    .memberNo(memberNo)
                    .amount(point)
                    .createdAt(LocalDateTime.parse(createdAt, formatter))
                    .build();
            pointUsageNo = pointUsageRepository.save(pointUsage).getPointNo();
        }
        Payment payment = Payment.builder()
                .createdAt(LocalDateTime.parse(createdAt, formatter))
                .method(method)
                .amount(amount)
                .build();
        int paymentNo = paymentRepository.save(payment).getPaymentNo();

        MemberFunding memberFunding = MemberFunding.builder()
                .memberNo(memberNo)
                .fundingNo(fundingNo)
                .paymentNo(paymentNo)
                .pointNo(pointUsageNo)
                .createdAt(LocalDateTime.parse(createdAt, formatter))
                .amount(amount + point)
                .isPublic(opened)
                .build();

        int memberFundingNo = memberFundingRepository.save(memberFunding).getMemberFundingNo();

        Funding funding = fundingRepository.findById(fundingNo).orElseThrow(() -> new IllegalArgumentException("펀딩이 존재하지 않습니다."));

        Integer totalAmount = memberFundingRepository.getTotalAmount(fundingNo);

        if (totalAmount + amount + point >= funding.getTargetAmount()) {
            fundingStatusHistoryRepository.updateFundingStatusToFinish(fundingNo);
        }

        return memberFundingNo;
    }

    /**
     * @inheritDoc
     */
    @Override
    @Transactional
    public int save(PointUsageReq request, int pointNo, LocalDateTime now) {
        MemberFunding memberFunding = MemberFunding.builder()
                .memberNo(request.getMemberNo())
                .fundingNo(request.getFundingNo())
                .pointNo(pointNo)
                .createdAt(now)
                .amount(request.getAmount())
                .isPublic(request.isOpened())
                .build();
        return memberFundingRepository.save(memberFunding).getMemberFundingNo();
    }
}
