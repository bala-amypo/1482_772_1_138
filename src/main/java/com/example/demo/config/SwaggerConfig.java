package com.example.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {

        final String securitySchemeName = "BearerAuth";

        // ✅ Define server (host + port)
        Server server = new Server();
        server.setUrl("https://9006.pro604cr.amypo.ai");
        server.setDescription("Production Server");

        return new OpenAPI()
                .info(new Info()
                        .title("Hotel Room Key Digital Share API")
                        .description("Spring Boot REST API for managing digital room keys")
                        .version("1.0.0"))
                .addServersItem(server) // ✅ ADD SERVER HERE
                .addSecurityItem(new SecurityRequirement()
                        .addList(securitySchemeName))
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .name(securitySchemeName)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")));
    }
}
