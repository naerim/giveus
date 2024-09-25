package com.giveus.gateway.config.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

@Component
@Slf4j
public class GlobalFilter extends AbstractGatewayFilterFactory<GlobalFilter.Config> {

    public GlobalFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        // Global Pre Filter
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();
            long startTime = System.currentTimeMillis();


            // 요청 method, id, uri 로깅
            if (config.isPreLogger()) {
                log.info("{} route uri -> {}", request.getMethod(), request.getURI());
            }

            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                long endTime = System.currentTimeMillis();
                long processedTime = endTime - startTime;
                if (config.isPostLogger()) {
                    log.info("response code -> {}, took -> {}ms", response.getStatusCode(), processedTime);
                }
            }));
        };
    }

    @AllArgsConstructor
    @Getter
    public static class Config {
        private boolean preLogger;
        private boolean postLogger;
    }
}