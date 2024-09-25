package com.giveus.auth.service;

import com.giveus.auth.common.util.JwtUtil;
import com.giveus.auth.dto.response.MemberSettingInfoRes;
import com.giveus.auth.entity.Member;
import com.giveus.auth.entity.MemberSetting;
import com.giveus.auth.exception.NoMemberExistException;
import com.giveus.auth.repository.AuthMemberRepository;
import com.giveus.auth.repository.MemberSettingRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class MemberServiceImpl implements MemberService{

    private final AuthMemberRepository authMemberRepository;
    private final MemberSettingRepository memberSettingRepository;
    private final JwtUtil jwtutil;

    /**
     * @inheritDoc
     */
    @Override
    public MemberSettingInfoRes getMemberSetting(HttpServletRequest httpServletRequest) {
        Member member = getMemberByAccessToken(httpServletRequest);

        MemberSetting memberSetting = memberSettingRepository.findMemberSettingByMember(member).get();

        return MemberSettingInfoRes.from(memberSetting);
    }

    /**
     * @inheritDoc
     */
    @Override
    @Transactional
    public MemberSettingInfoRes updateFundingReview(HttpServletRequest httpServletRequest) {
        Member member = authMemberRepository.findByMemberNo(21).get();
//        Member member = getMemberByAccessToken(httpServletRequest);

        MemberSetting memberSetting = memberSettingRepository.findMemberSettingByMember(member).get();
        memberSetting.changeFundingReview(); // 펀딩 후기 등록 알림 설정 변경 (true <-> false)
        memberSettingRepository.save(memberSetting);

        return MemberSettingInfoRes.from(memberSetting);
    }

    /**
     * @inheritDoc
     */
    @Override
    @Transactional
    public MemberSettingInfoRes updateUsageHistory(HttpServletRequest httpServletRequest) {
        Member member = getMemberByAccessToken(httpServletRequest);

        MemberSetting memberSetting = memberSettingRepository.findMemberSettingByMember(member).get();
        memberSetting.changeUsageHistory(); // 진료비 세부 내역 등록 알림 설정 변경 (true <-> false)
        memberSettingRepository.save(memberSetting);

        return MemberSettingInfoRes.from(memberSetting);
    }

    public Member getMemberByAccessToken(HttpServletRequest httpServletRequest) {
        String accessToken = httpServletRequest.getHeader("Authorization").replace("Bearer", "");

        String provider = jwtutil.getProvider(accessToken);
        String snsKey = jwtutil.getSnsKey(accessToken);

        return authMemberRepository.findByProviderAndKey(provider, snsKey).orElseThrow(NoMemberExistException::new);
    }

}
