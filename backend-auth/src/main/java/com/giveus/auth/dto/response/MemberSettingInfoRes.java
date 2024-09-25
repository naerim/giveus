package com.giveus.auth.dto.response;

import com.giveus.auth.entity.MemberSetting;
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
