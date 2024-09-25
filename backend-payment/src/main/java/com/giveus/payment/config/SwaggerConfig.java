package com.giveus.payment.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {
    @Value("${swagger.server}")
    private String server;
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("결제 API 명세서")
                        .description("C206")
                        .version("1.0.0")
                        .contact(new Contact()
                                .email("giveus2024@gmail.com")
                                .name("giveus"))
                )
                .servers(List.of(new Server()
                        .url(server)));
    }

}
