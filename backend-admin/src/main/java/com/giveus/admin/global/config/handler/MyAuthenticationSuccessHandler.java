package com.giveus.admin.global.config.handler;


import com.giveus.admin.global.util.JwtUtil;
import com.giveus.admin.domain.member.dto.AuthTokenRes;
import com.giveus.admin.domain.member.domain.Admin;
import com.giveus.admin.domain.member.dao.AuthAdminRepository;
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

    private final AuthAdminRepository authRepository;

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
            Admin admin = Admin.builder()
                    .email(email)
                    .name(oAuth2User.getAttribute("name"))
                    .provider(provider)
                    .key(key)
                    .build();
            authRepository.save(admin);
            log.info("MyAuthenticationSuccessHandler - Temperary register" + admin);

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