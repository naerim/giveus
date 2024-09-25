package com.giveus.auth.service;

import com.giveus.auth.common.dto.CreateSuccessDto;
import com.giveus.auth.common.util.JwtUtil;
import com.giveus.auth.dto.request.AuthJoinPostReq;
import com.giveus.auth.dto.request.MemberDevicePostReq;
import com.giveus.auth.dto.response.MemberInfoRes;
import com.giveus.auth.dto.response.NicknameExistRes;
import com.giveus.auth.entity.Member;
import com.giveus.auth.entity.MemberDevice;
import com.giveus.auth.exception.NoMemberExistException;
import com.giveus.auth.repository.AuthMemberDeviceRepository;
import com.giveus.auth.repository.AuthMemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final AuthMemberRepository authMemberRepository;
    private final AuthMemberDeviceRepository authMemberDeviceRepository;

    private final JwtUtil jwtutil;

    @Override
    public MemberInfoRes updateMember(AuthJoinPostReq userJoinPostReq) {
        String email = userJoinPostReq.getEmail();
        String provider = userJoinPostReq.getProvider();

        log.info("email = {}, provider = {}", email, provider);
        Member member = authMemberRepository.findByProviderAndEmail(provider, email).orElseThrow(NoMemberExistException::new);
        member.updateName(userJoinPostReq.getName());
        member.updateNickname(userJoinPostReq.getNickname());

        authMemberRepository.save(member);

        return MemberInfoRes.from(member);
    }

    @Override
    public Optional<Member> findByProviderAndKey(String provider, String key) {
        return authMemberRepository.findByProviderAndKey(provider, key);
    }

    @Override
    public MemberInfoRes findByMemberNo(int memberNo) {
        log.info("=== AuthServiceImpl - findByMemberNum === \n memberNo : {}", memberNo);
        Member member = authMemberRepository.findByMemberNo(memberNo).orElseThrow(NoMemberExistException::new);

        return MemberInfoRes.from(member);
    }

    @Override
    public MemberInfoRes findByAccessToken(HttpServletRequest httpServletRequest) {
        String accessToken = httpServletRequest.getHeader("Authorization").replace("Bearer", "");

        String provider = jwtutil.getProvider(accessToken);
        String snsKey = jwtutil.getSnsKey(accessToken);

        log.info("=== AuthServiceImpl - findByAccessToken === \n provider : {}, snsKey : {} ", provider, snsKey);
        Member member = authMemberRepository.findByProviderAndKey(provider, snsKey).orElseThrow(NoMemberExistException::new);

        return MemberInfoRes.from(member);
    }

    @Override
    public CreateSuccessDto createMemberDevice(MemberDevicePostReq memberDevicePostReq) {
        log.info("=== AuthServiceImpl - createMemberDevice === memberDevicePostReq : {}", memberDevicePostReq.toString());

        // 1) 해당 FCM 토큰이 있는지 확인
        Optional<MemberDevice> memberDevice = authMemberDeviceRepository.findByDeviceToken(memberDevicePostReq.getDeviceToken());

        // 2-1) 해당 FCM 토큰이 있다면 => 마지막 로그인 시간 업데이트
        if(memberDevice.isPresent()) {
            MemberDevice device = memberDevice.get();
            device.updateLastLoginAt();

            log.info("FCM 토큰 이미 존재");
            authMemberDeviceRepository.save(device);
        }
        // 2-2) 해당 FCM 토큰이 없다면 => 새로 Device 추가
        else {
            MemberDevice device = MemberDevice.builder()
                    .memberNo(memberDevicePostReq.getMemberNo())
                    .deviceToken(memberDevicePostReq.getDeviceToken())
                    .build();

            log.info("FCM 토큰 새로 추가");
            authMemberDeviceRepository.save(device);
        }

        return new CreateSuccessDto(memberDevicePostReq.getMemberNo());

    }

    @Transactional
    @Override
    public String deleteMemberDevice(int memberNo, String deviceToken) {
        log.info("===========" + memberNo + ", " + deviceToken);
        // 유효하지 않은 FCM 토큰 삭제
        authMemberDeviceRepository.deleteByMemberNoAndDeviceToken(memberNo, deviceToken);

        return "";
    }

    @Override
    public NicknameExistRes findByNickname(String nickname) {
        log.info("=== AuthServiceImpl - findByNickname === \n nickname : {}", nickname);
        Optional<Member> member = authMemberRepository.findByNickname(nickname);
        if(member.isPresent())
            return new NicknameExistRes(nickname, true);
        else
            return new NicknameExistRes(nickname, false);
    }

}
