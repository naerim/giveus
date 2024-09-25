package com.giveus.admin.domain.member.application;

import com.giveus.admin.domain.member.domain.Admin;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

/**
 * OAuth2 기반 인증 로직을 처리하는 서비스 클래스
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final AdminService authService;

    // 요청에 따른 유저 정보를 불러옴
    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        // 상위 클래스에 정의된 액세스 토큰으로 사용자 정보를 가져오는 로직을 실행
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);

        try {
            return processOAuth2User(oAuth2UserRequest, oAuth2User);
        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            // TODO exception 처리
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }

    }
    
    // 각 OAuth2 제공자별 제공되는 사용자 정보를 동일한 인터페이스로 변환하여 리턴함
    private OAuth2User processOAuth2User(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {
        // registrationId(naver, kakao)와 userNameAttributeName(카카오:id, 네이버:id) 가져옴
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration()
                .getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
        String accessToken = userRequest.getAccessToken().getTokenValue();

        log.info("CustomOAuth2UserService - userNameAttributeName = {}, attributes = {}", userNameAttributeName, oAuth2User.getAttributes());
        log.info("CustomOAuth2UserService - accessToken = {}", accessToken);

        OAuth2Attribute oAuth2Attribute =
                OAuth2Attribute.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
        Map<String, Object> memberAttribute = oAuth2Attribute.convertToMap();

        // 가입된 회원인지 조회
        Optional<Admin> findMember = authService.findByProviderAndKey((String)memberAttribute.get("provider"), String.valueOf(memberAttribute.get("key")));

        if (findMember.isEmpty()) {
            memberAttribute.put("exist", false);
        } else {
            memberAttribute.put("exist", true);
        }

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN")),
                memberAttribute, "key"); ///!!!

    }

}