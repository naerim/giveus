package com.giveus.admin.infra.sms;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CoolSmsClient {
    @Value("${coolsms.api.key}")
    private String apiKey;

    @Value("${coolsms.api.secret}")
    private String apiSecret;

    @Value("${coolsms.env.phone}")
    private String from;
    private final String COOLSMS_URL = "https://api.coolsms.co.kr";

    private DefaultMessageService messageService;

    @PostConstruct
    private void init() {
        this.messageService = NurigoApp.INSTANCE.initialize(apiKey, apiSecret, COOLSMS_URL);
    }

    public SingleMessageSentResponse send(String to, String msg, String regId) {
        Message message = new Message();
        message.setFrom(from);
        message.setTo(to);
        message.setText(msg + regId);
        SingleMessageSentResponse response = null;

//        try {
        response = this.messageService.sendOne(new SingleMessageSendingRequest(message));
//        } catch (Exception e) {
//            // TODO 펀딩 정상 등록되지 않았다는 혹은 문자가 정상적으로 발생되지 않았다는 예외 발생하기
////            throw new
//        }

        return response;
    }
}
