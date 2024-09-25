package com.giveus.admin.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Collections;
import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(
                List.of("http://localhost:3000", "http://localhost:3001", "http://localhost:5173", "http://localhost:8081", "http://localhost:8082",
                        "https://admin.giveus.site", "https://giveus.site", "http://giveus.site:8081"));
        configuration.setAllowedOriginPatterns(
                List.of("http://localhost:3000/**", "http://localhost:3001/**", "http://localhost:5173/**", "http://localhost:8081/**", "http://localhost:8082/**",
                        "https://admin.giveus.site/**", "https://giveus.site", "http://giveus.site:8081"));
        configuration.setAllowedMethods(Collections.singletonList("*"));
        configuration.setAllowedHeaders(Collections.singletonList("*"));
        configuration.setAllowCredentials(true);

        // configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000", "http://localhost:3001", "http://localhost:5173", "http://localhost:8081", "http://localhost:8082",
                                "https://admin.giveus.site", "https://giveus.site", "http://giveus.site:8081")
                .allowedMethods("HEAD", "GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowCredentials(true) // 쿠키 인증 요청 허용
                .maxAge(3000); // 원하는 시간만큼 pre-flight 리퀘스트를 캐싱
    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
////        registry.addResourceHandler("/resources/**").addResourceLocations("/WEB-INF/resources/");
//
//        registry.addResourceHandler("swagger-ui.html")
//                .addResourceLocations("classpath:/META-INF/resources/");
//
//        registry.addResourceHandler("/webjars/**")
//                .addResourceLocations("classpath:/META-INF/resources/webjars/");
//    }

//    public Filter requestLoggingFilter() {
//        CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
//        loggingFilter.setIncludeClientInfo(true);
//        loggingFilter.setIncludeQueryString(true);
//        loggingFilter.setIncludePayload(true);
//        loggingFilter.setIncludeHeaders(true);
//        loggingFilter.setMaxPayloadLength(64000);
//        return loggingFilter;
//    }

//    @Bean
//    public FilterRegistrationBean loggingFilterRegistration() {
//        FilterRegistrationBean registration = new FilterRegistrationBean(requestLoggingFilter());
//        registration.addUrlPatterns("/api/*");
//        return registration;
//    }
}
