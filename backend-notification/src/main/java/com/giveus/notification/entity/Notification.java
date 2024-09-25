package com.giveus.notification.entity;

import com.giveus.notification.entity.enums.NotificationCategory;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;

@Entity
@Table(name = "notification")
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicInsert
@DynamicUpdate
public class Notification {

    @Id
    @Column(name = "notification_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int notificationNo;

    @Column(name = "member_no")
    private int memberNo;

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private NotificationCategory category;

    @Column(name = "content")
    private String content;

    @Column(name = "detail")
    private String detail;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Setter
    @Column(name = "is_read", columnDefinition = "TINYINT(1)")
    @ColumnDefault("false")
    private boolean isRead;

    @Column(name = "funding_no")
    private int fundingNo;

    public void updateIsRead() {
        this.isRead = true;
    }

}
