package com.giveus.notification.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
public class FCMNotificationRes {

    private String fcmToken;
    private String title;
    private String body;
    private String image;
//    private int key;
//    private String category;
}
