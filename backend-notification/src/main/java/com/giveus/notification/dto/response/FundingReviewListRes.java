package com.giveus.notification.dto.response;

import com.giveus.notification.entity.enums.NotificationCategory;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FundingReviewListRes {

    private int memberNo;

    private int fundingNo;

    private String deviceToken;

    private String title;

    private String thumbnailUrl;

    private boolean fundingReview;

}
