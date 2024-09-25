package com.giveus.auth.service;

import com.giveus.auth.dto.response.MemberSettingInfoRes;
import jakarta.servlet.http.HttpServletRequest;

public interface MemberService {
    /**
     * 엑세스 토큰으로 사용자 알림 허용 정보를 가져오는 메서드입니다.
     * @param httpServletRequest Header의 AccessToken
     * @return
     */
    MemberSettingInfoRes getMemberSetting(HttpServletRequest httpServletRequest);

    /**
     * 엑세스 토큰으로 펀딩 후기 등록 알림 설정 변경하는 메서드입니다.
     * @param httpServletRequest Header의 AccessToken
     * @return
     */
    MemberSettingInfoRes updateFundingReview(HttpServletRequest httpServletRequest);

    /**
     * 엑세스 토큰으로 진료비 세부 내역 등록 알림 설정 변경하는 메서드입니다.
     * @param httpServletRequest Header의 AccessToken
     * @return
     */
    MemberSettingInfoRes updateUsageHistory(HttpServletRequest httpServletRequest);
}
