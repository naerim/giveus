package com.giveus.admin.global.util;


import com.giveus.admin.domain.member.dto.AuthTokenRes;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Access Token과 Refresh Token을 발급하는 클래스
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration.access}")
    private Long accessPeriod;

    @Value("${jwt.expiration.refresh}")
    private Long refreshPeriod;

    // 최초 로그인시 AccessToken과 RefreshToken을 발급
    public AuthTokenRes generateToken(String provider, String key) {

        String accessToken = generateAccessToken(provider, key);
        String refreshToken = generateRefreshToken(provider, key);
        log.info("================ accessToken :  {} =====================", accessToken);

        return new AuthTokenRes(accessToken, refreshToken);
    }

    public String generateRefreshToken(String provider, String key) {
        Claims claims = Jwts.claims().setSubject(key);
        claims.put("provider", provider);

        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + refreshPeriod))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String generateAccessToken(String provider, String key) {
        Claims claims = Jwts.claims().setSubject(key);
        claims.put("provider", provider);

        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + accessPeriod))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();

    }

    // 토큰에서 sns_key 추출
    public String getSnsKey(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    // 토큰에서 provider 추출
    public String getProvider(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().get("provider", String.class);
    }

}
