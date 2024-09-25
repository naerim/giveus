package com.giveus.notification.common.util;

import com.giveus.notification.dto.request.FCMNotificationRes;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class FCMSender {

    private final FirebaseMessaging firebaseMessaging;

    public void sendNotificationByToken(FCMNotificationRes fcmNotificationRes) {
        Notification notification = Notification.builder()
                .setTitle(fcmNotificationRes.getTitle())
                .setBody(fcmNotificationRes.getBody())
                .setImage(fcmNotificationRes.getImage())
                .build();

        Message message = Message.builder()
                .setToken(fcmNotificationRes.getFcmToken())
                .setNotification(notification)
//                .putData("key", String.valueOf(fcmNotificationRes.getKey()))
//                .putData("category", fcmNotificationRes.getCategory())
                .build();

        try {
            firebaseMessaging.send(message);
            log.info("FCM SUCCESS : {}", fcmNotificationRes.getFcmToken());
        } catch (FirebaseMessagingException e) {
            log.info("FCM ERROR : {}", fcmNotificationRes.getFcmToken());
            throw new RuntimeException(e);
//            throw new FCMTokenNotFoundException();
        }
    }

}