package com.giveus.notification.dto.response;

import com.giveus.notification.entity.MemberSetting;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemberSettingInfoRes {

    private boolean fundingReview;

    private boolean usageHistory;

    public static MemberSettingInfoRes from(MemberSetting memberSetting) {
        return MemberSettingInfoRes.builder()
                .fundingReview(memberSetting.isFundingReview())
                .usageHistory(memberSetting.isUsageHistory())
                .build();
    }

}
