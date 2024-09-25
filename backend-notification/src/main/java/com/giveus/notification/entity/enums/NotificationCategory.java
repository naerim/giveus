package com.giveus.notification.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NotificationCategory {
    REVIEW("후기등록"),
    USAGE("진료비등록"),
    PAYMENT("정기결제"),
    RECOMMEND("펀딩추천");

    private final String category;
}
