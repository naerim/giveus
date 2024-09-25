package com.giveus.notification.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UsageHistoryListRes {

    private int memberNo;

    private int fundingNo;

    private String deviceToken;

    private String title;

    private boolean usageHistory;

}
