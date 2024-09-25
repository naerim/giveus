package com.giveus.auth.config.handler;

import com.giveus.auth.common.util.JwtUtil;
import com.giveus.auth.dto.response.AuthTokenRes;
import com.giveus.auth.entity.Member;
import com.giveus.auth.entity.MemberSetting;
import com.giveus.auth.repository.AuthMemberRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Component
@RequiredArgsConstructor
public class MyAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Value("${redirect.success}")
    private String redirectUrl;

    private final JwtUtil jwtUtil;

    private final AuthMemberRepository authRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        // 인증된 사용자 정보를 가져옴
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        log.info("MyAuthenticationSuccessHandler - oAuth2User = {}", oAuth2User);

        boolean isExist = oAuth2User.getAttribute("exist");
        String provider = oAuth2User.getAttribute("provider");
        String key = oAuth2User.getAttribute("key");
        String email = oAuth2User.getAttribute("email");

        // 회원이 존재 x => 임시 회원가입 => 추가 정보 입력받음
        if(!isExist) {
            Member member = Member.builder()
                    .email(email)
                    .name(oAuth2User.getAttribute("name"))
                    .imageUrl(oAuth2User.getAttribute("imageUrl"))
                    .provider(provider)
                    .key(key)
                    .build();
            member.addMemberSetting(new MemberSetting());
            authRepository.save(member);
            log.info("MyAuthenticationSuccessHandler - Temperary register" + member);

            String targetUrl = UriComponentsBuilder.fromUriString(redirectUrl)
                    .queryParam("type", "register")
                    .queryParam("provider", provider)
                    .queryParam("email", email)
                    .build()
                    .encode(StandardCharsets.UTF_8)
                    .toUriString();

            getRedirectStrategy().sendRedirect(request, response, targetUrl);
        }
        // 회원 존재 => 로그인 처리
        else {
            // jwt token 발행 시작
            AuthTokenRes token = jwtUtil.generateToken(provider, key);
            log.info("jwtToken = {}", token.getAccessToken());

            String targetUrl = UriComponentsBuilder.fromUriString(redirectUrl)
                    .queryParam("type", "login")
                    .queryParam("accessToken", token.getAccessToken())
                    .build()
                    .encode(StandardCharsets.UTF_8)
                    .toUriString();

            getRedirectStrategy().sendRedirect(request, response, targetUrl);
        }
    }

}