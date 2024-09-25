package com.giveus.notification.dto.response;

import com.giveus.notification.entity.enums.NotificationCategory;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;


@Getter
@Builder
@AllArgsConstructor
@ToString
public class NotificationListRes {
    private int notificationNo;

    private int memberNo;

    private NotificationCategory category;

    private String content;

    private String detail;

    private LocalDate createdAt;

    private boolean isRead;

    private int fundingNo;
}
