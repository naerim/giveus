package com.giveus.gateway.config;

//@Slf4j
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class SecurityConfig {
//
//    private final JwtAuthFilter jwtAuthFilter;
//    private final JwtExceptionFilter jwtExceptionFilter;
//
////    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//        http
//                .httpBasic(config -> config.disable())
//                .cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.disable())
//                .csrf(config -> config.disable())
//                .sessionManagement(config -> config.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .formLogin(config -> config.disable())
////        http.exceptionHandling(exceptionHandlingSpec ->
////                exceptionHandlingSpec.accessDeniedHandler(accessDeniedHandler)); // 인증 에러 처리
////
////        http.exceptionHandling(exceptionHandlingSpec ->
////                exceptionHandlingSpec.authenticationEntryPoint(loginServerAuthenticationEntryPoint)); // 인가 에러 처리
//
//                // 요청에 대한 인증 설정
//                .authorizeHttpRequests(config -> config
//                                .requestMatchers("/**").permitAll()
////                        .requestMatchers("/token/**").permitAll() // 접근 권한 검사 x(인증 필요 없다는 뜻은 x). 토큰 발급을 위한 경로는 모두 허용
////                        .requestMatchers("/mypage/**").hasAnyRole("USER", "ADMIN") // 마이페이지는 회원, 관리자 권한디 있어야 접근 가능
////                        .requestMatchers("/", "/css/**", "/images/**", "/js/**", "/favicon.ico", "/h2-console/**").permitAll()
////                                .anyRequest().authenticated() // 그 외의 모든 요청은 인증이 필요
//                                .anyRequest().permitAll()
//
//                );
//
//        // JWT 인증 필터를 UsernamePasswordAuthenticationFilter 앞에 추가
//        http
//                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
//                .addFilterBefore(jwtExceptionFilter, JwtAuthFilter.class);
//        return http.build();
//    }
//}
