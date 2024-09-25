package com.giveus.notification.service;


import com.giveus.notification.common.util.FCMSender;
import com.giveus.notification.dto.request.FCMNotificationRes;
import com.giveus.notification.dto.response.FundingReviewListRes;
import com.giveus.notification.dto.response.NotificationListRes;
import com.giveus.notification.dto.response.UsageHistoryListRes;
import com.giveus.notification.entity.Notification;
import com.giveus.notification.entity.enums.NotificationCategory;
import com.giveus.notification.exception.NotificationDeleteFailedException;
import com.giveus.notification.exception.NotificationNotFoundException;
import com.giveus.notification.exception.NotificationUpdateFailedException;
import com.giveus.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class NotificationServiceImpl implements NotificationService{

    private final NotificationRepository notificationRepository;
    private final FCMSender fcmSender;
    private final RestTemplate restTemplate;

    @Value("${notification.auth-url}")
    private String authUrl;

    /**
     * @inheritDoc
     */
    @Override
    public List<NotificationListRes> getNotificationList(int memberNo) {
        return notificationRepository.findByMemberNoOrderByCreatedAtDesc(memberNo);
    }

    /**
     * @inheritDoc
     */
    @Override
    @Transactional
    public void deleteNotification(int notificationNo) {
        try {
            notificationRepository.deleteByNotificationNo(notificationNo);
        } catch(Exception e) {
            throw new NotificationDeleteFailedException();
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    @Transactional
    public void deleteAllNotification(int memberNo) {
        try {
            notificationRepository.deleteAllByMemberNo(memberNo);
        } catch(Exception e) {
            throw new NotificationDeleteFailedException();
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    @Transactional
    public void updateNotification(int notificationNo) {
        Notification notification = notificationRepository.findByNotificationNo(notificationNo).orElseThrow(NotificationNotFoundException::new);
        notification.setRead(true);
        notificationRepository.save(notification);
    }

    /**
     * @inheritDoc
     */
    @Override
    @Transactional
    public void updateAllNotification(int memberNo) {
        try {
            notificationRepository.updateAllByMemberNo(memberNo);
        } catch(Exception e) {
            throw new NotificationUpdateFailedException();
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    @Transactional
    public void createFundingReviewNotification(int fundingNo) {

        // 1) 해당 펀딩에 참여한 사람들 얻어옴
        List<FundingReviewListRes> list = notificationRepository.getFundingReviewList(fundingNo);

        ArrayList<Integer> people = new ArrayList<>();
        // 2) 해당 사람들 모두에게 알림 발송 + 기록
        for(int i=0; i<list.size(); i++) {
            if(list.get(i).isFundingReview() == false) continue; // 알림설정 false면 알림 보내지 x

            FCMNotificationRes fcmNotificationRes = FCMNotificationRes.builder()
                    .fcmToken(list.get(i).getDeviceToken())
                    .title("펀딩의 후기가 등록되었습니다")
                    .body(list.get(i).getTitle())
                    .image(list.get(i).getThumbnailUrl())
//                    .key(list.get(i).getFundingNo())
//                    .category("funding_review")
                    .build();

            // 2-1) 펀딩 후기 등록 알림 발송
            try {
                sendNotificationByToken(fcmNotificationRes);

                // 2-2) 알림 테이블에 기록 (해당 사람에게 알림 보낸 적 없다면 보냄)
                if(!people.contains(list.get(i).getMemberNo())) {
                    Notification notification = Notification.builder()
                            .memberNo(list.get(i).getMemberNo())
                            .category(NotificationCategory.REVIEW)
                            .content("펀딩 후기가 등록되었습니다")
                            .detail(list.get(i).getTitle())
                            .fundingNo(fundingNo)
                            .build();
                    notificationRepository.save(notification);

                    people.add(list.get(i).getMemberNo()); // 해당 사람에게 알림 보냈다고 표시
                }
            } catch (Exception e) {
                // 해당 fcm 토큰 삭제 (microservice간 통신)
                String requestUrl = authUrl + "?memberNo=" + list.get(i).getMemberNo() + "&deviceToken=" + list.get(i).getDeviceToken() ;

                log.info("=================requestUrl : " + requestUrl);
                ResponseEntity<String> response = restTemplate.exchange(
                        requestUrl, // 요청 URL
                        HttpMethod.DELETE, // 요청 메서드
                        null, // 요청 본문
                        String.class // 응답 타입
                );
            }

        }

    }

    /**
     * @inheritDoc
     */
    @Override
    @Transactional
    public void createUsageHistoryNotification(int fundingNo) {

        // 1) 해당 펀딩에 참여한 사람들 얻어옴
        List<UsageHistoryListRes> list = notificationRepository.getUsageHistoryList(fundingNo);

        ArrayList<Integer> people = new ArrayList<>();
        // 2) 해당 사람들 모두에게 알림 발송 + 기록
        for(int i=0; i<list.size(); i++) {
            if(list.get(i).isUsageHistory() == false) continue; // 알림설정 false면 알림 보내지 x

            FCMNotificationRes fcmNotificationRes = FCMNotificationRes.builder()
                    .fcmToken(list.get(i).getDeviceToken())
                    .title("펀딩 진료비 사용 내역이 등록되었습니다")
                    .body(list.get(i).getTitle())
//                    .key(list.get(i).getFundingNo())
//                    .category("funding_review")
                    .build();

            // 2-1) 펀딩 후기 등록 알림 발송
            try {
                sendNotificationByToken(fcmNotificationRes);

                // 2-2) 알림 테이블에 기록 (해당 사람에게 알림 보낸 적 없다면 보냄)
                if(!people.contains(list.get(i).getMemberNo())) {
                    Notification notification = Notification.builder()
                            .memberNo(list.get(i).getMemberNo())
                            .category(NotificationCategory.USAGE)
                            .content("펀딩 진료비 사용 내역이 등록되었습니다")
                            .detail(list.get(i).getTitle())
                            .fundingNo(fundingNo)
                            .build();
                    notificationRepository.save(notification);

                    people.add(list.get(i).getMemberNo()); // 해당 사람에게 알림 보냈다고 표시
                }
            } catch (Exception e) {
                // 해당 fcm 토큰 삭제 (microservice간 통신)
                String requestUrl = authUrl + "?memberNo=" + list.get(i).getMemberNo() + "&deviceToken=" + list.get(i).getDeviceToken() ;

                log.info("=================requestUrl : " + requestUrl);
                ResponseEntity<String> response = restTemplate.exchange(
                        requestUrl, // 요청 URL
                        HttpMethod.DELETE, // 요청 메서드
                        null, // 요청 본문
                        String.class // 응답 타입
                );
            }

        }

    }

    public void sendNotificationByToken(FCMNotificationRes fcmNotificationRes) {
        log.info("============== NotificationServiceImpl - sendNotificationByToken ::: fcmNotificationRes :  {}", fcmNotificationRes);

        if(fcmNotificationRes.getFcmToken() != null) {
            fcmSender.sendNotificationByToken(fcmNotificationRes);
        }

    }

}
