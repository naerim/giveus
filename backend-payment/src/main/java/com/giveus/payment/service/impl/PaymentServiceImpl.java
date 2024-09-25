package com.giveus.payment.service.impl;

import com.giveus.payment.entity.Payment;
import com.giveus.payment.repository.PaymentRepository;
import com.giveus.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    /**
     * @inheritDoc
     */
    @Override
    @Transactional
    public int save(String createdAt, String method, int amount, DateTimeFormatter formatter) {
        Payment payment = Payment.builder()
                .createdAt(LocalDateTime.parse(createdAt, formatter))
                .method(method)
                .amount(amount)
                .build();
        return paymentRepository.save(payment).getPaymentNo();
    }
}
