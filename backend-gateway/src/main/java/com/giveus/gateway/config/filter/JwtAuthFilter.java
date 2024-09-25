//package com.giveus.gateway.config.filter;
//
//import com.giveus.gateway.common.util.JwtUtil;
//import com.giveus.gateway.dto.SecurityMember;
//import com.giveus.gateway.exception.JwtException;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
////import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
////import org.springframework.security.core.Authentication;
////import org.springframework.security.core.authority.SimpleGrantedAuthority;
////import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//import java.util.List;
//
//@RequiredArgsConstructor
//@Slf4j
////@Component
//public class JwtAuthFilter extends OncePerRequestFilter { // OncePerRequestFilter : 어느 서블릿 컨테이너에서나 요청 당 한 번의 실행을 보장
//
//    private final JwtUtil jwtUtil;
////    private final MemberRepository memberRepository;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
//                                    FilterChain filterChain) throws ServletException, IOException {
//        String accessToken = request.getHeader("Authorization").replace("Bearer ", " ");
//
//        // AccessToken의 값이 비어있는 경우
//        if (!StringUtils.hasText(accessToken)) {
//            doFilter(request, response, filterChain); // 다음 filter로 통과(더 이상 토큰 검증을 진행 x)
//            return;
//        }
//
//        // AccessToken을 검증하였을때, 만료된 경우
//        if (!jwtUtil.verifyToken(accessToken)) {
//            throw new JwtException("AccessToken expired");
//        }
//        // AccessToken을 검증하였을때, 유효한 경우
//        else {
//            // 우리 서비스가 발행한 토큰인지 확인 => 위에서 jwtUtil.verifyToken()시 우리의 secret키로 확인하였음
//
//            // SecurityContext에 등록할 member 객체를 만들어줌
//            SecurityMember member = SecurityMember.builder()
//                    .provider(jwtUtil.getProvider(accessToken))
//                    .snsKey(jwtUtil.getSnsKey(accessToken))
//                    .build();
//            log.info("JwtAuthFilter - SecurityMember : {}", member.toString());
//
//            // SecurityContext에 인증 객체를 등록해줌
//            Authentication auth = getAuthentication(member);
//            SecurityContextHolder.getContext().setAuthentication(auth);
//        }
//
//        filterChain.doFilter(request, response);
//    }
//
//    // Authentication 객체로 변환
//    public Authentication getAuthentication(SecurityMember member) {
//        return new UsernamePasswordAuthenticationToken(member, "",
//                List.of(new SimpleGrantedAuthority("ROLE_USER")));
//    }
//
//}