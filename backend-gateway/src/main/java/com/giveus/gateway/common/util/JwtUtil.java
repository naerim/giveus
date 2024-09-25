package com.giveus.gateway.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
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
    private static String secret;

    // AccessToken이나 RefreshToken이 현재 유효한지 확인
    public boolean verifyToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(secret) // 비밀키를 설정하여 파싱함
                    .parseClaimsJws(token);  // 주어진 토큰을 파싱하여 Claims 객체를 얻음
            // 토큰의 만료 시간과 현재 시간비교
            return claims.getBody()
                    .getExpiration()
                    .after(new Date());  // 만료 시간이 현재 시간 이후인지 확인하여 유효성 검사 결과를 반환
        } catch (Exception e) {
            return false; // => 토큰이 유효하지 않다는 예외는 JWT 토큰을 검증하는 JwtAuthFilter에서 발생
        }
    }

    // 토큰에서 소셜로그인 고유키 추출
    public static String getSnsKey(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    // 토큰에서 소셜로그인 종류 추출
    public static String getProvider(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().get("provider", String.class);
    }

//    // 토큰에서 ROLE(권한)만 추출
//    public String getRole(String token) {
//        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().get("role", String.class);
//    }

}
